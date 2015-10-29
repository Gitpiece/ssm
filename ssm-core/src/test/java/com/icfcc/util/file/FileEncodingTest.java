package com.icfcc.util.file;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by admin on 2015/10/29.
 */
public class FileEncodingTest {

    @Test
    public void testGBK() throws IOException {
        InputStream is = FileEncodingTest.class.getResourceAsStream("gbk.txt");
        byte[] b = new byte[1024];
        int length = is.read(b);
        String charset = FileEncoding.fatchCharset(b);
        Assert.assertEquals("GBK", charset);
    }

    @Test
    public void testGBK2()throws Exception{
        System.out.println(FileEncodingTest.class.getResource("gbk.txt").getFile());
        String charset = FileEncoding.codeString(FileEncodingTest.class.getResource("gbk.txt").getFile());
        System.out.println(charset);
        Assert.assertEquals("GBK", charset);
    }


    @Test
    public void testutf8() throws IOException {
        InputStream is = FileEncodingTest.class.getResourceAsStream("utf8.txt");
        byte[] b = new byte[1024];
        int length = is.read(b);
        for (int i = 0; i < length; i++) {
            System.out.println(b[i]);
        }
        String charset = FileEncoding.fatchCharset(b,length);
        Assert.assertEquals("UTF-8", charset);
    }

    @Test
    public void testutf82()throws Exception{
        System.out.println(FileEncodingTest.class.getResource("utf8.txt").getFile());
        String charset = FileEncoding.codeString(FileEncodingTest.class.getResource("utf8.txt").getFile());
        System.out.println(charset);
        Assert.assertEquals("UTF-8", charset);
    }

    public void testEncoding(){
        byte[] b = new byte[3];
//        b[0] = oxef;
//        Integer

        System.out.println();
    }
}
