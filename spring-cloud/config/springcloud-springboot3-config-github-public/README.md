EXAMPLE
-------

![My Image](readme-images/image-01.png)

![My Image](readme-images/image-02.png)

![My Image](readme-images/image-03.png)

![My Image](readme-images/image-04.png)


DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to create **Config Server** which reads properties from **public github**
repository and connect it with microservice with usage **Java** programming language and **Spring Cloud** framework. 

Config service enables reading configuration properties from different sources like: github, file etc. In this
case properties are read from public Github repository and sent to microservice. This microservice displays
these properties: common, public and secret.

##### Content
This project consists of following applications:
* **MS**: an application created in **Java** programming language with usage **Spring Boot** framework. It reads
properties from Config Server
* **Config**: a tool which reads configuration from external source (like Github etc.)

##### Terminology
Terminology explanation:
* **Git**: tool for distributed version control
* **Maven**: tool for build automation
* **Java**: object-oriented programming language
* **Spring Boot**: framework for Java. It consists of: Spring + Container + Configuration
* **Spring Cloud**: Spring Cloud is a framework within the Spring ecosystem that provides tools for building 
distributed systems and microservices. It simplifies tasks like service discovery, configuration management, 
load balancing, circuit breakers, and distributed tracing, allowing developers to build scalable 
and resilient cloud-native applications.
* **Microservices**: Microservices are a software architecture style where an application is built as a collection of 
small, independent services that communicate through APIs. Each service focuses on a specific business function, 
allowing for easier scaling, deployment, and maintenance.
* **Spring Cloud Config**: Spring Cloud Config is a tool within the Spring Cloud ecosystem that provides server-side 
and client-side support for externalized configuration in distributed systems. It allows you to store configuration 
data centrally (e.g., in a Git repository) and share it across multiple microservices, ensuring consistent and 
manageable configuration settings for your entire application environment.


USAGES
------

This project can be tested in following configurations:
* **Usage Manual**: custom services are started manually from command line.
* **Usage Docker Compose**: all services are started as Docker containers defined in docker-compose file
* **Usage Kubernetes (Kind)**: all services are started as Kubernetes pods.


USAGE DOCKER COMPOSE (RECOMMENDED)
----------------------------------

> **Usage Docker Compse** means that all services are started as Docker containers definied in "docker-compose.yaml" file.

> Please **clone/download** project, open **project's main folder** in your favorite **command line tool** and then **proceed with steps below**.

> Please be aware that following tools should be installed on your local PC:  
* **Operating System** (tested on Windows 11)
* **Git** (tested on version 2.33.0.windows.2)
* **Docker** (tested on version 4.33.1)

##### Required steps:
1. Start **Docker** tool
1. In a command line tool **start Docker containers** with `docker-compose up -d --build`
1. In a browser visit `http://localhost:8080`
   * Expected HTML page with **Database Message**, **Back-End Port** and **Front-End Port** 
1. Clean up environment 
     * In a command line tool **remove Docker containers** with `docker-compose down --rmi all`
     * Stop **Docker** tool

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


USAGE KUBERNETES (KIND) (RECOMMENDED)
-----------------------------------------

> **Usage Kubernetes** means that all services are started as Kubernetes pods. 

> Please **clone/download** project, open **project's main folder** in your favorite **command line tool** and then **proceed with steps below**.

> Please be aware that following tools should be installed on your local PC:  
* **Operating System** (tested on Windows 11)
* **Git** (tested on version 2.33.0.windows.2)
* **Docker** (tested on version 4.33.1)
* **Kind** (tested on version 0.26.0)

##### Required steps:
1. Start **Docker** tool
1. In the command line tool create and start cluster **Kind** with `kind create cluster --name helloworld`
1. In the command line tool **start Kubernetes Pods** with `kubectl apply -f kubernetes.yaml`
1. In the command line tool **check status of Kubernetes Pods** with `kubectl get pods`
   * Expected mysql, be and fe as **READY 1/1** (it can take few minutes)
