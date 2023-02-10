DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to implement **microservices** using **Java** programming language and **Spring Boot Cloud** framework. This project consists of few microservices implemented as independent **Maven modules**. In the system there are two Hello World modules - Display and Storage - which are connected in the **network**. Network means that Service HelloWorld Display displays message received from Service HelloWorld Storage. The rest of services in the system are provided by Spring Boot Cloud and they are used for system management.

All services are dockerized so you can run them using **Docker** tool. 

##### Service
This project consists of following services:
* **Service Discovery**: port **8761**. This service displays list of all active services in system
* **Service Config**: port **8888**. This service provides flexible configuration variables. These variables can be taken for instance from Github
* **Service HelloWorld Storage**: port **8081** and **8082**: This service provides JSON with message and port
* **Service HelloWorld Display**: port **8080**. This service displays to the user three information: message from Storage, uuid from Storage and uuid from Display
* **Service Gateway**: port **8762**. This service redirects request from outside system to service inside system. It also takes care of load balancing

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


PRECONDITIONS
-------------

##### Preconditions - Tools
* Installed **Operating System** (tested on Windows 10)
* Installed **Java** (tested on version 11.0.16.1). Tool details: `https://docs.google.com/document/d/119VYxF8JIZIUSk7JjwEPNX1RVjHBGbXHBKuK_1ytJg4/edit?usp=sharing`
* Installed **Maven** (tested on version 3.8.5). Tool details: `https://docs.google.com/document/d/1cfIMcqkWlobUfVfTLQp7ixqEcOtoTR8X6OGo3cU4maw/edit?usp=sharing`
* Installed **Git** (tested on version 2.33.0.windows.2). Tool details: `https://docs.google.com/document/d/1Iyxy5DYfsrEZK5fxZJnYy5a1saARxd5LyMEscJKSHn0/edit?usp=sharing`
* Installed **Docker** (tested on version 20.10.21). Tool details: `https://docs.google.com/document/d/1tKdfZIrNhTNWjlWcqUkg4lteI91EhBvaj6VDrhpnCnk/edit?usp=sharing`

##### Preconditions - Actions
* **Launched** Docker and Docker Compose tools on your local machine
* **Download** source code using Git 
* Open any **Command Line** (for instance "Windonw PowerShell" on Windows OS) tool on **project's folder** (exact localization of project you can check in GIT repositories on page `https://github.com/wisniewskikr/chrisblog-it-cloud`)


USAGE
-----

