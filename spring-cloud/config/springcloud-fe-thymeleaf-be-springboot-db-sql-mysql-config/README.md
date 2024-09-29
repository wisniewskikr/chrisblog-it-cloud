DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to create **chain of applications (microservices)** with a **config service** with usage **Java** programming language and **Spring Cloud** framework. Config service enables reading configuration properties stored in the Github.

This chain of services consists of following applications:
* **Database**: SQL database - in this case type **MySql**
* **Back-End**: an application created in **Java** programming language with usage **Spring Boot** framework
* **Front-End**: an application created in **Java** programming language with usage **Spring Boot** framework. **Thymeleaf** engine is used to display data
* **Eureka**: a tool which displays basic information - like status, port etc. - about microservices

Fe output consists of following elements:
* **Database Message**: the HTML displays the message stored in database. It's the simple text "Hello World!".
* **Back-End Port**: the HTML page displays the port of Back-End application.
* **Front-End Port**: the HTML page displays port of Front-End application.

##### Terminology
Terminology explanation:
* **Git**: tool for distributed version control
* **Maven**: tool for build automation
* **Java**: object-oriented programming language
* **Spring Boot**: framework for Java. It consists of: Spring + Container + Configuration
* **Database**: A database is an organized collection of data that is stored and managed electronically, allowing for efficient retrieval, manipulation, and updating of information. It is typically managed by a database management system (DBMS).
* **MySql**: MySQL is an open-source relational database management system (RDBMS) that uses Structured Query Language (SQL) for managing and organizing data. It's widely used for web applications and is known for its speed, reliability, and ease of use.
* **Back-End**: The back-end refers to the server-side part of a software application, responsible for managing the database, server logic, and application programming interface (API). It processes requests from the front-end (user interface), handles data storage, retrieval, and business logic, and sends the appropriate responses back to the front-end.
* **Front-End**: Front-end refers to the part of a website or application that users interact with directly. It includes the visual elements, layout, and design, typically built using HTML, CSS, and JavaScript. The front-end is responsible for the user experience (UX) and interface (UI) that allows users to navigate and interact with the system.
* **Thymeleaf**: Thymeleaf is a modern server-side Java template engine for Java-based web applications. It processes HTML, XML, JavaScript, CSS, and plain text, integrating with the Spring framework. It allows dynamic content rendering on the server while ensuring templates are HTML-compliant. Thymeleaf's key features include natural templates (which work as valid HTML even before rendering), easy integration with Spring MVC, and powerful expressions for iterating, conditional display, and data binding. It's often used to create dynamic web pages that combine static HTML with server-side logic in a clean and intuitive way.
* **Spring Cloud**: Spring Cloud is a framework within the Spring ecosystem that provides tools for building distributed systems and microservices. It simplifies tasks like service discovery, configuration management, load balancing, circuit breakers, and distributed tracing, allowing developers to build scalable and resilient cloud-native applications.
* **Microservices**: Microservices are a software architecture style where an application is built as a collection of small, independent services that communicate through APIs. Each service focuses on a specific business function, allowing for easier scaling, deployment, and maintenance.
* **Spring Cloud Config**: Spring Cloud Config is a tool within the Spring Cloud ecosystem that provides server-side and client-side support for externalized configuration in distributed systems. It allows you to store configuration data centrally (e.g., in a Git repository) and share it across multiple microservices, ensuring consistent and manageable configuration settings for your entire application environment.

##### Implementation
Implementation details for **Config Server**:
* Update file **pom.xml** with **spring-boot-starter-web** and **spring-cloud-config-server** dependencies
* Update start class with annotation **@EnableConfigServer**
* Update file **application.properties** with property **spring.cloud.config.server.git.uri** and **spring.cloud.config.server.git.default-label**

Implementation details for **Config Client**:
* Update file **pom.xml** with **spring-boot-starter-web** and **spring-cloud-starter-config** dependencies
* Update file **application.properties** with property **spring.config.import**

Implementation details for **Git Repository**:
* In Git repository create properties per client **<client-name>.properties** (for instance "be.properties", "fe.properties" etc.)


EXAMPLE
-------

![My Image](readme-images/image-01.png)


USAGE MANUAL
------------

> **Usage Manual** means that microservices are provided as **Java and Maven applications** and started **manually**. Database is provided as **Docker container**.

> Please **clone/download** project, open **project's main folder** in your favorite **command line tool** and then **proceed with steps below**. 

> Please be aware that following tools should be installed on your local PC:  
* **Operating System** (tested on Windows 11)
* **Java** (tested on version 17.0.5)
* **Maven** (tested on version 3.8.5)
* **Git** (tested on version 2.33.0.windows.2)
* **Docker** (tested on version 4.33.1 - it has to be up and running)

