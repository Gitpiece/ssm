package com.icfcc.modules.sys.utils;

import com.icfcc.modules.auth.security.SystemAuthorizingRealm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Created by admin on 2015/8/27.
 */
public class
        UserUtils {
    private Log logger = LogFactory.getLog(getClass());
    public static Session getSession(){
        try{
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null){
                session = subject.getSession();
            }
            if (session != null){
                return session;
            }
//			subject.logout();
        }catch (InvalidSessionException e){

        }
        return null;
    }

    /**
     *
     */
    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    /**
     *
     */
    public static SystemAuthorizingRealm.Principal getPrincipal(){
        try{
            Subject subject = SecurityUtils.getSubject();
            SystemAuthorizingRealm.Principal principal = (SystemAuthorizingRealm.Principal)subject.getPrincipal();
            if (principal != null){
                return principal;
            }
//			subject.logout();
        }catch (UnavailableSecurityManagerException e) {

        }catch (InvalidSessionException e){

        }
        return null;
    }
//    /**
//     * ??????????????
//     * @param loginName
//     * @return ?????????null
//     */
//    public static SmUserbaseinfo getByLoginName(String loginName){
//        User user = (User)CacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
//        if (user == null){
//            user = userDao.getByLoginName(new User(null, loginName));
//            if (user == null){
//                return null;
//            }
//            user.setRoleList(roleDao.findList(new Role(user)));
//            CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
//            CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
//        }
//        return user;
//    }
}
