package com.akybenko.solutions.company.gateway.constant;

public class Constants {

    public interface JWT {
        String JWT_TOKEN_EXPIRATION_PROPERTY_NULL = "jwt.token.validity property must be configured";
        String JWT_TOKEN_SECRET_PROPERTY_NULL = "jwt.secret property must be configured";
    }

    public interface Swagger {
        String APP_INFO_TITLE = "Gateway API documentation";
        String APP_INFO_DESCRIPTION = "Gateway API documentation";
        String APP_INFO_VERSION = "2.0";
        String APP_INFO_TERMS_URL = "http://swagger.io/terms/";
        String APP_INFO_LICENSE = "Apache 2.0";
        String APP_INFO_LICENSE_URL = "http://www.apache.org/licenses/LICENSE-2.0.html";
        String APP_INFO_CONTACT_NAME = "Andrii Kybenko";
        String APP_INFO_CONTACT_URL = "";
        String APP_INFO_CONTACT_EMAIL = "endryukibenko@gmail.com";

        String BASE_PACKAGE = "com.akybenko.solutions.company.gateway.controller";

        String CUSTOMER_SERVICE_APPLICATION_NAME = "customer-service";
        String EMPLOYEE_SERVICE_APPLICATION_NAME = "employee-service";
        String GATEWAY_SERVICE_APPLICATION_NAME = "gateway-server";
        String CUSTOMER_SERVICE_API_DOCS_LOCATION = "/api/customer/v2/api-docs";
        String EMPLOYEE_SERVICE_API_DOCS_LOCATION = "/api/employee/v2/api-docs";
        String GATEWAY_SERVICE_API_DOCS_LOCATION = "/v2/api-docs";
        String SWAGGER_VERSION = "2.0";
    }

    public interface ExceptionMessages {
        String INVALID_OBJECT_EXCEPTION_MESSAGE = "Invalid object";
    }
}
