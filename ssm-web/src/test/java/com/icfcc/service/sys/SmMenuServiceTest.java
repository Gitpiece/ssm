package com.icfcc.service.sys;

import com.icfcc.TestBase;
import com.icfcc.db.sys.SmMenu;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2015/11/3.
 */
public class SmMenuServiceTest extends TestBase{

    @Autowired
    protected SmMenuServiceImpl smMenuServiceImpl;

    @Test
    public void test(){
        List<SmMenu> list = smMenuServiceImpl.getUserMenus(null);
        for (SmMenu smMenu:list){
            System.out.printf("menu %s \n",smMenu.getsName());
        }
    }

    @Test
    public void testAdd(){
        SmMenu smMenu = new SmMenu();
        smMenu.setiId(3);
        smMenu.setsName("用户管理");
        smMenu.setsHref("sm/user/users");
        smMenu.setiSort(2);
        smMenu.setTsCreate(new Date());
        smMenu.setTsLastupdate(new Date());
        smMenuServiceImpl.add(smMenu);
    }
}
