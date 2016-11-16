package me.pinenut.util.file;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by admin on 2015/10/30.
 */
public class FileCharsetDetectorTest {

    @Test
    public void detectCharsetTest() throws Exception{
        //binary file
        detectCharset("user.jpg");
        detectCharset("zip.zip");
        //txt
        detectCharset("ASCII.txt");
        detectCharset("gbk.txt");
        detectCharset("gb2312.txt");
        detectCharset("gb18030.txt");
        detectCharset("utf8.txt");
        detectCharset("utf8-bom.txt");
        detectCharset("utf16.txt");
    }

    private void detectCharset(String filename) throws IOException {
        File file = getFile(filename);
        String charset = new FileCharsetDetector().guessFileEncoding(file);
        System.out.println(file.getAbsolutePath());
        System.out.printf("charset : %s\n", charset);
        String[] charsetArr = charset.split(",");
        for (int i = 0; i < charsetArr.length; i++) {
            System.out.printf("charset %s`s content : %s\n", charsetArr[i], FileUtils.readFileToString(file, charsetArr[i]));
            break;
        }
        System.out.printf("\n");
    }

    protected File getFile(String filepath){
        Class clazz = FileCharsetDetectorTest.class;
        File file = new File(clazz.getResource(filepath).getFile());
        return file;
    }
}
