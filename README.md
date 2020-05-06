# CompanyApplication
In this project, I'm demonstrating the most interesting features of Spring Cloud Project for building microservice-based architecture like documentation base on Swagger, distributed tracing based on Zipkin, central configuration based on Spring Cloud Config, microservice communication based on Feign and others.

## Usage
In most cases you need to have Maven and JDK8+. The best way to run the sample applications is with IDEs like IntelliJ IDEA or Eclipse. You need to run PostgreSQL on your local machine and run RabbitMQ message broker. Also you can run above applications on Docker containers. All properties are removed from application.properties files. So you need clone https://github.com/endryu1994/CompanyAppConfigurationServer on your local machine and use these properties.

## Architecture
The microservice-based system consists of the following modules:
* **ConfigServer** - a module that provides a central place to manage external properties for microservices. Based on Spring Cloud Config.
* **EurekaServer** - a module that contains information about all microservices. Based on Spring Cloud Netflix Eureka.
* **ZuulGatewayServer** - a module that handles all the requests and does the dynamic routing of microservice applications. Based on Spring Cloud Netflix Zuul.
* **CustomerService** - a module that allows to perform CRUD operations on PostgreSQL repository of customers accounts.
* **EmployeeService** - a module that allows to perform CRUD operations on PostgreSQL repository of employees accounts.

The whole sample architecture is visible on the picture below. Each microservice has its own database as shown on the following picture.
