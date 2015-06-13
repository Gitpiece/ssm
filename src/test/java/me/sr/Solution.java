package me.sr;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main( String[] args ) throws UnsupportedEncodingException
    {
        String urlStr =  "http://10.1.4.79:80/ibmcognos/cgi-bin/cognos.cgi?b_action=cognosViewer&ui.action=run&ui.object=%2fcontent%2fpackage%5b%40name%3d%27BBUS_ORG%27%5d%2freport%5b%40name%3d%27%e5%9f%ba%e7%a1%80%e4%b8%9a%e5%8a%a1%e6%9c%ba%e6%9e%84%e8%a1%a8%27%5d&ui.name=%e5%9f%ba%e7%a1%80%e4%b8%9a%e5%8a%a1%e6%9c%ba%e6%9e%84%e8%a1%a8&run.outputFormat=&run.prompt=true" ;
        System.out.println(URLDecoder.decode(urlStr, "UTF-8"));
        System.out.println(URLEncoder.encode(URLDecoder.decode(urlStr, "UTF-8"),"UTF-8"));

    }
}
