package com.icfcc.service.sys;

import com.icfcc.db.user.SmUserbaseinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2015/11/3.
 */
@Service
public class SmUserbaseinfoService {
    @Autowired
    private SmUserbaseinfoMapper smUserbaseinfoMapper;
}
