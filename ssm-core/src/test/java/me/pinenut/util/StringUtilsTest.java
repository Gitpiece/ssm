package me.pinenut.util;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Created by WangHuanyu on 2015/10/25.
 */
@FixMethodOrder(MethodSorters.DEFAULT)
public class StringUtilsTest {

    @Test
    public void test1(){
        System.out.println(StringUtils.urlEndsWithAny("http://localhost:8081/ssm/static/adminlte/plugins/fonts/ionicons.ttf?123", "abc", "ttf"));
    }

    @Test
    public void testb(){
        System.out.println("testb");
    }

    @Test
    public void testa(){
        System.out.println("testa");
    }
}
