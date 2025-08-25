package com.table_tracking.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.http.CacheControl.maxAge;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns(
                        "http://localhost:8100",
                        "http://10.220.129.4:8100",
                        "http://10.198.190.4:8080",
                        "http://10.220.129.4:8080",
                        "http://10.238.98.40:8100",
                        "*" // allow all if needed
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("*")
                .allowCredentials(false) // use true if you need cookies/auth headers
                .maxAge(3600);
    }
}
