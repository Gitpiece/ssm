package com.icfcc.modules.auth.security;

import com.icfcc.common.utils.StringManager;
import com.icfcc.common.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 表单验证（包含验证码）过滤类
 *
 * @author ThinkGem
 * @version 2014-5-19
 */
@Service
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {
    public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";
    //public static final String DEFAULT_MOBILE_PARAM = "mobileLogin";
    public static final String DEFAULT_MESSAGE_PARAM = "message";
    public static final String DEFAULT_ERROR_MESSAGE_PARAM = "error";
    private Log logger = LogFactory.getLog(FormAuthenticationFilter.class);
    private String captchaParam = DEFAULT_CAPTCHA_PARAM;
    //private String mobileLoginParam = DEFAULT_MOBILE_PARAM;
    private String messageParam = DEFAULT_MESSAGE_PARAM;
    private String errorMessageParam = DEFAULT_ERROR_MESSAGE_PARAM;
    StringManager sm = StringManager.getManager("com.icfcc.modules.auth.security");
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        if (password == null) {
            password = "";
        }
        boolean rememberMe = isRememberMe(request);
        String host = StringUtils.getRemoteAddr((HttpServletRequest) request);
        String captcha = getCaptcha(request);
//        boolean mobile = isMobileLogin(request);
        return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha);
    }

    public String getCaptchaParam() {
        return captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

//    public String getMobileLoginParam() {
//        return mobileLoginParam;
//    }

//    protected boolean isMobileLogin(ServletRequest request) {
//        return WebUtils.isTrue(request, getMobileLoginParam());
//    }

    public String getMessageParam() {
        return messageParam;
    }

    /**
     * 登录成功之后跳转URL
     */
    public String getSuccessUrl() {
        return super.getSuccessUrl();
    }

    @Override
    protected void issueSuccessRedirect(ServletRequest request,
                                        ServletResponse response) throws Exception {
//		Principal p = UserUtils.getPrincipal();
//		if (p != null && !p.isMobileLogin()){
        WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
//		}else{
//			super.issueSuccessRedirect(request, response);
//		}
    }

    /**
     * 登录失败调用事件
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token,
                                     AuthenticationException e, ServletRequest request, ServletResponse response) {
        String className = e.getClass().getName(), message ;
//        if (IncorrectCredentialsException.class.getName().equals(className)
//                || UnknownAccountException.class.getName().equals(className)) {
//            message = "用户或密码错误, 请重试.";
//        } else
        if (e.getMessage() != null && !StringUtils.endsWith(e.getMessage(), "null")) {
            message = e.getMessage();
        } else {
            message = sm.getString("system.error");//"系统出现点问题，请稍后再试！";
            logger.error(message + e.getMessage(), e);
        }
        request.setAttribute(getFailureKeyAttribute(), className);
        request.setAttribute(getMessageParam(), message);
        return true;
    }

}