package com.icfcc.util;

import org.junit.Test;

/**
 * Created by whydk on 2015/10/25.
 */
public class StringUtilsTest {

    @Test
    public void test(){
        System.out.println(StringUtils.urlEndsWithAny("http://localhost:8081/ssm/static/adminlte/plugins/fonts/ionicons.ttf?123", "abc", "ttf"));
    }
}
