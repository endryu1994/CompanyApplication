package com.akybenko.solutions.company.gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.akybenko.solutions.company.gateway.constant.Constants.Swagger.*;

@Component
@Primary
@EnableSwagger2
public class SwaggerConfig implements SwaggerResourcesProvider {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        resources.add(getResource(CUSTOMER_SERVICE_APPLICATION_NAME, CUSTOMER_SERVICE_API_DOCS_LOCATION));
        resources.add(getResource(EMPLOYEE_SERVICE_APPLICATION_NAME, EMPLOYEE_SERVICE_API_DOCS_LOCATION));
        resources.add(getResource(GATEWAY_SERVICE_APPLICATION_NAME, GATEWAY_SERVICE_API_DOCS_LOCATION));
        return resources;
    }

    private SwaggerResource getResource(String name, String location) {
        SwaggerResource resource = new SwaggerResource();
        resource.setName(name);
        resource.setLocation(location);
        resource.setSwaggerVersion(SWAGGER_VERSION);
        return resource;
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
