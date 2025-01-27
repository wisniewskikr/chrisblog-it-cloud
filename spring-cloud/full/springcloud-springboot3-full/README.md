EXAMPLE
-------

![My Image](readme-images/image-01.png)

![My Image](readme-images/image-02.png)

![My Image](readme-images/image-03.png)

![My Image](readme-images/image-04.png)

![My Image](readme-images/image-05.png)

![My Image](readme-images/image-06.png)


DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to create **ecosystem of microservices** with usage **Java** programming language and **Spring Cloud** and **Spring Boot 3** frameworks.

##### Services
This project consists of following applications:
* **Database**: SQL database - in this case type **MySql**
* **Back-End**: an application created in **Java** programming language with usage **Spring Boot** framework
* **Front-End**: an application created in **Java** programming language with usage **Spring Boot** framework. **Thymeleaf** engine is used to display data
* **Loki**: it enables collecting logs from many microservices
* **Prometeus**" it enables collecting metrics from many microservices
* **Tempo**: it enables collecting traces from many microservices
* **Grafana**: it enables displaying logs, metrics and traces from Loki, Prometeus and Tempo tools 
* **Api Gateway**: it enables redirectic traffic to different microservices
* **Service Discovery**: it manages microservices

##### Inputs
This project requires following inputs:
* **Service Discovery**: http call from any browser
* **Api Gateway**: http call from any browser
* **Grafana**: http call from any browser

##### Outputs
This project provides following outputs:
* **Service Discovery**: it displayes all microservices in ecosystem
* **Api Gateway**: it redirects trafic to Front-End service which connects with Back-End service which connects with database. Output:
   * **Database Message**: the HTML displays the message stored in database. It's the simple text "Hello World!".
   * **Back-End Port**: the HTML page displays the port of Back-End application.
   * **Front-End Port**: the HTML page displays port of Front-End application.
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
* **API Gateway**: An API Gateway is a management tool that acts as an entry point for APIs, handling tasks like routing, authentication, rate limiting, monitoring, and load balancing. It simplifies communication between clients and backend services, often used in microservices architectures to centralize API requests and enforce policies.
* **Service Discovery**: Service discovery is the process of automatically detecting and locating network services or resources in a system, enabling communication between components without requiring manual configuration. It ensures that services can find and connect to each other dynamically in distributed or microservices architectures.


USAGES
------

This project can be tested in following configurations:
* **Usage Docker Compose**: all services are started as Docker containers definied in docker compose file.
* **Usage Kubernetes (Kind)**: all services are started as Kubernetes pods.


USAGE DOCKER COMPOSE
--------------------

> **Usage Docker Compse** means all services are started as Docker containers definied in docker compose file.

> Please **clone/download** project, open **project's main folder** in your favorite **command line tool** and then **proceed with steps below**.

> **Prerequisites**:  
* **Operating System** (tested on Windows 11)
* **Git** (tested on version 2.33.0.windows.2)
* **Docker** (tested on version 4.33.1)

##### Required steps:
1. Start **Docker** tool
1. In a command line tool **start Docker containers** with `docker-compose -f .\docker-compose\docker-compose.yaml up -d --build`
1. In a browser visit `http://localhost:8761`
   * Expected HTML page with **Discovery dashboard**
1. In a browser visit `http://localhost:8762`
   * Expected HTML page with **Database Message**, **Back-End Port** and **Front-End Port** 
1. In a browser visit `http://localhost:3000`
   * Expected HTML page with **Grafana dashboard** (please check section **EXAMPLE**).
1. Clean up environment 
     * In a command line tool **remove Docker containers** with `docker-compose -f .\docker-compose\docker-compose.yaml down --rmi all`
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


USAGE KUBERNETES (KIND)
---------------------------

> **Usage Kubernetes** means that all services are started as Kubernetes pods. 

> Please **clone/download** project, open **project's main folder** in your favorite **command line tool** and then **proceed with steps below**.

> **Prerequisites**:  
* **Operating System** (tested on Windows 11)
* **Git** (tested on version 2.33.0.windows.2)
* **Kind** (tested on version 0.26.0)

##### Required steps:
1. Start **Docker** tool
1. In the first command line tool create and start cluster **Kind** with `kind create cluster --name helloworld`
1. In the second command line tool **start Kubernetes Pods** with `kubectl apply -f ./k8s/kubernetes.yaml`
1. In the second command line tool **check status of Kubernetes Pods** with `kubectl get pods`
   * Expected mysql, be and fe as **READY 1/1** (it can take few minutes)

...

1. Clean up environment 
     * In the second command line tool **remove Kubernetes Pods** with `kubectl delete -f ./k8s/kubernetes.yaml`
     * In the first command line tool delete cluster **Kind** with `kind delete cluster --name helloworld`
     * Stop **Docker** tool

##### Optional steps:
1. In a command line tool build Docker BE image with `docker build -f springcloud-springboot3-full_BE/Dockerfile -t wisniewskikr/springcloud-springboot3-full_be:0.0.1 ./springcloud-springboot3-full_BE`
1. In a command line tool push Docker BE image to Docker Repository with `docker push wisniewskikr/springcloud-springboot3-full_be:0.0.1` 
1. In a command line tool build Docker FE image with `docker build -f springcloud-springboot3-full_FE/Dockerfile -t wisniewskikr/springcloud-springboot3-full_fe:0.0.1 ./springcloud-springboot3-full_FE`
1. In a command line tool push Docker FE image to Docker Repository with `docker push wisniewskikr/springcloud-springboot3-full_fe:0.0.1` 
1. In a command line tool build Docker GATEWAY image with `docker build -f springcloud-springboot3-full_GATEWAY/Dockerfile -t wisniewskikr/springcloud-springboot3-full_gateway:0.0.1 ./springcloud-springboot3-full_GATEWAY`
1. In a command line tool push Docker GATEWAY image to Docker Repository with `docker push wisniewskikr/springcloud-springboot3-full_gateway:0.0.1`
1. In a command line tool build Docker DISCOVERY image with `docker build -f springcloud-springboot3-full_DISCOVERY/Dockerfile -t wisniewskikr/springcloud-springboot3-full_discovery:0.0.1 ./springcloud-springboot3-full_DISCOVERY`
1. In a command line tool push Docker DISCOVERY image to Docker Repository with `docker push wisniewskikr/springcloud-springboot3-full_discovery:0.0.1`  
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