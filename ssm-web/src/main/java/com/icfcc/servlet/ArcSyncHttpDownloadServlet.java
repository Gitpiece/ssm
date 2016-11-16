package com.icfcc.servlet;

import me.pinenut.util.StringUtils;
import com.icfcc.web.util.WebContentUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件下载servlet，支持断点续传。
 * todo 断点继传功能还有问题，需要调整
 */
public class ArcSyncHttpDownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    final static Log logger = LogFactory.getLog(ArcSyncHttpDownloadServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String file = request.getParameter("file");
        if (StringUtils.isEmpty(file)) {
            file = "D:\\TD\\infoQ第一期\\分布式平台内核揭秘.avi";
        }
        File downloadFile = new File(file);//要下载的文件

        long fileLength = downloadFile.length();//记录文件大小
        logger.info("fileLength：" + fileLength);
        long pastLength = 0;//记录已下载文件大小
        int rangeSwitch = 0;//0：从头开始的全文下载；1：从某字节开始的下载（bytes=27000-）；2：从某字节开始到某字节结束的下载（bytes=27000-39000）
        long toLength = 0;//记录客户端需要下载的字节段的最后一个字节偏移量（比如bytes=27000-39000，则这个值是为39000）
        long contentLength = 0;//客户端请求的字节总量
        String rangeBytes = "";//记录客户端传来的形如“bytes=27000-”或者“bytes=27000-39000”的内容
        RandomAccessFile raf = null;//负责读取数据
        OutputStream os = null;//写出数据
        OutputStream out = null;//缓冲
        byte b[] = new byte[1024];//暂存容器

        if (request.getHeader("Range") != null) {// 客户端请求的下载的文件块的开始字节
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            logger.info("request.getHeader(\"Range\")=" + request.getHeader("Range"));
            rangeBytes = request.getHeader("Range").replaceAll("bytes=", "");
            int index = rangeBytes.indexOf('-');
            int length = rangeBytes.length() - 1;
            if (index == 1) {//bytes=969998336-
                rangeSwitch = 1;
// rangeBytes = rangeBytes.substring(0, rangeBytes.indexOf('-')); 
                String temp0 = rangeBytes.substring(0, rangeBytes.indexOf('-'));
                String temp2 = rangeBytes.substring(rangeBytes.indexOf('-') + 1, rangeBytes.length());
                pastLength = Long.parseLong(temp0.trim());
                if ("".equals(temp2)) {
                    contentLength = fileLength - pastLength + 1;//客户端请求的是 969998336 之后的字节
                } else {
                    toLength = Long.parseLong(temp2);//bytes=1275856879-1275877358，到第 1275877358 个字节结束
                    contentLength = toLength - pastLength + 1;//客户端请求的是 1275856879-1275877358 之间的字节
                }
            } else {//bytes=1275856879-1275877358
                rangeSwitch = 2;
                String temp0 = rangeBytes.substring(0, rangeBytes.indexOf('-'));
                String temp2 = rangeBytes.substring(rangeBytes.indexOf('-') + 1, rangeBytes.length());
                pastLength = Long.parseLong(temp0.trim());//bytes=1275856879-1275877358，从第 1275856879 个字节开始下载
                toLength = Long.parseLong(temp2);//bytes=1275856879-1275877358，到第 1275877358 个字节结束
                contentLength = toLength - pastLength + 1;//客户端请求的是 1275856879-1275877358 之间的字节
            }
        } else {//从开始进行下载
            contentLength = fileLength;//客户端要求全文下载
        }

        /*
         * 如果设设置了Content-Length，则客户端会自动进行多线程下载。如果不希望支持多线程，则不要设置这个参数。
         * 响应的格式是:
         * Content-Length: [文件的总大小] - [客户端请求的下载的文件块的开始字节]
         * ServletActionContext.getResponse().setHeader("Content-Length",
         * new Long(file.length() - p).toString());
         */
        response.reset();//告诉客户端允许断点续传多线程连接下载,响应的格式是:Accept-Ranges: bytes
        response.setHeader("Accept-Ranges", "bytes");//如果是第一次下,还没有断点续传,状态是默认的 200,无需显式设置;响应的格式是:HTTP/1.1 200 OK
        if (pastLength != 0) {
            //不是从最开始下载,
            //响应的格式是:
            //Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小]
            logger.info("----------------------------不是从开始进行下载！服务器即将开始断点续传...");
            switch (rangeSwitch) {
                case 1: {//针对 bytes=27000- 的请求
                    String contentRange = new StringBuffer("bytes ").append(new Long(pastLength).toString()).append("-").append(new Long(fileLength - 1).toString()).append("/").append(new Long(fileLength).toString()).toString();
                    response.setHeader("Content-Range", contentRange);
                    break;
                }
                case 2: {//针对 bytes=27000-39000 的请求
                    String contentRange = rangeBytes + "/" + new Long(fileLength).toString();
                    response.setHeader("Content-Range", contentRange);
                    break;
                }
                default: {
                    break;
                }
            }
        } else {
            //是从开始下载
            logger.info("----------------------------是从开始进行下载！");
        }

        try {
            response.addHeader("Content-Disposition", "attachment; filename=\"" + downloadFile.getName() + "\"");
            response.setContentType(WebContentUtil.setContentType(downloadFile.getName()));// set the MIME type.
            response.addHeader("Content-Length", String.valueOf(contentLength));
            os = response.getOutputStream();
            out = new BufferedOutputStream(os);
            raf = new RandomAccessFile(downloadFile, "r");
            try {
                switch (rangeSwitch) {
                    case 0: {//普通下载，或者从头开始的下载
                        //同1
                    }
                    case 1: {//针对 bytes=27000- 的请求
                        raf.seek(pastLength);//形如 bytes=969998336- 的客户端请求，跳过 969998336 个字节
                        int n = 0;
                        while ((n = raf.read(b, 0, 1024)) != -1) {
                            out.write(b, 0, n);
                        }
                        break;
                    }
                    case 2: {//针对 bytes=27000-39000 的请求
                        raf.seek(pastLength - 1);//形如 bytes=1275856879-1275877358 的客户端请求，找到第 1275856879 个字节
                        int n = 0;
                        long readLength = 0;//记录已读字节数
                        while (readLength <= contentLength - 1024) {//大部分字节在这里读取
                            n = raf.read(b, 0, 1024);
                            readLength += 1024;
                            out.write(b, 0, n);
                        }
                        if (readLength <= contentLength) {//余下的不足 1024 个字节在这里读取
                            n = raf.read(b, 0, (int) (contentLength - readLength));
                            out.write(b, 0, n);
                        }
                        //
                        // raf.seek(pastLength);//形如 bytes=1275856879-1275877358 的客户端请求，找到第 1275856879 个字节
                        // while (raf.getFilePointer() < toLength) {
                        // out.write(raf.read());
                        // }
                        break;
                    }
                    default: {
                        break;
                    }
                }
                out.flush();
            } catch (IOException ie) {
                logger.error(ie.getMessage(),ie);
                throw ie;
                /*
                 * 在写数据的时候，
                 * 对于 ClientAbortException 之类的异常，
                 * 是因为客户端取消了下载，而服务器端继续向浏览器写入数据时，
                 * 抛出这个异常，这个是正常的。
                 * 尤其是对于迅雷这种吸血的客户端软件，
                 * 明明已经有一个线程在读取 bytes=1275856879-1275877358，
                 * 如果短时间内没有读取完毕，迅雷会再启第二个、第三个。。。线程来读取相同的字节段，
                 * 直到有一个线程读取完毕，迅雷会 KILL 掉其他正在下载同一字节段的线程，
                 * 强行中止字节读出，造成服务器抛 ClientAbortException。
                 * 所以，我们忽略这种异常
                 */
                //ignore
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }
}

 