package nut.config;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by admin on 2015/7/16.
 */
public class GlobalTest {

    @Test
    public void test(){
        Assert.assertNotNull(Global.getConfig("web.staticFile"));
    }
}
