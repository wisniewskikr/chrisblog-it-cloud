DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to implement application in **Java** programming language with usage **Spring Boot Cloud** framework which advantage of **Discovery"" and **Gateway** services.

**Discovery** service (f.e. Eureka) contains information about all applications and their instances in system. So Load Balancer can use information about instances to redirect traffic between them. 

**Gateway** service is entry point to system for users. Users should always use URL to Gateway and then Gateway will redirect them to specific serivce.

All services are dockerized so you can run them using **Docker** tool. 

##### Service
This project consists of following services:
* **Service Discovery**: port **8761**. This service displays list of all active services in system
* **Service HelloWorld**: ports **8080** and **8081**. Two instances of Service HelloWorld which provide JSON with message and application id
* **Service Gateway**: port **8762**. This service redirects request from outside system to service inside system. It also takes care of load balancing

##### Flow
The following flow takes place in this project:
1. User via browser sends request to Service Gateway for content
1. Service Gateway checks instances of Service HelloWorld in Service Discovery. If there are more then one instance then it starts load balancing
1. Service Gateway redirects request to Service HelloWorld for content
1. Service HelloWorld sends back response to Service Gateway with message and uuid
1. Service Gateway redirects response to User via browser 

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
1. Build Service HelloWorld image with `docker build -f service-helloworld/Dockerfile-Fast -t service-helloworld-image ./service-helloworld`
1. Start Service HelloWorld 1 container with `docker run -d -p 8080:8080 --network helloworld-network -e spring.application.name=service-helloworld -e server.port=8080 -e eureka.client.service-url.defaultZone=http://service-discovery-container:8761/eureka -e management.endpoints.jmx.exposure.include=health,info,env,beans -e management.endpoints.web.exposure.include=health,info,env,beans -e service.helloworld.message="Hello World" --name service-helloworld-container-1 service-helloworld-image`
1. Start Service HelloWorld 2 container with `docker run -d -p 8081:8081 --network helloworld-network -e spring.application.name=service-helloworld -e server.port=8081 -e eureka.client.service-url.defaultZone=http://service-discovery-container:8761/eureka -e management.endpoints.jmx.exposure.include=health,info,env,beans -e management.endpoints.web.exposure.include=health,info,env,beans -e service.helloworld.message="Hello World" --name service-helloworld-container-2 service-helloworld-image`
1. Build Service Gateway image with `docker build -f service-gateway/Dockerfile-Fast -t service-gateway-image ./service-gateway`
1. Start Service Gateway container with `docker run -d -p 8762:8762 --network helloworld-network -e spring.application.name=service-gateway -e server.port=8762 -e eureka.client.service-url.defaultZone=http://service-discovery-container:8761/eureka -e management.endpoints.jmx.exposure.include=health,info,env,beans -e management.endpoints.web.exposure.include=health,info,env,beans -e SPRING_CLOUD_GATEWAY_ROUTES[0]_ID='service-helloworld' -e SPRING_CLOUD_GATEWAY_ROUTES[0]_URI='lb://service-helloworld' -e SPRING_CLOUD_GATEWAY_ROUTES[0]_PREDICATES[0]='Path=/service-helloworld**' -e SPRING_CLOUD_GATEWAY_ROUTES[0]_FILTERS[0]='RewritePath=/service-helloworld,/' --name service-gateway-container service-gateway-image`
1. Visit (expected first uuid - feature of load balancer) `http://localhost:8762/service-helloworld`
1. Visit (expected second uuid - feature of load balancer) `http://localhost:8762/service-helloworld`
1. Visit (expected again first uuid - feature of load balancer) `http://localhost:8762/service-helloworld`
1. (Optional) Check services in Service Discovery by visiting `http://localhost:8761`
1. (Optional) Check first Service HelloWorld without Load Balancer by visiting `http://localhost:8080`  
1. (Optional) Check second Service HelloWorld without Load Balancer by visiting `http://localhost:8081` 
1. Clean up environment:

    * Remove Service Discovery container with `docker rm -f service-discovery-container`
    * Remove Service Discovery image with `docker rmi service-discovery-image`
    * Remove Service HelloWorld container with `docker rm -f service-helloworld-container-1`
    * Remove Service HelloWorld container with `docker rm -f service-helloworld-container-2`
    * Remove Service HelloWorld image with `docker rmi service-helloworld-image`
    * Remove Service Gateway container with `docker rm -f service-gateway-container`
    * Remove Service Gateway image with `docker rmi service-gateway-image`
    * Remove network with `docker network rm helloworld-network`