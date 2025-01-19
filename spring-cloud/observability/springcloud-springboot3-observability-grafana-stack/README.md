DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to create **chain of applications (microservices)** with **observability** type **Grafana Stack (Loki + Prometeus + Tempo)** with usage **Java** programming language and **Spring Cloud** and **Spring Boot 3** frameworks. Grafana Stack enables observing many microservices (Logs, Metrics and Traces) in one central Grafana dashboard.

##### Services
This chain of services consists of following applications:
* **Database**: SQL database - in this case type **MySql**
* **Back-End**: an application created in **Java** programming language with usage **Spring Boot** framework
* **Front-End**: an application created in **Java** programming language with usage **Spring Boot** framework. **Thymeleaf** engine is used to display data
* **Loki**: it enables collecting logs from many microservices
* **Prometeus**" it enables collecting metrics from many microservices
* **Tempo**: it enables collecting traces from many microservices
* **Grafana**: it enables displaying logs, metrics and traces from Loki, Prometeus and Tempo tools 

##### Inputs
Inputs to custom services:
* **FE**: http call from any browser

Inputs to other services:
* **Grafana**: http call from any browser

##### Outputs
Output of custom services:
* **FE**: Front-End service connects with Back-End service which connects with database. Output displayed by FE:
   * **Database Message**: the HTML displays the message stored in database. It's the simple text "Hello World!".
   * **Back-End Port**: the HTML page displays the port of Back-End application.
   * **Front-End Port**: the HTML page displays port of Front-End application.

Output of other services:
* **Grafana**: this dashboard contains following data: 
   * **Logs**: logs of all custom services 
   * **Metrics**: metrics of all custom services
   * **Traces**: traces of all custom services

##### Terminology
Terminology explanation:
* **Git**: tool for distributed version control
* **Maven**: tool for build automation
* **Java**: object-oriented programming language
* **Spring Boot**: framework for Java. It consists of: Spring + Container + Configuration
* **Spring Cloud**: Spring Cloud is a framework within the Spring ecosystem that provides tools for building distributed systems and microservices. It simplifies tasks like service discovery, configuration management, load balancing, circuit breakers, and distributed tracing, allowing developers to build scalable and resilient cloud-native applications.
* **Database**: A database is an organized collection of data that is stored and managed electronically, allowing for efficient retrieval, manipulation, and updating of information. It is typically managed by a database management system (DBMS).
* **MySql**: MySQL is an open-source relational database management system (RDBMS) that uses Structured Query Language (SQL) for managing and organizing data. It's widely used for web applications and is known for its speed, reliability, and ease of use.
* **Back-End**: The back-end refers to the server-side part of a software application, responsible for managing the database, server logic, and application programming interface (API). It processes requests from the front-end (user interface), handles data storage, retrieval, and business logic, and sends the appropriate responses back to the front-end.
* **Front-End**: Front-end refers to the part of a website or application that users interact with directly. It includes the visual elements, layout, and design, typically built using HTML, CSS, and JavaScript. The front-end is responsible for the user experience (UX) and interface (UI) that allows users to navigate and interact with the system.
* **Loki**: Grafana Loki is a log aggregation system designed to store, query, and visualize logs efficiently. Unlike traditional log management tools, Loki is lightweight and cost-effective, as it indexes logs by labels (like Kubernetes pod or service name) rather than indexing the entire log content. It's tightly integrated with Grafana, enabling unified metrics and log analysis within the same interface, making it ideal for cloud-native environments.
* **Prometeus**: Prometheus is an open-source monitoring and alerting toolkit designed for collecting, storing, and querying time-series data, primarily metrics from servers, applications, and services.
* **Tempo**: Grafana Tempo is a highly scalable, distributed tracing backend used to collect, store, and query traces from applications. It supports open standards like OpenTelemetry, integrates seamlessly with Grafana for visualization, and is optimized for low-cost storage by only indexing trace IDs while keeping the rest of the trace data in object storage.
* **Grafana**: Grafana is an open-source visualization and analytics platform used for monitoring, querying, and visualizing metrics from various data sources in customizable dashboards.


EXAMPLE
-------

![My Image](readme-images/image-01.png)

![My Image](readme-images/image-02.png)

![My Image](readme-images/image-03.png)

![My Image](readme-images/image-04.png)

![My Image](readme-images/image-05.png)


USAGES
------

