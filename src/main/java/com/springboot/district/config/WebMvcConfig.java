package com.springboot.district.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Cher on 2019-08-29
 */
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${swagger.enable}")
    private Boolean enable;

    /**
     * 添加静态资源路径
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // swagger文档页面路径
        if (this.enable) {
            registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");
        }
    }
}
