package com.icfcc.util.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 文件修改检查类，如果文件修改时间超过，删除超时(过期)的文件。
 *
 * <p><blockquote><pre>
 *     FileModifyChecker fileModifyChecker = new FileModifyChecker(monitorpath,offtime);
 *     //定时运行
 *     fileModifyChecker.backgroundProcess();
 * </pre></blockquote>
 *
 * Created by wanghuanyu on 2015/6/12.
 */
public class FileTimeoutChecker {
    public Log log = LogFactory.getLog(getClass());
    private final HashMap<String, Long> jarModificationTimes = new HashMap<String, Long>();

    /**
     * 修改时间差
     */
    Long offtime = 0L;

    /**
     * 监控目录
     */
    String monitorPath;

    public FileTimeoutChecker(String monitorPath, Long offtime) {
        this.monitorPath = monitorPath;
        this.offtime = offtime;
        init();
    }

    /**
     *
     */
    protected void init() {
        //初始化设置为当前系统时间
        File monitorFile = new File(monitorPath);
        jarModificationTimes.put(monitorPath, monitorFile.lastModified());

        if(log.isDebugEnabled()){
            log.debug("监控文件目录："+monitorPath+"；文件修改时间"+monitorFile.lastModified());
        }
    }

    /**
     * @return
     */
    public boolean modified() {
        Long recordedLastModified = jarModificationTimes.get(monitorPath);
        if(log.isDebugEnabled()){
            log.debug("记录中的最后修改时间："+recordedLastModified);
        }
        if (recordedLastModified == null) {
            return false;
        }
        if(log.isDebugEnabled()){
            log.debug("当前时间："+new Date());
        }
        if (isModified(System.currentTimeMillis(), recordedLastModified, offtime)) {
            return true;
        }

        return false;
    }

    private boolean isModified(Long currenttime, Long recordedLastModified, Long offtime) {
        if(log.isDebugEnabled()){
            log.debug("时间差："+(currenttime - recordedLastModified.longValue())+"；时间差范围"+offtime);
        }
        if (currenttime - recordedLastModified.longValue() > offtime) {
            return true;
        }
        return false;
    }

    public void backgroundProcess() {
        if (modified()) {
            File monitorFile = new File(monitorPath);
            deleteModifiedFile(monitorFile);
            jarModificationTimes.put(monitorPath, monitorFile.lastModified());
        }
    }

    /**
     * 删除修改的文件
     */
    private void deleteModifiedFile(File monitorFile) {
        List<File> files = new ArrayList<File>(1);
        List<File> tempFiles = new ArrayList<File>(1);
        files.add(monitorFile);
        while (files.size() > 0) {

            for (File file : files) {

                //如果是文件，如果修改，删除。
                if (file.isFile() && isModified(System.currentTimeMillis(), file.lastModified(), offtime)) {
                    deletefile(file);
                } else if (file.isDirectory()) {
                    File[] files1 = file.listFiles();
                    for (int i = 0; i < files1.length; i++) {
                        File file1 = files1[i];
                        if (file1.isFile() && isModified(System.currentTimeMillis(), file1.lastModified(), offtime)) {
//                        	System.out.println(System.currentTimeMillis()+"-"+file1.lastModified()+"-"+offtime);
                            deletefile(file1);
                        } else if (file1.isDirectory()) {
                            tempFiles.add(file1);
                        }
                    }
                }
            }
            files.clear();
            files.addAll(tempFiles);
            tempFiles.clear();
        }
//        System.out.println(monitorFile.lastModified());
        jarModificationTimes.put(monitorPath, monitorFile.lastModified());
    }

    private void deletefile(File file) {
        try {
            FileUtils.forceDelete(file);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        if(log.isDebugEnabled()){
            log.debug("delete file :"+file.getAbsolutePath());
        }
    }
}