You can test this project in many configurations. Please choose the configuration that suits you best. Configurations:
* **Usage Docker Compose (Recommended)**: all services are started as Docker containers definied in "docker-compose/with-custom-services/docker-compose.yaml" file.
* **Usage Kubernetes (Minikube) (Recommended)**: all services are started as Kubernetes pods.
* **Usage Manual + Docker**: custom services are started manually from command line. Other services (like Sql Databases, NoSql Databases etc.) are started as Docker containers.
* **Usage Manual + Docker Compose**: custom services are started manually from command line. Other services (like Sql Databases, NoSql Databases etc.) are started as Docker containers definied in "docker-compose/without-custom-services/docker-compose.yaml" file.
* **Usage Docker**: all services are started as Docker containers.


USAGE DOCKER COMPOSE (RECOMMENDED)
---------------------------------

> **Usage Docker Compse** means all services are started as Docker containers definied in "docker-compose/with-custom-services/docker-compose.yaml" file.

> Please **clone/download** project, open **project's main folder** in your favorite **command line tool** and then **proceed with steps below**.

> **Prerequisites**:  
* **Operating System** (tested on Windows 11)
* **Git** (tested on version 2.33.0.windows.2)
* **Docker** (tested on version 4.33.1)

##### Required steps:
1. Start **Docker** tool
1. In a command line tool **start Docker containers** with `docker-compose -f .\docker-compose\with-custom-services\docker-compose.yaml up -d --build`
1. In a browser visit `http://localhost:8080`
   * Expected HTML page with **Database Message**, **Back-End Port** and **Front-End Port** 
1. In a browser visit `http://localhost:3000`
   * Expected HTML page with **Grafana dashboard** (please check section **EXAMPLE**).
1. Clean up environment 
     * In a command line tool **remove Docker containers** with `docker-compose -f .\docker-compose\with-custom-services\docker-compose.yaml down --rmi all`
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


USAGE KUBERNETES (MINIKUBE) (RECOMMENDED)
----------------------------------------

> **Usage Kubernetes** means that Back-End, Front-End services and Database are provided as **Docker containers** managed by **Kubernetes** type **Minikube**. 

> Please **clone/download** project, open **project's main folder** in your favorite **command line tool** and then **proceed with steps below**.

> **Prerequisites**:  
* **Operating System** (tested on Windows 11)
* **Git** (tested on version 2.33.0.windows.2)
* **Minikube** (tested on version 1.33.1)

##### Required steps:
1. In the first command line tool **with administrator privileges** start **Minikube** with `minikube start --cpus 4 --memory 7000`
1. In the second command line tool **start Kubernetes Pods** with `kubectl apply -f ./k8s/kubernetes.yaml`
1. In the second command line tool **check status of Kubernetes Pods** with `kubectl get pods`
   * Expected mysql, be and fe as **READY 1/1** (it can take few minutes)
1. In the first command line tool **with administrator privileges** display FE service in a Browser with `minikube service fe-service`
   * Expected HTML page with **Database Message**, **Back-End Port** and **Front-End Port** 
1. In the first command line tool **with administrator privileges** display Grafana service in a Browser with `minikube service grafana`
   * Expected HTML page with **Grafana dashboard** (please check section **EXAMPLE**).
1. Clean up environment 
     * In the second command line tool **remove Kubernetes Pods** with `kubectl delete -f ./k8s/kubernetes.yaml`
     * In the first command line tool **with administrator privileges** stop **Minikube** with `minikube stop`

##### Optional steps:
1. In a command line tool build Docker BE image with `docker build -f springcloud-springboot3-observability-grafana-stack_BE/Dockerfile -t wisniewskikr/springcloud-springboot3-observability-grafana-stack_be:0.0.1 ./springcloud-springboot3-observability-grafana-stack_BE`
1. In a command line tool push Docker BE image to Docker Repository with `docker push wisniewskikr/springcloud-springboot3-observability-grafana-stack_be:0.0.1` 
1. In a command line tool build Docker FE image with `docker build -f springcloud-springboot3-observability-grafana-stack_FE/Dockerfile -t wisniewskikr/springcloud-springboot3-observability-grafana-stack_fe:0.0.1 ./springcloud-springboot3-observability-grafana-stack_FE`
1. In a command line tool push Docker FE image to Docker Repository with `docker push wisniewskikr/springcloud-springboot3-observability-grafana-stack_fe:0.0.1` 
1. In the first command line tool with administrator privileges check status of Minikube with `minikube status`
1. In the first command line tool with administrator privileges check Docker images in Minikube with `minikube ssh docker images`
1. In the first command line tool with administrator privileges check Docker containers in Minikube with `minikube ssh docker ps`
1. In a command line tool check Kubernetes Deployments with `kubectl get deployments`
1. In a command line tool check Kubernetes Deployments details with **kubectl describe deployment {deployment-name}**
1. In a command line tool check Kubernetes Services with `kubectl get services`
1. In a command line tool check Kubernetes Services details with **kubectl describe service {service-name}**
1. In a command line tool check Kubernetes Pods with `kubectl get pods`
1. In a command line tool check Kubernetes Pods details with **kubectl describe pod {pod-name}**
1. In a command line tool check Kubernetes Pods logs with **kubectl logs {pod-name}**


