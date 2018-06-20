package com.makis.base.config;

import com.makis.base.shrio.FormAuthenticationCaptchaFilter;
import com.makis.base.shrio.Md5CredentialsMatcher;
import com.makis.base.shrio.SecurityRealm;
import com.google.common.collect.Maps;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;


import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.Map;

/**
 * Shiro配置类
 */
@Configuration
public class ShiroConfig {
    /**
     * FilterRegistrationBean
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistration.setEnabled(true);
        // 添加过滤规则
        filterRegistration.addUrlPatterns("/*");
        // 忽略过滤的格式
        filterRegistration.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/static/*");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        filterRegistration.setOrder(1);
        return filterRegistration;
    }

    /**
     * 过滤器配置，配置权限访问、匿名访问请求路劲
     *
     * @return
     * @see ShiroFilterFactoryBean
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/exam/user/login");
        bean.setUnauthorizedUrl("/error");
        bean.setSuccessUrl("/");

        Map<String, Filter> filters = Maps.newLinkedHashMap();
        filters.put("authc", new FormAuthenticationCaptchaFilter());
        bean.setFilters(filters);

        Map<String, String> chains = Maps.newLinkedHashMap();
        chains.put("/unauthorized", "anon");
        chains.put("/logout", "anon");
        chains.put("/dist/*", "anon");
        chains.put("/export/*", "anon");
        chains.put("/upload/*", "anon");
        chains.put("/servlet/*", "anon");
        chains.put("/css/**", "anon");
        chains.put("/resources/**", "anon");
        chains.put("/attachments/**", "anon");
        chains.put("/static/**", "anon");
        chains.put("/favicon.ico", "anon");
        chains.put("/login/*", "anon");
        chains.put("/api/v1/user/login", "anon");
        chains.put("/exam/user/register","anon");
        chains.put("/exam/exam/**","authc");
        chains.put("/exam/user/**","authc");
        chains.put("/api/v1/exam/**","authc");
        chains.put("/api/v1/course/joinCourse","authc");
        chains.put("/logout", "logout");
        chains.put("/plugins/**", "anon");
        chains.put("/**", "anon");
        bean.setFilterChainDefinitionMap(chains);

        return bean;
    }

    /**
     * @return
     * @see org.apache.shiro.mgt.SecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userRealm());

        return manager;
    }

    /**
     * @return
     * @see DefaultWebSessionManager
     */
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager defaultWebSessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //sessionManager.setCacheManager(cacheManager());
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setDeleteInvalidSessions(true);

        return sessionManager;
    }

    /**
     * @return
     * @see SecurityRealm--->AuthorizingRealm
     */
    @Bean
    @DependsOn(value = "lifecycleBeanPostProcessor")
    public SecurityRealm userRealm() {
        SecurityRealm userRealm = new SecurityRealm();
        userRealm.setCredentialsMatcher(credentialsMatcher());

        return userRealm;
    }

    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new Md5CredentialsMatcher();
    }


    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}