##### Required steps:
1. In the first command line tool **start Docker MySql container** with `docker run -d --name mysql-container -e MYSQL_ROOT_PASSWORD=my_secret_password -e MYSQL_DATABASE=database -e MYSQL_USER=admin -e MYSQL_PASSWORD=admin123 -p 3306:3306 mysql:5.7`
1. In the second command line tool **start Config application** with `mvn -f ./springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_SERVER spring-boot:run`
1. In the third command line tool **start Back-End application** with `mvn -f ./springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_BE spring-boot:run`
1. In the fourth command line tool **start Front-End application** with `mvn -f ./springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_FE spring-boot:run`
1. In a browser visit `http://localhost:8080`
   * Expected HTML page with **Database Message**, **Back-End Port** and **Front-End Port** 
1. Clean up environment 
     * In the fourth command line tool **stop Front-End application** with `ctrl + C`
     * In the third command line tool **stop Back-End application** with `ctrl + C`
     * In the second command line tool **stop Config application** with `ctrl + C`
     * In the first command line tool **stop and remove Docker MySql container** with `docker rm -f mysql-container`
     * In the first command line tool **remove Docker MySql image** with `docker rmi mysql:5.7`

##### Optional steps:
1. In a browser check Back-End application healthcheck with `http://localhost:8081/actuator/health`
1. In a browser check Back-End application API result with `http://localhost:8081/message/1`
1. In a browser check Front-End application healthcheck with `http://localhost:8080/actuator/health`
1. In a command line tool check list of Docker images with `docker images`
1. In a command line tool check list of all Docker containers with `docker ps -a`
1. In a command line tool check list of active Docker containers with `docker ps`


USAGE DOCKER
------------

> **Usage Docker** means that microservices and Database are provided as **Docker containers**. 

> Please **clone/download** project, open **project's main folder** in your favorite **command line tool** and then **proceed with steps below**.

> Please be aware that following tools should be installed on your local PC:  
* **Operating System** (tested on Windows 11)
* **Git** (tested on version 2.33.0.windows.2)
* **Docker** (tested on version 4.33.1 - it has to be up and running)

##### Required steps:
1. In a command line tool create **Docker Network** with `docker network create helloworld-network`
1. In a command line tool build and start **Docker container MySql** database with `docker run -d --name mysql-container -e MYSQL_ROOT_PASSWORD=my_secret_password -e MYSQL_DATABASE=database -e MYSQL_USER=admin -e MYSQL_PASSWORD=admin123 -p 3306:3306 --network helloworld-network mysql:5.7`
1. In a command line tool build **Docker image CONFIG** with `docker build -f springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_SERVER/Dockerfile -t config-image:0.0.1 ./springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_SERVER`
1. In a command line tool build and start **Docker container CONFIG** with `docker run -p 8888:8888 --name config-container --network helloworld-network -e spring.cloud.config.server.git.default-label=docker -d config-image:0.0.1`
1. In the file **springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_BE\src\main\resources\application.properties** comment property **Config Server - USAGE MANUAL** and uncomment property **Config Server - USAGE DOCKER**
1. In a command line tool build **Docker image BE** with `docker build -f springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_BE/Dockerfile -t be-image:0.0.1 ./springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_BE`
1. In a command line tool build and start **Docker container BE** with `docker run -p 8081:8081 --name be-container --network helloworld-network -d be-image:0.0.1`
1. In the file **springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_FE\src\main\resources\application.properties** comment property **Config Server - USAGE MANUAL** and uncomment property **Config Server - USAGE DOCKER**
1. In a command line tool build **Docker image FE** with `docker build -f springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_FE/Dockerfile -t fe-image:0.0.1 ./springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_FE`
1. In a command line tool build and start **Docker container FE** with `docker run -p 8080:8080 --name fe-container --network helloworld-network -d fe-image:0.0.1`
1. In a browser visit `http://localhost:8080`
   * Expected HTML page with **Database Message**, **Back-End Port** and **Front-End Port**  
1. Clean up environment 
     * In a command line tool stop and remove **BE Docker container** with `docker rm -f fe-container`
     * In a command line tool remove **BE Docker image** with `docker rmi fe-image:0.0.1`
     * In a command line tool stop and remove **FE Docker container** with `docker rm -f be-container`
     * In a command line tool remove **FE Docker image** with `docker rmi be-image:0.0.1`
     * In a command line tool stop and remove **CONFIG Docker container** with `docker rm -f config-container`
     * In a command line tool remove **CONFIG Docker image** with `docker rmi config-image:0.0.1`
     * In a command line tool stop and remove **Database Docker container** with `docker rm -f mysql-container`
     * In a command line tool remove **Database Docker image** with `docker rmi mysql:5.7`
     * In a command line tool remove **Docker Nerwork** with `docker network rm helloworld-network`

##### Optional steps:
1. In a browser check Back-End application healthcheck with `http://localhost:8081/actuator/health`
1. In a browser check Back-End application API result with `http://localhost:8081/message/1`
1. In a browser check Front-End application healthcheck with `http://localhost:8080/actuator/health`
1. In a command line tool check list of Docker images with `docker images`
1. In a command line tool check list of all Docker containers with `docker ps -a`
1. In a command line tool check list of active Docker containers with `docker ps`
1. In a command line tool check list of Docker nerworks with `docker network ls`
1. In a command line tool check BE container logs with `docker logs be-container`
1. In a command line tool check FE container logs with `docker logs fe-container`


