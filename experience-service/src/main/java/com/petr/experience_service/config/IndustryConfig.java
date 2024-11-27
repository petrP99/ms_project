package com.petr.experience_service.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static java.util.Objects.nonNull;

@Configuration
public class IndustryConfig {

    private static final String AUTHORIZATION = "Authorization";

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder getFeignErrorDecoder() {
        return new FeignErrorDecoder();
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return (requestTemplate) -> {
            ServletRequestAttributes attributes =
                    (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (nonNull(attributes)) {
                HttpServletRequest request = attributes.getRequest();
                String authorizationHeader = request.getHeader(AUTHORIZATION);

                if (nonNull(authorizationHeader)) {
                    requestTemplate.header(AUTHORIZATION, authorizationHeader);
                }
            }
        };
    }

}
