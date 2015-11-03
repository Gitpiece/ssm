package com.icfcc.modules.sys.c;

import com.icfcc.db.sys.SmMenu;
import com.icfcc.db.user.SmUserbaseinfo;
import com.icfcc.service.sys.SmMenuServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by admin on 2015/11/3.
 */
@Controller
@RequestMapping("/sm/menu")
public class SmMenuController {
    public final Log logger = LogFactory.getFactory().getInstance(this.getClass());
    @Autowired
    private SmMenuServiceImpl smMenuService;

    @RequestMapping(path = "/usermenus",method = RequestMethod.GET)
    public String usermenu(Model model) {
        List<SmMenu> smMenus = smMenuService.getUserMenus(new SmUserbaseinfo());
        model.addAttribute("data", smMenus);
        model.addAttribute("message", "Hello World!");
        return "sm/menu/usermenus";
    }


    @RequestMapping(path = "/menus",method = RequestMethod.GET)
    public String menu(Model model) {
        List<SmMenu> smMenus = smMenuService.getUserMenus(new SmUserbaseinfo());
        model.addAttribute("data", smMenus);
        model.addAttribute("message", "Hello World!");
        return "sm/menu/menus";
    }

}
