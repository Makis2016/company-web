package com.makis.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * description：MVC配置视图解析器以及静态资源
 *
 * @author chenfuqian
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Value("${resources.url}")
    private String resourcesUrl;

    @Value("${resources.path}")
    private String resouresPath;

    @Value("${upload.url}")
    private String uploadUrl;

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcesUrl + "/**").addResourceLocations("file:" + resouresPath + "/");
        registry.addResourceHandler(uploadUrl + "/**").addResourceLocations("file:" + uploadPath + "/");
    }
}
