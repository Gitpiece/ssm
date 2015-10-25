package com.icfcc.uril;

import com.icfcc.util.StringUtils;

/**
 * Created by whydk on 2015/10/25.
 */
public class StringUtilsTest {

    public void test(){
        System.out.println(StringUtils.urlEndsWithAny("http://localhost:8081/ssm/static/adminlte/plugins/fonts/ionicons.ttf?123", "abc", "ttf"));
    }
}
