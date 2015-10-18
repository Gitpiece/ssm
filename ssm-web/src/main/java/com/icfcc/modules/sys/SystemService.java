package com.icfcc.modules.sys;

import com.icfcc.db.user.SmUserAuth;
import com.icfcc.db.user.SmUserAuthMapper;
import com.icfcc.db.user.SmUserbaseinfo;
import com.icfcc.db.user.SmUserbaseinfoMapper;
import com.icfcc.modules.user.UserService;
import com.icfcc.security.session.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.UserMapper;

/**
 * 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * Created by admin on 2015/8/17.
 */
@Service
@Transactional(readOnly = true)
public class SystemService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SmUserbaseinfoMapper smUserbaseinfoMapper;
    @Autowired
    private SmUserAuthMapper smUserAuthMapper;
    @Autowired
    private SessionDAO sessionDao;

    @Autowired
    private UserService userService;

    public SessionDAO getSessionDao() {
        return sessionDao;
    }

    /**
     * 根据登录名获取用户
     *
     * @param authcode
     * @return
     */
    public SmUserbaseinfo getUserByAuthcode(String authcode) {
        SmUserAuth smUserAuth = smUserAuthMapper.selectByAuthcode(authcode);
        SmUserbaseinfo userbaseinfo = null;
        if(smUserAuth != null){
            userbaseinfo = userService.getUserByLoginName(smUserAuth.getiUserid());
            userbaseinfo.setSmUserAuth(smUserAuth);
        }
        return userbaseinfo;
    }
}
