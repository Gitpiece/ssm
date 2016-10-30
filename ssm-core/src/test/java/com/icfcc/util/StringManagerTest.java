package com.icfcc.util;


import org.testng.annotations.Test;

/**
 * Created by why on 10/30/2016.
 */
public class StringManagerTest {
    StringManager sm = StringManager.getManager(StringManagerTest.class);

    @Test
    public void testGetString() throws Exception {
        System.out.println(sm.getString("foobar"));
        System.out.println(sm.getString("error","系统"));
    }

    @Test
    public void testGetManager() throws Exception {

    }

    @Test
    public void testGetManager1() throws Exception {

    }
}