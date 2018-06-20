package com.makis.base.shrio;

import com.alibaba.fastjson.JSONObject;
import com.makis.base.misc.Tracker;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class FormAuthenticationCaptchaFilter extends FormAuthenticationFilter {

    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    public String getCaptchaParam() {
        return captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        // 用户名
        String username = getUsername(request);
        // 密码
        String password = getPassword(request);
        // 验证码
        //String captcha = getCaptcha(request);
        // 是否记住我
        boolean rememberMe = isRememberMe(request);
        // host
        String host = getHost(request);

        return new UsernamePasswordToken(username,
                password.toCharArray(), rememberMe, host);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        return super.onLoginFailure(token, e, request, response);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        return super.onLoginSuccess(token, subject, request, response);
    }

    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        super.setFailureAttribute(request, ae);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {

                return executeLogin(request, response);
            }
            else {
                //allow them to see the login page ;)
                return true;
            }
        }
        else {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            if (isAjax(httpRequest)) {
                returnJsonResult((HttpServletResponse) response);
            }
            else {
                saveRequestAndRedirectToLogin(request, response);
            }
            return false;
        }
    }

    /**
     * 拼接未登陆返回UI信息
     *
     * @param httpServletResponse
     */
    private void returnJsonResult(HttpServletResponse httpServletResponse) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("code", "-999");
            map.put("message", "未登录");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write(JSONObject.toJSONString(map));
        }
        catch (Exception e) {
            Tracker.error(e);
        }
    }

    /**
     * 判断ajax请求
     *
     * @param request
     * @return
     */
    private boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }
}