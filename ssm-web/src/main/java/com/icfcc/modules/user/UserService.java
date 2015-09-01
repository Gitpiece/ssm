package com.icfcc.modules.user;

import com.icfcc.db.user.SmUserAuth;
import com.icfcc.db.user.SmUserAuthMapper;
import com.icfcc.db.user.SmUserbaseinfo;
import com.icfcc.db.user.SmUserbaseinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2015/8/27.
 */
@Service
public class UserService {

    @Autowired
    private SmUserbaseinfoMapper smUserbaseinfoMapper;

    @Autowired
    private SmUserAuthMapper smUserAuthMapper;

    /**
     * 通过用户名获取用户对象
     * @param userid 用户名
     * @return
     */
    public SmUserbaseinfo getUserByLoginName(Integer userid){
        return smUserbaseinfoMapper.selectByPrimaryKey(userid);
    }

    /**
     * 通过用户id获取用户授权对象
     * @param id 用户id
     * @return
     */
    public SmUserAuth getUserById(String id){

        return smUserAuthMapper.selectByAuthcode(id);
    }
}