1. In the command line tool **forward port of FE service** with `kubectl port-forward service/fe 8080:8080`
1. In a browser visit `http://localhost:8080`
   * Expected HTML page with **Database Message**, **Back-End Port** and **Front-End Port**
1. Clean up environment 
     * In the command line tool **remove Kubernetes Pods** with `kubectl delete -f kubernetes.yaml`
     * In the command line tool delete cluster **Kind** with `kind delete cluster --name helloworld`
     * Stop **Docker** tool

##### Optional steps:
1. In a command line tool build Docker CONFIG image with `docker build -f springcloud-springboot3-config-file_SERVER/Dockerfile -t wisniewskikr/springcloud-springboot3-config-file_server:0.0.1 ./springcloud-springboot3-config-file_SERVER`
1. In a command line tool push Docker CONFIG image to Docker Repository with `docker push wisniewskikr/springcloud-springboot3-config-file_server:0.0.1`
1. In a command line tool build Docker BE image with `docker build -f springcloud-springboot3-config-file_BE/Dockerfile -t wisniewskikr/springcloud-springboot3-config-file_be:0.0.1 ./springcloud-springboot3-config-file_BE`
1. In a command line tool push Docker BE image to Docker Repository with `docker push wisniewskikr/springcloud-springboot3-config-file_be:0.0.1` 
1. In a command line tool build Docker FE image with `docker build -f springcloud-springboot3-config-file_FE/Dockerfile -t wisniewskikr/springcloud-springboot3-config-file_fe:0.0.1 ./springcloud-springboot3-config-file_FE`
1. In a command line tool push Docker FE image to Docker Repository with `docker push wisniewskikr/springcloud-springboot3-config-file_fe:0.0.1` 
1. In the first command line tool with administrator privileges check clusers with `kind get clusters`
1. In a command line tool check Kubernetes Deployments with `kubectl get deployments`
1. In a command line tool check Kubernetes Deployments details with **kubectl describe deployment {deployment-name}**
1. In a command line tool check Kubernetes Services with `kubectl get services`
1. In a command line tool check Kubernetes Services details with **kubectl describe service {service-name}**
1. In a command line tool check Kubernetes Pods with `kubectl get pods`
1. In a command line tool check Kubernetes Pods details with **kubectl describe pod {pod-name}**
1. In a command line tool check Kubernetes Pods logs with **kubectl log {pod-name}**


USAGE MANUAL + DOCKER
---------------------

> **Usage Manual** means that custom services are started manually from command line. Other services (like Sql Databases, NoSql Databases etc.) are started as Docker containers.

> Please **clone/download** project, open **project's main folder** in your favorite **command line tool** and then **proceed with steps below**. 

> Please be aware that following tools should be installed on your local PC:  
* **Operating System** (tested on Windows 11)
* **Java** (tested on version 17.0.5)
* **Maven** (tested on version 3.8.5)
* **Git** (tested on version 2.33.0.windows.2)
* **Docker** (tested on version 4.33.1)

##### Required steps:
1. Start **Docker** tool
1. In the first command line tool **start Docker MySql container** with `docker run -d --name mysql-container -e MYSQL_ROOT_PASSWORD=my_secret_password -e MYSQL_DATABASE=database -e MYSQL_USER=admin -e MYSQL_PASSWORD=admin123 -p 3306:3306 mysql:5.7`
1. In the second command line tool **start Config application** with `mvn -f ./springcloud-springboot3-config-file_SERVER spring-boot:run`
1. In the third command line tool (it has to be **BASH** tool e.g. Git Bash) **start Back-End application** with `mvn -f ./springcloud-springboot3-config-file_BE spring-boot:run -Dspring-boot.run.arguments="--spring.config.import=configserver:http://localhost:8888 --spring.application.name=be"`
1. In the fourth command line tool (it has to be **BASH** tool e.g. Git Bash) **start Front-End application** with `mvn -f ./springcloud-springboot3-config-file_FE spring-boot:run -Dspring-boot.run.arguments="--spring.config.import=configserver:http://localhost:8888 --spring.application.name=fe"`
1. In a browser visit `http://localhost:8080`
   * Expected HTML page with **Database Message**, **Back-End Port** and **Front-End Port** 
