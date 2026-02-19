DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to create **Open API documentation** for **three** microservices with usage 
**Java** programming language and **Spring Cloud** and **Spring Boot 3** frameworks.

Additionally all services and their documentation are secured by **Keycloak**.

##### Services
This project consists of following applications:
* **Database**: SQL database - in this case type **MySql**
* **Second Service**: an application created in **Java** programming language with usage **Spring Boot** framework. It has connection with MySql database
* **First Service**: an application created in **Java** programming language with usage **Spring Boot** framework. It has connection with Second Service
* **Zero Service**: an application created in **Java** programming language with usage **Spring Boot** framework. It has connection with First Service
* **Keycloak**: identity and access management tool

##### Terminology
Terminology explanation:
* **Git**: tool for distributed version control
* **Maven**: tool for build automation
* **Java**: object-oriented programming language
* **Spring Boot**: framework for Java. It consists of: Spring + Container + Configuration
* **Spring Cloud**: Spring Cloud is a framework within the Spring ecosystem that provides tools for building distributed systems and microservices. It simplifies tasks like service discovery, configuration management, load balancing, circuit breakers, and distributed tracing, allowing developers to build scalable and resilient cloud-native applications.
* **Database**: A database is an organized collection of data that is stored and managed electronically, allowing for efficient retrieval, manipulation, and updating of information. It is typically managed by a database management system (DBMS).
* **MySql**: MySQL is an open-source relational database management system (RDBMS) that uses Structured Query Language (SQL) for managing and organizing data. It's widely used for web applications and is known for its speed, reliability, and ease of use.
* **Keycloak**: Keycloak is an open-source identity and access management solution that provides single sign-on, authentication, authorization, and user federation for modern applications and services.


USAGES
------

This project can be tested in following configurations:
* **Usage Manual**: infrastructure services are started as Docker containers. Application services are started manually in command line
* **Usage Docker Compose**: N/A
* **Usage Kubernetes (Kind)**: N/A


USAGE MANUAL
------------

> **Usage Manual** means that infrastructure services are started as Docker containers. Application services are started manually in command line.

> Please **clone/download** project, open **project's main folder** in your favorite **command line tool** and then **proceed with steps below**.

> **Prerequisites**:
* **Operating System** (tested on Windows 11)
* **Git** (tested on version 2.33.0.windows.2)
* **Docker** (tested on version 4.33.1)

##### Required steps:
1. Start **Docker** tool
1. In a first command line tool **start Docker containers** with `docker-compose -f .\docker-compose\infrastructure\docker-compose.yaml up -d --build`
1. In a second command line tool **start Second application** with `mvn -f ./springcloud-springboot3-swagger3-keycloak-services-three_SECOND spring-boot:run`
1. In a third command line tool **start First application** with `mvn -f ./springcloud-springboot3-swagger3-keycloak-services-three_FIRST spring-boot:run`
1. In a fourth command line tool **start Zero application** with `mvn -f ./springcloud-springboot3-swagger3-keycloak-services-three_ZERO spring-boot:run`
1. In any REST Client (e.g. Postman) visit **REST API** application with `http://localhost:7070/secured`
   * Authorization -> Type -> OAuth 2.0
   * Token Name: **Token**
   * Grant Type: **Authorization Code (With PKCE)
   * Callback URL: **http://localhost:7070/login/oauth2/code/helloworld-client**
   * Auth URL: **http://localhost:8080/realms/helloworld-realm/protocol/openid-connect/auth**
   * Access Token URL: **http://localhost:8080/realms/helloworld-realm/protocol/openid-connect/token**
   * Client ID: **helloworld-client**
   * Code Challenge Method: **SHA-256**
   * Click **Get New Access Token -> Register new user with credentials user/user -> Use Token**
   * Click **Send**
   * Expected text **Hello World, Secured!**
1. In any Internet Browser (e.g. Chrome) visit `http://localhost:7070/swagger-ui/index.html`
   * Log in with credentials user/user
   * Expected HTML page with First service documentation
   * Try out endpoint **secured** for First service
1. Clean up environment:
   * In the fourth command line tool **stop Zero application** with `ctrl + C`
   * In the third command line tool **stop First application** with `ctrl + C`
   * In the second command line tool **stop Second application** with `ctrl + C`
   * In the first command line tool **remove Docker containers** with `docker-compose -f .\docker-compose\infrastructure\docker-compose.yaml down --rmi all`
   * Stop **Docker** tool


USAGE DOCKER COMPOSE
--------------------

N/A


USAGE KUBERNETES (KIND)
---------------------------

N/A