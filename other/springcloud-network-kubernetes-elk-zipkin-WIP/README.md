USAGE
-----

> **NOTE:**  Please open Command Line tool as **administrator** on **main folder of project**.

Usage steps:
1. Connect Minikube and Docker with (Windows) `minikube docker-env | Invoke-Expression`
1. Pull Elasticsearch image with `docker pull docker.elastic.co/elasticsearch/elasticsearch:8.3.3`
1. Pull Kibana image with `docker pull docker.elastic.co/kibana/kibana:8.3.3`
1. Pull Logstash image with `docker pull docker.elastic.co/logstash/logstash:8.3.3`
1. Pull Zipkin image with `docker pull openzipkin/zipkin`
1. Build package with `mvn clean package -D maven.test.skip`
1. Build Service Discovery image with `docker build -f service-discovery/Dockerfile-Fast -t wisniewskikr/discovery-image ./service-discovery`
1. Build Service Config image with `docker build -f service-config/Dockerfile-Fast -t wisniewskikr/config-image ./service-config`
1. Build Service HelloWorld image with `docker build -f service-helloworld-storage/Dockerfile-Fast -t wisniewskikr/helloworld-storage-image ./service-helloworld-storage`
1. Build Service HelloWorld image with `docker build -f service-helloworld-display/Dockerfile-Fast -t wisniewskikr/helloworld-display-image ./service-helloworld-display`
1. Build Service Gateway image with `docker build -f service-gateway/Dockerfile-Fast -t wisniewskikr/gateway-image ./service-gateway`
1. (Optional) Check images in Minikube:

     * Run Minikube SSH with `minikube ssh`
     * Display Minikube images (expected new images from this project) with `docker images`
     * Close Minikube SSH with `exit`

1. Start Elasticsearch service with `kubectl apply -f 1-elasticsearch.yaml`
1. Start Kibana service with `kubectl apply -f 2-kibana.yaml`
1. Start Logstash service with `kubectl apply -f 3-logstash.yaml`
1. Start Zipkin service with `kubectl apply -f 4-zipkin.yaml`
1. Start Discovery service with `kubectl apply -f 5-discovery.yaml`
1. Start Config service with `kubectl apply -f 6-config.yaml`
1. Start Storage service with `kubectl apply -f 7-storage.yaml`
1. Start Display service with `kubectl apply -f 8-display.yaml`
1. Start Gateway service with `kubectl apply -f 9-gateway.yaml`
1. (Optional) Check status of services with `kubectl get pods`
1. Launch Gateway Service in browser with `minikube service service-gateway-show`
1. Launch Zipkin Service in browser with `minikube service service-zipkin-show`
1. (Optional) Launch Discovery Service in browser with `minikube service service-discovery-show`
1. (Optional) Launch HelloWorld Service in browser with `minikube service service-helloworld-show`
1. Clean up environment:
    
    * Remove Gateway service with `kubectl delete -f 9-gateway.yaml`
    * Remove HelloWorld service with `kubectl delete -f 8-display.yaml`
    * Remove HelloWorld service with `kubectl delete -f 7-storage.yaml`
    * Remove Config service with `kubectl delete -f 6-config.yaml`
    * Remove Discovery service with `kubectl delete -f 5-discovery.yaml`
    * Remove Zipkin service with `kubectl delete -f 4-zipkin.yaml`
    * Remove Logstash service with `kubectl delete -f 3-logstash.yaml`
    * Remove Kibana service with `kubectl delete -f 2-kibana.yaml`
    * Remove Elasticsearch service with `kubectl delete -f 1-elasticsearch.yaml`
    * Remove Service Discovery image with `docker rmi wisniewskikr/discovery-image`
    * Remove Service HelloWorld image with `docker rmi wisniewskikr/helloworld-storage-image`
    * Remove Service HelloWorld image with `docker rmi wisniewskikr/helloworld-display-image`
    * Remove Service Gateway image with `docker rmi wisniewskikr/gateway-image`
    * Remove Service Config image with `docker rmi wisniewskikr/config-image`
    * Remove Service Elasticsearch image with `docker rmi docker.elastic.co/elasticsearch/elasticsearch:8.3.3`
    * Remove Service Kibana image with `docker rmi docker.elastic.co/kibana/kibana:8.3.3`
    * Remove Service Logstash image with `docker rmi docker.elastic.co/logstash/logstash:8.3.3`
    * Remove Service Zipkin image with `docker rmi openzipkin/zipkin`




























DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to implement **microservices** using **Java** programming language and **Spring Boot Cloud** framework. This project consists of few microservices implemented as independent **Maven modules**. In the system there are two Hello World modules - Display and Storage - which are connected in the **network**. Network means that Service HelloWorld Display displays message received from Service HelloWorld Storage. The rest of services in the system are provided by Spring Boot Cloud and they are used for system management.

All services are dockerized and manged by docker orchestration tool **docker compose**. It means that user does not have to start up manually all services one by one. It's done automatically by orchestration tool. 

This project also presents how to configure and use **Zipkin** - tool for distributed tracking. It enables to track every request/response sent in system - which services were involved in this communication and how long takes every service to handle request/response.

This project also presents how to configure and use **EKL** (Elasticsearch, Kibana, Logstash) - tool for centralized logging. It enables to display in one place logs from all services in system.

##### Service
This project consists of following services:
* **Service Discovery**: port **8761**. This service displays list of all active services in system
* **Service Config**: port **8888**. This service provides flexible configuration variables. These variables can be taken for instance from Github
* **Service HelloWorld Storage**: port **random**: This service provides JSON with message and port
* **Service HelloWorld Display**: port **8080**. This service displays to the user three information: message from Storage, uuid from Storage and uuid from Display
* **Service Gateway**: port **8762**. This service redirects request from outside system to service inside system. It also takes care of load balancing
* **Elasticsearch**: port **9200**. This service stores logs provided by applications and used by Kibana
* **Kibana**: port **5601**. This service displays logs.
* **Logstash**: port **5000**. This service takes logs from applications and provides them to Kibana
* **Zipkin**: port **9411**. This service provides information about distributed tracking.

