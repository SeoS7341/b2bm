package com.example.spring_boot_app.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ExceptionConfig implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    // HTTP 응답 코드에 대한 에러 처리
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
        factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
    }
}