USAGE DOCKER COMPOSE
--------------------

> **Usage Docker Compse** means that microservices and Database are provided as **Docker containers** definied in **Docker Compose** file. 

> Please **clone/download** project, open **project's main folder** in your favorite **command line tool** and then **proceed with steps below**.

> Please be aware that following tools should be installed on your local PC:  
* **Operating System** (tested on Windows 11)
* **Git** (tested on version 2.33.0.windows.2)
* **Docker** (tested on version 4.33.1 - it has to be up and running)

##### Required steps:
1. In the file **springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_BE\src\main\resources\application.properties** comment property **Config Server - USAGE MANUAL** and uncomment property **Config Server - USAGE DOCKER COMPOSE**
1. In the file **springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_FE\src\main\resources\application.properties** comment property **Config Server - USAGE MANUAL** and uncomment property **Config Server - USAGE DOCKER COMPOSE**
1. In a command line tool **start Docker containers** with `docker-compose up -d --build`
1. In a browser visit `http://localhost:8080`
   * Expected HTML page with **Database Message**, **Back-End Port** and **Front-End Port** 
1. Clean up environment 
     * In a command line tool **remove Docker containers** with `docker-compose down --rmi all`

##### Optional steps:
1. In a browser check Back-End application healthcheck with `http://localhost:8081/actuator/health`
1. In a browser check Back-End application API result with `http://localhost:8081/message/1`
1. In a browser check Front-End application healthcheck with `http://localhost:8080/actuator/health`
1. In a command line tool validate Docker Compose with `docker-compose config`
1. In a command line tool check list of Docker images with `docker images`
1. In a command line tool check list of all Docker containers with `docker ps -a`
1. In a command line tool check list of active Docker containers with `docker ps`
1. In a command line tool check list of Docker nerworks with `docker network ls`
1. In a command line tool check BE container logs with `docker logs be-container`
1. In a command line tool check FE container logs with `docker logs fe-container`


USAGE KUBERNETES (MINIKUBE)
---------------------------

> **Usage Kubernetes** means that microservices and Database are provided as **Docker containers** managed by **Kubernetes** type **Minikube**. 

> Please **clone/download** project, open **project's main folder** in your favorite **command line tool** and then **proceed with steps below**.

> Please be aware that following tools should be installed on your local PC:  
* **Operating System** (tested on Windows 11)
* **Git** (tested on version 2.33.0.windows.2)
* **Minikube** (tested on version 1.33.1)

##### Required steps:
1. In the first command line tool **with administrator privileges** start **Minikube** with `minikube start`
1. In the second command line tool **start Kubernetes Pods** with `kubectl apply -f kubernetes.yaml`
1. In the second command line tool **check status of Kubernetes Pods** with `kubectl get pods`
   * Expected mysql, be and fe as **READY 1/1** (it can take few minutes)
1. In the first command line tool **with administrator privileges** display FE service in a Browser with `minikube service fe-service`
   * Expected HTML page with **Database Message**, **Back-End Port** and **Front-End Port** 
1. Clean up environment 
     * In the second command line tool **remove Kubernetes Pods** with `kubectl delete -f kubernetes.yaml`
     * In the first command line tool **with administrator privileges** stop **Minikube** with `minikube stop`

##### Optional steps:
1. In a command line tool build Docker CONFIG image with `docker build -f springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_SERVER/Dockerfile -t wisniewskikr/springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_server:0.0.1 ./springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_SERVER`
1. In a command line tool push Docker CONFIG image to Docker Repository with `docker push wisniewskikr/springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_server:0.0.1`
1. In a command line tool build Docker BE image with `docker build -f springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_BE/Dockerfile -t wisniewskikr/springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_be:0.0.1 ./springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_BE`
1. In a command line tool push Docker BE image to Docker Repository with `docker push wisniewskikr/springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_be:0.0.1` 
1. In a command line tool build Docker FE image with `docker build -f springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_FE/Dockerfile -t wisniewskikr/springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_fe:0.0.1 ./springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_FE`
1. In a command line tool push Docker FE image to Docker Repository with `docker push wisniewskikr/springcloud-fe-thymeleaf-be-springboot-db-sql-mysql-config_fe:0.0.1` 
1. In the first command line tool with administrator privileges check status of Minikube with `minikube status`
1. In the first command line tool with administrator privileges check Docker images in Minikube with `minikube ssh docker images`
1. In the first command line tool with administrator privileges check Docker containers in Minikube with `minikube ssh docker ps`
1. In a command line tool check Kubernetes Deployments with `kubectl get deployments`
1. In a command line tool check Kubernetes Deployments details with **kubectl describe deployment {deployment-name}**
1. In a command line tool check Kubernetes Services with `kubectl get services`
1. In a command line tool check Kubernetes Services details with **kubectl describe service {service-name}**
1. In a command line tool check Kubernetes Pods with `kubectl get pods`
1. In a command line tool check Kubernetes Pods details with **kubectl describe pod {pod-name}**
1. In a command line tool check Kubernetes Pods logs with **kubectl log {pod-name}**