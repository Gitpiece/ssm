package com.icfcc.util.file;

import java.io.*;

/**
 * Created by admin on 2015/10/29.
 */
public class FileEncoding {

    public static String fatchCharset(String filepath) throws IOException {
        InputStream is = new FileInputStream(new File(filepath));
        byte[] b = new byte[1024];
        int length = is.read(b);
        return fatchCharset(b,length);
    }

    public static String fatchCharset(byte[] b){
        return fatchCharset(b,b.length);
    }
    public static String fatchCharset(byte[] b, int length){
        return null;
    }

    public static String codeString(String fileName) throws Exception{
        BufferedInputStream bin = new BufferedInputStream(
                new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        String code = null;

        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }

        return code;
    }
}
