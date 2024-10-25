package org.zerock.apiserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zerock.apiserver.controller.formatter.LocalDateFormatter;

@Configuration
public class CustomServletConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        System.out.println("is addFormatter");
        registry.addFormatter(new LocalDateFormatter());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 어떤 경로에 적용할지
                .maxAge(500) // 요청 처리 시간 제한
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS") // 어떤 메소드의 요청을 허락할지
                .allowedOrigins("*"); // 어디로부터 들어오는 연결을 허락할지
    }
}
