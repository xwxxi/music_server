package com.hunk.music.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 拦截所有的请求
                .allowedOriginPatterns("*")  // 可跨域的域名，可以为 *
                .allowCredentials(true)
                .allowedMethods("*")   // 允许跨域的方法，可以单独配置
                .allowedHeaders("*");  // 允许跨域的请求头，可以单独配置
    }
}
