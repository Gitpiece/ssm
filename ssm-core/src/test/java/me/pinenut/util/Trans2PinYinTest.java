package me.pinenut.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by whydk on 11/16/2016.
 */
public class Trans2PinYinTest {

    @Test
    public void testConvert() throws Exception {
        Trans2PinYin trans2PinYin = Trans2PinYin.getInstance();

        assertEquals("bingbao",trans2PinYin.convertAll("冰雹"));
        assertEquals("leiyudafeng",trans2PinYin.convertAll("雷雨大风"));
        assertEquals("taifeng",trans2PinYin.convertAll("台风"));
        assertEquals("hongse",trans2PinYin.convertAll("红色"));
        assertEquals("lanse",trans2PinYin.convertAll("蓝色"));
        assertEquals("chengse",trans2PinYin.convertAll("橙色"));
        assertEquals("huangse",trans2PinYin.convertAll("黄色"));
    }
}