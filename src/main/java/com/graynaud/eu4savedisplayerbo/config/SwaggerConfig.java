package com.graynaud.eu4savedisplayerbo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api () {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Eu4 Save Displayer")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.graynaud.eu4savedisplayerbo.api"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Eu4 Save Displayer",
                "Eu4 Save Displayer swagger",
                "0.0.1",
                "None",
                new Contact("Gaétan RAYNAUD", "", ""),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList()
        );
    }
}