1. Clean up environment 
     * In the fourth command line tool **stop Front-End application** with `ctrl + C`
     * In the third command line tool **stop Back-End application** with `ctrl + C`
     * In the second command line tool **stop Config application** with `ctrl + C`
     * In the first command line tool **stop and remove Docker MySql container** with `docker rm -f mysql-container`
     * In the first command line tool **remove Docker MySql image** with `docker rmi mysql:5.7`
     * Stop **Docker** tool

##### Optional steps:
1. In a browser check Back-End application healthcheck with `http://localhost:8081/actuator/health`
1. In a browser check Back-End application API result with `http://localhost:8081/message/1`
1. In a browser check Front-End application healthcheck with `http://localhost:8080/actuator/health`
1. In a command line tool check list of Docker images with `docker images`
1. In a command line tool check list of all Docker containers with `docker ps -a`
1. In a command line tool check list of active Docker containers with `docker ps`


USAGE DOCKER
------------

> **Usage Docker** means that all services are started as Docker containers. 

> Please **clone/download** project, open **project's main folder** in your favorite **command line tool** and then **proceed with steps below**.

> Please be aware that following tools should be installed on your local PC:  
* **Operating System** (tested on Windows 11)
* **Git** (tested on version 2.33.0.windows.2)
* **Docker** (tested on version 4.33.1)

##### Required steps:
1. Start **Docker** tool
1. In a command line tool create **Docker Network** with `docker network create helloworld-network`
1. In a command line tool build and start **Docker container MySql** database with `docker run -d --name mysql-container -e MYSQL_ROOT_PASSWORD=my_secret_password -e MYSQL_DATABASE=database -e MYSQL_USER=admin -e MYSQL_PASSWORD=admin123 -p 3306:3306 --network helloworld-network mysql:5.7`
1. In a command line tool build **Docker image CONFIG** with `docker build -f springcloud-springboot3-config-file_SERVER/Dockerfile -t config-image:0.0.1 ./springcloud-springboot3-config-file_SERVER`
1. In a command line tool build and start **Docker container CONFIG** with `docker run -p 8888:8888 --name config-container --network helloworld-network -e spring.cloud.config.server.native.search-locations=classpath:/configurations/docker -d config-image:0.0.1`
1. In a command line tool build **Docker image BE** with `docker build -f springcloud-springboot3-config-file_BE/Dockerfile -t be-image:0.0.1 ./springcloud-springboot3-config-file_BE`
1. In a command line tool build and start **Docker container BE** with `docker run -p 8081:8081 --name be-container -e spring.config.import=configserver:http://config-container:8888 -e spring.application.name=be --network helloworld-network -d be-image:0.0.1`
1. In a command line tool build **Docker image FE** with `docker build -f springcloud-springboot3-config-file_FE/Dockerfile -t fe-image:0.0.1 ./springcloud-springboot3-config-file_FE`
1. In a command line tool build and start **Docker container FE** with `docker run -p 8080:8080 --name fe-container -e spring.config.import=configserver:http://config-container:8888 -e spring.application.name=fe --network helloworld-network -d fe-image:0.0.1`
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
     * Stop **Docker** tool

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


IMPLEMENTATION
--------------

Implementation details for **Config Server**:
* Update file **pom.xml** with **spring-boot-starter-web** and **spring-cloud-config-server** dependencies
* Update start class with annotation **@EnableConfigServer**
* Update file **application.properties** with property **spring.cloud.config.server.git.uri** and **spring.cloud.config.server.git.default-label**

Implementation details for **Config Client**:
* Update file **pom.xml** with **spring-boot-starter-web** and **spring-cloud-starter-config** dependencies
* Update file **application.properties** with property **spring.config.import**

Implementation details for **Git Repository**:
* In Git repository create properties per client **<client-name>.properties** (for instance "be.properties", "fe.properties" etc.)