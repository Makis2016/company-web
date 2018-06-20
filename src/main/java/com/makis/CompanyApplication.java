package com.makis;

import com.makis.base.servlet.CaptchaServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableTransactionManagement
public class CompanyApplication implements ServletContextInitializer {
    public static void main( String[] args )
    {
        SpringApplication.run(CompanyApplication.class,args);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new CaptchaServlet(), "/servlet/captchaCode");
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.getSessionCookieConfig().setName("examWeb");
    }
}