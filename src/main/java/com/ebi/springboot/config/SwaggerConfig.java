package com.ebi.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration.
 *
 * @author Yogesh Patil
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Swagger Docket bean.
     * <p>
     * We only need to supply controllers from the packages so that other default services such as actuator, health
     * doesn't show up on swagger.
     *
     * @return Swagger Docket bean
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select() //selecting `ApiSelectorBuilder`, which provides a way to control the endpoints exposed by Swagger
                .apis(RequestHandlerSelectors.basePackage("com.ebi.springboot.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}