EXAMPLE
-------

![My Image](readme-images/image-01.png)

![My Image](readme-images/image-02.png)


DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to create **Integration Tests** with **MockServer** 
with usage **Java** programming language and **Spring Cloud** and **Spring Boot 3** frameworks.

##### Services
This project consists of following applications:
* **Second Service**: an application created in **Java** programming language with usage **Spring Boot** framework.
* **First Service**: an application created in **Java** programming language with usage **Spring Boot** framework. It has connection with Second Service

##### Terminology
Terminology explanation:
* **Git**: tool for distributed version control
* **Maven**: tool for build automation
* **Java**: object-oriented programming language
* **Spring Boot**: framework for Java. It consists of: Spring + Container + Configuration
* **Spring Cloud**: Spring Cloud is a framework within the Spring ecosystem that provides tools for building distributed systems and microservices. It simplifies tasks like service discovery, configuration management, load balancing, circuit breakers, and distributed tracing, allowing developers to build scalable and resilient cloud-native applications.
* **MockServer**: MockServer is a Java-based framework used to mock or simulate HTTP and HTTPS servers. It allows developers to create mock endpoints for testing and developing client-server interactions without needing the actual backend service.


USAGES
------

This project can be tested in following configurations:
* **Usage Manual**: integration tests are started manually in command line
* **Usage Docker Compose**: N/A
* **Usage Kubernetes (Kind)**: N/A


USAGE MANUAL
------------

> **Usage Manual** means that integration tests are started manually in command line.

> Please **clone/download** project, open **project's main folder** in your favorite **command line tool** and then **proceed with steps below**.

> **Prerequisites**:
* **Operating System** (tested on Windows 11)
* **Git** (tested on version 2.33.0.windows.2)

##### Required steps:
1. In a first command line tool **start Integration Tests** with `mvn clean test`
   * Expected integration tests are passed
1. Clean up environment
   * N/A


USAGE DOCKER COMPOSE
--------------------

N/A


USAGE KUBERNETES (KIND)
---------------------------

N/A