USAGE MANUAL + DOCKER
---------------------

> **Usage Manual + Docker** means that custom services are started manually from command line. Other services (like Sql Databases, NoSql Databases etc.) are started as Docker containers.

> Please **clone/download** project, open **project's main folder** in your favorite **command line tool** and then **proceed with steps below**. 

> **Prerequisites**:  
* **Operating System** (tested on Windows 11)
* **Java** (tested on version 17.0.5)
* **Maven** (tested on version 3.8.5)
* **Git** (tested on version 2.33.0.windows.2)
* **Docker** (tested on version 4.33.1)

##### Required steps:
1. Start **Docker** tool
1. In the first command line tool **create network** with `docker network create helloworld-network`
1. In the first command line tool **start Tempo container** with `docker run -d --name tempo -p 3110:3100 -p 9411:9411 --network helloworld-network -v ${pwd}/docker/tempo/tempo.yml:/etc/tempo.yaml:ro -v ${pwd}/docker/tempo/tempo-data:/tmp/tempo grafana/tempo:2.2.2 --config.file /etc/tempo.yaml`
1. In the first command line tool **start Loki container** with `docker run -d -p 3100:3100 --network helloworld-network --name loki grafana/loki:3.3.2 --config.file /etc/loki/local-config.yaml`
1. In the first command line tool **start Prometheus container** with `docker run -d --name prometheus -p 9090:9090 --network helloworld-network -v ${pwd}/docker/prometheus/prometheus-localhost.yml:/etc/prometheus/prometheus.yml:ro prom/prometheus:v2.46.0 --enable-feature exemplar-storage --config.file /etc/prometheus/prometheus.yml`
1. In the first command line tool **start Grafana container** with `docker run -d --name grafana -p 3000:3000 --network helloworld-network -v ${pwd}/docker/grafana:/etc/grafana/provisioning/datasources:ro -e GF_AUTH_ANONYMOUS_ENABLED=true -e GF_AUTH_ANONYMOUS_ORG_ROLE=Admin -e GF_AUTH_DISABLE_LOGIN_FORM=true grafana/grafana:10.1.0`
1. In the first command line tool **start Docker MySql container** with `docker run -d --name mysql-container -e MYSQL_ROOT_PASSWORD=my_secret_password -e MYSQL_DATABASE=database -e MYSQL_USER=admin -e MYSQL_PASSWORD=admin123 -p 3306:3306 mysql:5.7`
1. In the second command line tool **start Back-End application** with `mvn -f ./springcloud-springboot3-observability-grafana-stack_BE spring-boot:run`
1. In the third command line tool **start Front-End application** with `mvn -f ./springcloud-springboot3-observability-grafana-stack_FE spring-boot:run`
1. In a browser visit `http://localhost:8080`
   * Expected HTML page with **Database Message**, **Back-End Port** and **Front-End Port** 
1. In a browser visit `http://localhost:3000`
   * Expected HTML page with **Grafana dashboard** (please check section **EXAMPLE**).
1. Clean up environment 
     * In the third command line tool **stop Front-End application** with `ctrl + C`
     * In the second command line tool **stop Back-End application** with `ctrl + C`
     * In the first command line tool **stop and remove Docker MySql container** with `docker rm -f mysql-container`
     * In the first command line tool **remove Docker MySql image** with `docker rmi mysql:5.7`
     * In the first command line tool **stop and remove Grafana container** with `docker rm -f grafana`
     * In the first command line tool **remove Grafana image** with `docker rmi grafana/grafana:10.1.0`
     * In the first command line tool **stop and remove Prometheus container** with `docker rm -f prometheus`
     * In the first command line tool **remove Prometheus image** with `docker rmi prom/prometheus:v2.46.0` 
     * In the first command line tool **stop and remove Loki container** with `docker rm -f loki`
     * In the first command line tool **remove Loki image** with `docker rmi grafana/loki:3.3.2`
     * In the first command line tool **stop and remove Tempo container** with `docker rm -f tempo`
     * In the first command line tool **remove Tempo image** with `docker rmi grafana/tempo:2.2.2`
     * In a command line tool remove **Docker Nerwork** with `docker network rm helloworld-network` 
     * Stop **Docker** tool