Usage steps:
1. Build package with `mvn clean package -D maven.test.skip`
1. Create network with `docker network create helloworld-network`
1. Build Service Discovery image with `docker build -f service-discovery/Dockerfile-Fast -t service-discovery-image ./service-discovery`
1. Start Service Discovery container with `docker run -d -p 8761:8761 --network helloworld-network -e spring.application.name=service-discovery -e server.port=8761 -e eureka.client.fetch-registry=false -e eureka.client.register-with-eureka=false --name service-discovery-container service-discovery-image`
1. Build Service Config image with `docker build -f service-config/Dockerfile-Fast -t service-config-image ./service-config`
1. Start Service Config container with `docker run -d -p 8888:8888 --network helloworld-network -e spring.application.name=service-config -e server.port=8888 -e eureka.client.service-url.defaultZone=http://service-discovery-container:8761/eureka -e management.endpoints.jmx.exposure.include=health,info,env,beans -e management.endpoints.web.exposure.include=health,info,env,beans -e spring.cloud.config.server.git.uri=https://github.com/wisniewskikr/springcloud-config -e spring.cloud.config.server.git.clone-on-start=true --name service-config-container service-config-image`
1. Build Service Storage HelloWorld image with `docker build -f service-helloworld-storage/Dockerfile-Fast -t service-helloworld-storage-image ./service-helloworld-storage`
1. Start Service Storage HelloWorld 1 container with `docker run -d -p 8081:8081 --network helloworld-network -e spring.application.name=service-helloworld-storage -e server.port=8081 -e eureka.client.service-url.defaultZone=http://service-discovery-container:8761/eureka -e management.endpoints.jmx.exposure.include=health,info,env,beans -e management.endpoints.web.exposure.include=health,info,env,beans -e spring.config.import=optional:configserver:http://service-config-container:8888 --name service-helloworld-storage-container-1 service-helloworld-storage-image`
1. Start Service Storage HelloWorld 2 container with `docker run -d -p 8082:8082 --network helloworld-network -e spring.application.name=service-helloworld-storage -e server.port=8082 -e eureka.client.service-url.defaultZone=http://service-discovery-container:8761/eureka -e management.endpoints.jmx.exposure.include=health,info,env,beans -e management.endpoints.web.exposure.include=health,info,env,beans -e spring.config.import=optional:configserver:http://service-config-container:8888 --name service-helloworld-storage-container-2 service-helloworld-storage-image`
1. Build Service Display HelloWorld image with `docker build -f service-helloworld-display/Dockerfile-Fast -t service-helloworld-display-image ./service-helloworld-display`
1. Start Service Display HelloWorld container with `docker run -d -p 8080:8080 --network helloworld-network -e spring.application.name=service-helloworld-display -e server.port=8080 -e eureka.client.service-url.defaultZone=http://service-discovery-container:8761/eureka -e management.endpoints.jmx.exposure.include=health,info,env,beans -e management.endpoints.web.exposure.include=health,info,env,beans -e service.helloworld.storage.name=service-helloworld-storage --name service-helloworld-display-container service-helloworld-display-image`
1. Build Service Gateway image with `docker build -f service-gateway/Dockerfile-Fast -t service-gateway-image ./service-gateway`
1. Start Service Gateway container with `docker run -d -p 8762:8762 --network helloworld-network -e spring.application.name=service-gateway -e server.port=8762 -e eureka.client.service-url.defaultZone=http://service-discovery-container:8761/eureka -e management.endpoints.jmx.exposure.include=health,info,env,beans -e management.endpoints.web.exposure.include=health,info,env,beans -e SPRING_CLOUD_GATEWAY_ROUTES[0]_ID='service-helloworld-display' -e SPRING_CLOUD_GATEWAY_ROUTES[0]_URI='lb://service-helloworld-display' -e SPRING_CLOUD_GATEWAY_ROUTES[0]_PREDICATES[0]='Path=/service-helloworld-display**' -e SPRING_CLOUD_GATEWAY_ROUTES[0]_FILTERS[0]='RewritePath=/service-helloworld-display,/' --name service-gateway-container service-gateway-image`
1. Visit (expected first uuid - feature of load balancer) `http://localhost:8762/service-helloworld-display`
1. Visit (expected second uuid - feature of load balancer) `http://localhost:8762/service-helloworld-display`
1. Visit (expected again first uuid - feature of load balancer) `http://localhost:8762/service-helloworld-display`
1. (Optional) Check services in Service Discovery by visiting `http://localhost:8761`
1. (Optional) Check first Service HelloWorld Storage without Load Balancer by visiting `http://localhost:8081`  
1. (Optional) Check second Service HelloWorld Storage without Load Balancer by visiting `http://localhost:8082`   
1. (Optional) Check second Service HelloWorld Display without Load Balancer by visiting `http://localhost:8080` 
1. Clean up environment:

    * Remove Service Discovery container with `docker rm -f service-discovery-container`
    * Remove Service Discovery image with `docker rmi service-discovery-image`
    * Remove Service Config container with `docker rm -f service-config-container`
    * Remove Service Config image with `docker rmi service-config-image`
    * Remove Service HelloWorld container with `docker rm -f service-helloworld-storage-container-1`
    * Remove Service HelloWorld container with `docker rm -f service-helloworld-storage-container-2`
    * Remove Service HelloWorld container with `docker rm -f service-helloworld-display-container`
    * Remove Service HelloWorld image with `docker rmi service-helloworld-storage-image`
    * Remove Service HelloWorld image with `docker rmi service-helloworld-display-image`
    * Remove Service Gateway container with `docker rm -f service-gateway-container`
    * Remove Service Gateway image with `docker rmi service-gateway-image`
    * Remove network with `docker network rm helloworld-network`