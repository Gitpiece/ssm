package com.icfcc.modules.sys;

import com.icfcc.TestBase;
import com.icfcc.db.user.SmUserbaseinfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by admin on 2015/9/1.
 */
public class SystemServiceTest extends TestBase {
    @Autowired
    SystemService systemService;

    @Test
    public void testgetUserByAuthcode(){
        SmUserbaseinfo admin = systemService.getUserByAuthcode("admin");
        System.out.println(admin.getSmUserAuth().getsAuthpwd());
        Assert.assertNotNull(admin);
    }
}