##### Optional steps:
1. In a browser check Back-End application healthcheck with `http://localhost:8081/actuator/health`
1. In a browser check Back-End application API result with `http://localhost:8081/message/1`
1. In a browser check Front-End application healthcheck with `http://localhost:8080/actuator/health`
1. In a command line tool check list of Docker images with `docker images`
1. In a command line tool check list of all Docker containers with `docker ps -a`
1. In a command line tool check list of active Docker containers with `docker ps`


USAGE MANUAL + DOCKER COMPOSE
-----------------------------

> **Usage Manual + Docker Compse** means that custom services are started manually from command line. Other services (like Sql Databases, NoSql Databases etc.) are started as Docker containers definied in "docker-compose/without-custom-services/docker-compose.yaml" file.

> Please **clone/download** project, open **project's main folder** in your favorite **command line tool** and then **proceed with steps below**.

> **Prerequisites**:  
* **Operating System** (tested on Windows 11)
* **Git** (tested on version 2.33.0.windows.2)
* **Docker** (tested on version 4.33.1)

##### Required steps:
1. Start **Docker** tool
1. In the first command line tool **start Docker containers** with `docker-compose -f .\docker-compose\without-custom-services\docker-compose.yaml up -d`
1. In the second command line tool **start Back-End application** with `mvn -f ./springcloud-springboot3-observability-grafana-stack_BE spring-boot:run`
1. In the third command line tool **start Front-End application** with `mvn -f ./springcloud-springboot3-observability-grafana-stack_FE spring-boot:run`
1. In a browser visit `http://localhost:8080`
   * Expected HTML page with **Database Message**, **Back-End Port** and **Front-End Port** 
1. In a browser visit `http://localhost:3000`
   * Expected HTML page with **Grafana dashboard** (please check section **EXAMPLE**).
1. Clean up environment 
     * In the third command line tool **stop Front-End application** with `ctrl + C`
     * In the second command line tool **stop Back-End application** with `ctrl + C`
     * In the first command line tool **remove Docker containers** with `docker-compose -f .\docker-compose\without-custom-services\docker-compose.yaml down --rmi all`
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


USAGE DOCKER
------------

> **Usage Docker** means that all services are started as Docker containers. 

> Please **clone/download** project, open **project's main folder** in your favorite **command line tool** and then **proceed with steps below**.

> **Prerequisites**:  
* **Operating System** (tested on Windows 11)
* **Git** (tested on version 2.33.0.windows.2)
* **Docker** (tested on version 4.33.1)

