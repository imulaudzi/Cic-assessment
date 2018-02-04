package com.eoh.communication.Cic.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket cicApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(cicPaths()).build();
    }

    private Predicate<String> cicPaths() {
        return or(regex("/cic.*"),regex("/entity.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("EOH Cic API")
                .description("EOH Cic API reference for developers and testers")
                .version("1.0").contact(contact()).build();
    }

    private Contact contact(){
        return new Contact("Isaac Mulaudzi","","imulaudzi@gmail.com");
    }
}
