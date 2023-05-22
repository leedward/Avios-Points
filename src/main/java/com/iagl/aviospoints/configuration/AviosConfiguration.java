package com.iagl.aviospoints.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AviosConfiguration {

    // Bean for configuring CORS in the application
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Configure CORS to allow all origins (this would change for live release) and only POST method
                // on the avios endpoint (/avios)
                // It does not allow cookies or HTTP authentication to be included in CORS requests
                registry.addMapping("/avios")
                        .allowedOrigins("*")
                        .allowedMethods("POST")
                        .allowCredentials(false);
            }
        };
    }
}
