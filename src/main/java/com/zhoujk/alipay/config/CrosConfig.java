package com.zhoujk.alipay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : zhoujiankang
 * @Desc:
 * @since : 2022/5/3 23:03
 */

@Configuration
public class CrosConfig implements WebMvcConfigurer
{

    public static final  String[] METHODS ={"GET","POST","PUT","PATCH","DELETE"};

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowedMethods(METHODS)
                .allowCredentials(true)
                .maxAge(6000);
    }
}