##### Flow
The following flow takes place in this project:
1. User via any REST Client (for instance Postman) sends request to Service HellWorld Display for content. This request is not sent directly but through Service Gateway. 
1. Service Gateway takes location of all services in system from Service Discovery.
1. Service HelloWorld Display sends request for content to Service HelloWorld Storage. In this example system there are two instances of Storage. In such situation Service Gateway performs load balancing - first request is sent to Service HelloWorld Storage 1, second to Service HelloWorld Storage 2, third again to Service HelloWorld Storage 1 etc. 
1. Service HelloWorld Storage connects with Service Config for text of message. This text is taken from Github project
1. Service HelloWorld Storage sends response to Service HelloWorld Display
1. Service HelloWorld Display sends response to User via REST Client. This response contains message, port of Display and port of instance of Storage. 
After every request this port is changed because of Service Gateway and load balancing

##### Launch
To launch this application please make sure that the **Preconditions** are met and then follow instructions from **Usage** section.

##### Technologies
This project uses following technologies:
* **Spring Boot** framework: `https://docs.google.com/document/d/1mvrJT5clbkr9yTj-AQ7YOXcqr2eHSEw2J8n9BMZIZKY/edit?usp=sharing`
* **Microservices**: `https://docs.google.com/document/d/1j_lwf5L0-yTPew75RIWcA6AGeAnJjx0M4Bk4DrUcLXc/edit?usp=sharing`
* **Docker**: `https://docs.google.com/document/d/1tKdfZIrNhTNWjlWcqUkg4lteI91EhBvaj6VDrhpnCnk/edit?usp=sharing`
* **Docker Compose**: `https://docs.google.com/document/d/1SPrCS5OS_G0je_wmcLGrX8cFv7ZkQbb5uztNc9kElS4/edit?usp=sharing`


PRECONDITIONS
-------------

##### Preconditions - Tools
* Installed **Operating System** (tested on Windows 10)
* Installed **Java** (tested on version 11.0.16.1). Tool details: `https://docs.google.com/document/d/119VYxF8JIZIUSk7JjwEPNX1RVjHBGbXHBKuK_1ytJg4/edit?usp=sharing`
* Installed **Maven** (tested on version 3.8.5). Tool details: `https://docs.google.com/document/d/1cfIMcqkWlobUfVfTLQp7ixqEcOtoTR8X6OGo3cU4maw/edit?usp=sharing`
* Installed **Git** (tested on version 2.33.0.windows.2). Tool details: `https://docs.google.com/document/d/1Iyxy5DYfsrEZK5fxZJnYy5a1saARxd5LyMEscJKSHn0/edit?usp=sharing`
* Installed **Docker** (tested on version 20.10.21). Tool details: `https://docs.google.com/document/d/1tKdfZIrNhTNWjlWcqUkg4lteI91EhBvaj6VDrhpnCnk/edit?usp=sharing`
* Installed **Docker Compose** (tested on version v2.12.2). Tool details: `https://docs.google.com/document/d/1SPrCS5OS_G0je_wmcLGrX8cFv7ZkQbb5uztNc9kElS4/edit?usp=sharing`

##### Preconditions - Actions
* **Launched** Docker and Docker Compose tools on your local machine
* **Download** source code using Git 
* Open any **Command Line** (for instance "Windonw PowerShell" on Windows OS) tool on **project's folder** (exact localization of project you can check in GIT repositories on page `https://github.com/wisniewskikr/chrisblog-it-cloud`)


USAGE FAST (REQUIRES LOCALLY INSTALLED JAVA AND MAVEN)
------------------------------------------------------

Usage steps:
1. In Command Line tool build packages with `mvn clean package -Dmaven.test.skip`
1. In Command Line tool start services with `docker-compose -f docker-compose-fast.yml up --scale service-helloworld-storage=2 --build`
1. In any REST Client (for instance Postman) connect with Service HelloWorld via Service Gateway with (method GET): `http://localhost:8762/service-helloworld-display`
1. Check distributed tracking in Zipkin with `http://localhost:9411`
1. Check centralized logs in EKL with `http://localhost:5601` 
1. (Optional) In any browser check services list with `http://localhost:8761`
1. (Optional) In any Rest Client run following request many times to check load balancing (Service Storage Uuid should be changed every request) (method GET): `http://localhost:8762/service-helloworld-display`
1. In Command Line stop services with `ctrl + C`
1. In Command Line remove containers with `docker-compose down`


USAGE SLOW (DOES NOT REQUIRE LOCALLY INSTALLED JAVA AND MAVEN)
------------------------------------------------------

Usage steps:
1. In Command Line tool start services with `docker-compose up --scale service-helloworld-storage=2 --build`
1. In any REST Client (for instance Postman) connect with Service HelloWorld via Service Gateway with (method GET): `http://localhost:8762/service-helloworld-display`
1. Check distributed tracking in Zipkin with `http://localhost:9411`
1. Check centralized logs in EKL with `http://localhost:5601` 
1. (Optional) In any browser check services list with `http://localhost:8761`
1. (Optional) In any Rest Client run following request many times to check load balancing (Service Storage Uuid should be changed every request) (method GET): `http://localhost:8762/service-helloworld-display`
1. In Command Line stop services with `ctrl + C`
1. In Command Line remove containers with `docker-compose down`