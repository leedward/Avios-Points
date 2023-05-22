package com.iagl.aviospoints.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {

    // Bean to configure Swagger for documenting the REST API
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                // Configure Swagger to scan for API endpoints in the specified base package
                .apis(RequestHandlerSelectors.basePackage("com.iagl.aviospoints.controller"))
                // Configure Swagger to document all paths
                .paths(PathSelectors.any())
                .build()
                // Set API info for Swagger documentation
                .apiInfo(apiEndPointsInfo());
    }

    // Method to configure the API info for Swagger documentation
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Avios Points API")
                .description("Avios Points API for calculating avios points")
                .version("1.0.0")
                .build();
    }

}
