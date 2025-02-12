package com.example.spring_boot_app.config;

import org.hibernate.dialect.MariaDBDialect;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.spring_boot_app.common.CommonInterceptor;

@Configuration("webConfig")
public class WebConfig implements WebMvcConfigurer {

    // MariaDB Dialect 설정
    @Bean
    public MariaDBDialect mariaDBDialect() {
        return new MariaDBDialect();
    }

    // 정적 리소스 핸들러 설정
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    // 인터셉터 추가
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor())
                .addPathPatterns("/**");
    }

    @Bean
    public CommonInterceptor commonInterceptor() {
        return new CommonInterceptor();
    }

    // HTTP METHOD 사용을 위한 Filter 설정
    @Bean
    public FilterRegistrationBean<HiddenHttpMethodFilter> httpMethodFilter() {
        FilterRegistrationBean<HiddenHttpMethodFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new HiddenHttpMethodFilter());
        filter.setName("httpMethodFilter");
        filter.addUrlPatterns("/*");
        return filter;
    }
}