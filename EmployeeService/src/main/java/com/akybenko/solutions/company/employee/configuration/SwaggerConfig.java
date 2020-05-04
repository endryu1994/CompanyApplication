package com.akybenko.solutions.company.employee.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

import static com.akybenko.solutions.company.employee.constant.Constants.Swagger.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(parameters())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    private List<Parameter> parameters() {
        Parameter auth = new ParameterBuilder()
                .name(AUTHORIZATION_PARAMETER_NAME)
                .modelRef(new ModelRef(AUTHORIZATION_PARAMETER_TYPE))
                .parameterType(AUTHORIZATION_PARAMETER_PARAMETER_TYPE)
                .required(AUTHORIZATION_PARAMETER_REQUIRED)
                .build();
        return Collections.singletonList(auth);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(APP_INFO_TITLE)
                .description(APP_INFO_DESCRIPTION)
                .version(APP_INFO_VERSION)
                .termsOfServiceUrl(APP_INFO_TERMS_URL)
                .license(APP_INFO_LICENSE)
                .licenseUrl(APP_INFO_LICENSE_URL)
                .contact(new Contact(APP_INFO_CONTACT_NAME, APP_INFO_CONTACT_URL, APP_INFO_CONTACT_EMAIL))
                .build();
    }
}