##### Required steps:
1. Start **Docker** tool
1. In the first command line tool **create network** with `docker network create helloworld-network`
1. In the first command line tool **start Tempo container** with `docker run -d --name tempo -p 3110:3100 -p 9411:9411 --network helloworld-network -v ${pwd}/docker/tempo/tempo.yml:/etc/tempo.yaml:ro -v ${pwd}/docker/tempo/tempo-data:/tmp/tempo grafana/tempo:2.2.2 --config.file /etc/tempo.yaml`
1. In the first command line tool **start Loki container** with `docker run -d -p 3100:3100 --network helloworld-network --name loki grafana/loki:3.3.2 --config.file /etc/loki/local-config.yaml`
1. In the first command line tool **start Prometheus container** with `docker run -d --name prometheus -p 9090:9090 --network helloworld-network -v ${pwd}/docker/prometheus/prometheus-localhost.yml:/etc/prometheus/prometheus.yml:ro prom/prometheus:v2.46.0 --enable-feature exemplar-storage --config.file /etc/prometheus/prometheus.yml`
1. In the first command line tool **start Grafana container** with `docker run -d --name grafana -p 3000:3000 --network helloworld-network -v ${pwd}/docker/grafana:/etc/grafana/provisioning/datasources:ro -e GF_AUTH_ANONYMOUS_ENABLED=true -e GF_AUTH_ANONYMOUS_ORG_ROLE=Admin -e GF_AUTH_DISABLE_LOGIN_FORM=true grafana/grafana:10.1.0`
1. In the first command line tool **start Docker MySql container** with `docker run -d --name mysql-container --network helloworld-network -e MYSQL_ROOT_PASSWORD=my_secret_password -e MYSQL_DATABASE=database -e MYSQL_USER=admin -e MYSQL_PASSWORD=admin123 -p 3306:3306 mysql:5.7`
1. In a command line tool build **Docker image BE** with `docker build -f springcloud-springboot3-observability-grafana-stack_BE/Dockerfile -t be-image:0.0.1 ./springcloud-springboot3-observability-grafana-stack_BE`
1. In a command line tool build and start **Docker container BE** with `docker run -p 8081:8081 --name be-container --network helloworld-network -e spring.datasource.url=jdbc:mysql://mysql-container:3306/database -e management.zipkin.tracing.endpoint=http://tempo:9411/api/v2/spans -e LOKI_URL=http://loki:3100 -d be-image:0.0.1`
1. In a command line tool build **Docker image FE** with `docker build -f springcloud-springboot3-observability-grafana-stack_FE/Dockerfile -t fe-image:0.0.1 ./springcloud-springboot3-observability-grafana-stack_FE`
1. In a command line tool build and start **Docker container FE** with `docker run -p 8080:8080 --name fe-container --network helloworld-network -e api.url=http://be-container:8081 -e management.zipkin.tracing.endpoint=http://tempo:9411/api/v2/spans -e LOKI_URL=http://loki:3100 -d fe-image:0.0.1`
1. In a browser visit `http://localhost:8080`
   * Expected HTML page with **Database Message**, **Back-End Port** and **Front-End Port** 
1. In a browser visit `http://localhost:3000`
   * Expected HTML page with **Grafana dashboard** (please check section **EXAMPLE**).
1. Clean up environment 
     * In a command line tool stop and remove **FE Docker container** with `docker rm -f fe-container`
     * In a command line tool remove **FE Docker image** with `docker rmi fe-image:0.0.1`
     * In a command line tool stop and remove **BE Docker container** with `docker rm -f be-container`
     * In a command line tool remove **BE Docker image** with `docker rmi be-image:0.0.1`
     * In a command line tool **stop and remove Docker MySql container** with `docker rm -f mysql-container`
     * In a command line tool **remove Docker MySql image** with `docker rmi mysql:5.7`
     * In a command line tool **stop and remove Grafana container** with `docker rm -f grafana`
     * In a command line tool **remove Grafana image** with `docker rmi grafana/grafana:10.1.0`
     * In a command line tool **stop and remove Prometheus container** with `docker rm -f prometheus`
     * In a command line tool **remove Prometheus image** with `docker rmi prom/prometheus:v2.46.0` 
     * In a command line tool **stop and remove Loki container** with `docker rm -f loki`
     * In a command line tool **remove Loki image** with `docker rmi grafana/loki:3.3.2`
     * In a command line tool **stop and remove Tempo container** with `docker rm -f tempo`
     * In a command line tool **remove Tempo image** with `docker rmi grafana/tempo:2.2.2`
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

Implementation steps for **Loki (logs)**:
* In every custom service add file **src/main/resources/logback-spring.xml**
* In every custom servide update **pom.xml** file with **loki-logback-appender**
* In every custom servide update **application.properties** file with **logging.pattern.correlation=[${spring.application.name:},%X{traceId:-},%X{spanId:-}]**

Implementation steps for **Prometeus (metrics)**:
* In every custom servide update **pom.xml** file with **spring-boot-starter-actuator** and **micrometer-registry-prometheus**
* In every custom servide update **application.properties** file with **management.endpoints.web.exposure.include**, **management.metrics.distribution.percentiles-histogram.http.server.requests** and **management.observations.key-values.application**
* In root folder create file **docker/prometheus/prometheus.yml**

Implementation steps for **Tempo (tracks)**:
* In every custom servide update **pom.xml** file with **micrometer-tracing-bridge-brave** and **zipkin-reporter-brave**
* In every custom servide update **application.properties** file with **management.tracing.sampling.probability=1.0**
* In root folder create file **docker/tempo/tempo.yml**

Implementation steps for **Grafana (dashboard)**:
* In root folder create file **docker/grafana/datasource.yml**


ADDITIONAL SOURCES
------------------

Sources:
* https://www.youtube.com/watch?v=PT2yZTBnUwQ&list=PLSVW22jAG8pBnhAdq9S8BpLnZ0_jVBj0c