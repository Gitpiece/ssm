package com.icfcc.service.sys;

import com.icfcc.db.sys.SmMenu;
import com.icfcc.db.sys.SmMenuMapper;
import com.icfcc.db.user.SmUserbaseinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2015/11/3.
 */
@Service
public class SmMenuServiceImpl {
    @Autowired
    private SmMenuMapper smMenuMapper;

    public List<SmMenu> getUserMenus(SmUserbaseinfo smUserbaseinfo){
        return smMenuMapper.selectAll();
    }

    public void add(SmMenu smMenu){
        smMenuMapper.insert(smMenu);
    }
}
