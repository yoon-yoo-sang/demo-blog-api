package com.example.demo_blog_api.config;

import com.example.demo_blog_api.auth.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {
//    @Bean
//    public FilterRegistrationBean jwtFilter() {
//        FilterRegistrationBean filter= new FilterRegistrationBean();
//        filter.setFilter(new JwtFilter());
//        filter.addUrlPatterns("/*");
//        return filter;
//    }
}
