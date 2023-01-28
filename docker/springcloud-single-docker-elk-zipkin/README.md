USAGE
-----

Usage steps:
1. Build package with `mvn clean package -D maven.test.skip`
1. Create network with `docker network create helloworld-network`

1. Start Elasticsearch container with `docker run -d -p 9200:9200 --network helloworld-network -e bootstrap.memory_lock=true -e ES_JAVA_OPTS=-Xms512m -Xmx512m -e discovery.type=single-node -e xpack.security.enabled=false -v elasticsearch_data:/usr/share/elasticsearch/data --name elasticsearch-container docker.elastic.co/elasticsearch/elasticsearch:8.3.3`

1. Start Kibana container with `docker run -d -p 5601:5601 --network helloworld-network -e elasticsearch.url=http://elasticsearch-container:9200 -e elasticsearch.hosts=["http://elasticsearch-container:9200"] --name kibana-container docker.elastic.co/kibana/kibana:8.3.3`

1. Start Logstash container with `docker run -d -p 5044:5044 -p 5000:5000/tcp -p 5000:5000/udp -p 9600:9600 --network helloworld-network -e ls.java.opts="-Xmx256m -Xms256m" -v ./logstash/config/logstash.yml:/usr/share/logstash/config/logstash.yml:ro -v ./logstash/pipeline:/usr/share/logstash/pipeline:ro --name logstash-container docker.elastic.co/logstash/logstash:8.3.3`

1. Start Zipkin container with `docker run -d -p 9411:9411 --network helloworld-network --name zipkin-container openzipkin/zipkin`

1. Build Service Discovery image with `docker build -f service-discovery/Dockerfile-Fast -t service-discovery-image ./service-discovery`
1. Start Service Discovery container with `docker run -d -p 8761:8761 --network helloworld-network -e spring.application.name=service-discovery -e server.port=8761 -e eureka.client.fetch-registry=false -e eureka.client.register-with-eureka=false --name service-discovery-container service-discovery-image`
1. Build Service Config image with `docker build -f service-config/Dockerfile-Fast -t service-config-image ./service-config`
1. Start Service Config container with `docker run -d -p 8888:8888 --network helloworld-network -e spring.application.name=service-config -e server.port=8888 -e eureka.client.service-url.defaultZone=http://service-discovery-container:8761/eureka -e management.endpoints.jmx.exposure.include=health,info,env,beans -e management.endpoints.web.exposure.include=health,info,env,beans -e spring.cloud.config.server.git.uri=https://github.com/wisniewskikr/springcloud-config -e spring.cloud.config.server.git.clone-on-start=true --name service-config-container service-config-image`
1. Build Service HelloWorld image with `docker build -f service-helloworld/Dockerfile-Fast -t service-helloworld-image ./service-helloworld`
1. Start Service HelloWorld 1 container with `docker run -d -p 8080:8080 --network helloworld-network -e spring.application.name=service-helloworld -e server.port=8080 -e eureka.client.service-url.defaultZone=http://service-discovery-container:8761/eureka -e management.endpoints.jmx.exposure.include=health,info,env,beans -e management.endpoints.web.exposure.include=health,info,env,beans -e spring.config.import=optional:configserver:http://service-config-container:8888 -e spring.zipkin.baseUrl=http://zipkin-container:9411/ --name service-helloworld-container-1 service-helloworld-image`
1. Start Service HelloWorld 2 container with `docker run -d -p 8081:8081 --network helloworld-network -e spring.application.name=service-helloworld -e server.port=8081 -e eureka.client.service-url.defaultZone=http://service-discovery-container:8761/eureka -e management.endpoints.jmx.exposure.include=health,info,env,beans -e management.endpoints.web.exposure.include=health,info,env,beans -e spring.config.import=optional:configserver:http://service-config-container:8888 -e spring.zipkin.baseUrl=http://zipkin-container:9411/ --name service-helloworld-container-2 service-helloworld-image`
1. Build Service Gateway image with `docker build -f service-gateway/Dockerfile-Fast -t service-gateway-image ./service-gateway`
1. Start Service Gateway container with `docker run -d -p 8762:8762 --network helloworld-network -e spring.application.name=service-gateway -e server.port=8762 -e eureka.client.service-url.defaultZone=http://service-discovery-container:8761/eureka -e management.endpoints.jmx.exposure.include=health,info,env,beans -e management.endpoints.web.exposure.include=health,info,env,beans -e SPRING_CLOUD_GATEWAY_ROUTES[0]_ID='service-helloworld' -e SPRING_CLOUD_GATEWAY_ROUTES[0]_URI='lb://service-helloworld' -e SPRING_CLOUD_GATEWAY_ROUTES[0]_PREDICATES[0]='Path=/service-helloworld**' -e SPRING_CLOUD_GATEWAY_ROUTES[0]_FILTERS[0]='RewritePath=/service-helloworld,/' --name service-gateway-container service-gateway-image`
1. Visit (expected first uuid - feature of load balancer) `http://localhost:8762/service-helloworld`
1. Visit (expected second uuid - feature of load balancer) `http://localhost:8762/service-helloworld`
1. Visit (expected again first uuid - feature of load balancer) `http://localhost:8762/service-helloworld`
1. (Optional) Check services in Service Discovery by visiting `http://localhost:8761`
1. (Optional) Check first Service HelloWorld without Load Balancer by visiting `http://localhost:8080`  
1. (Optional) Check second Service HelloWorld without Load Balancer by visiting `http://localhost:8081` 
1. Clean up environment:

    * Remove Elasticsearch container with `docker rm -f elasticsearch-container`
    * Remove Elasticsearch image with `docker rmi docker.elastic.co/elasticsearch/elasticsearch:8.3.3`
    
    * Remove Kibana container with `docker rm -f kibana-container`
    * Remove Kibana image with `docker rmi docker.elastic.co/kibana/kibana:8.3.3`
    
    * Remove Logstash container with `docker rm -f logstash-container`
    * Remove Logstash image with `docker rmi logstash-container docker.elastic.co/logstash/logstash:8.3.3`
    
    * Remove Zipkin container with `docker rm -f zipkin-container`
    * Remove Zipkin image with `docker rmi openzipkin/zipkin`
    
    * Remove Service Discovery container with `docker rm -f service-discovery-container`
    * Remove Service Discovery image with `docker rmi service-discovery-image`
    * Remove Service Config container with `docker rm -f service-config-container`
    * Remove Service Config image with `docker rmi service-config-image`
    * Remove Service HelloWorld container with `docker rm -f service-helloworld-container-1`
    * Remove Service HelloWorld container with `docker rm -f service-helloworld-container-2`
    * Remove Service HelloWorld image with `docker rmi service-helloworld-image`
    * Remove Service Gateway container with `docker rm -f service-gateway-container`
    * Remove Service Gateway image with `docker rmi service-gateway-image`
    * Remove network with `docker network rm helloworld-network`
















DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to implement **microservices** using **Java** programming language and **Spring Boot Cloud** framework. This project consists of few microservices implemented as independent **Maven modules**. In the system there is only one custom service - Service HelloWorld. This service is run as two instances to present load balancing usage. The rest of services in the system are provided by Spring Boot Cloud and they are used for system management.

All services are dockerized and manged by docker orchestration tool **docker compose**. It means that user does not have to start up manually all services one by one. It's done automatically by orchestration tool. 

This project also presents how to configure and use **Zipkin** - tool for distributed tracking. It enables to track every request/response sent in system - which services were involved in this communication and how long takes every service to handle request/response.

This project also presents how to configure and use **EKL** (Elasticsearch, Kibana, Logstash) - tool for centralized logging. It enables to display in one place logs from all services in system.

##### Service
This project consists of following services:
* **Service Discovery**: port **8761**. This service displays list of all active services in system
* **Service Config**: port **8888**. This service provides flexible configuration variables. These variables can be taken for instance from Github
* **Service HelloWorld**: port **random**. Two instances of Service HelloWorld which provide JSON with message and application id
* **Service Gateway**: port **8762**. This service redirects request from outside system to service inside system. It also takes care of load balancing
* **Elasticsearch**: port **9200**. This service stores logs provided by applications and used by Kibana
* **Kibana**: port **5601**. This service displays logs.
* **Logstash**: port **5000**. This service takes logs from applications and provides them to Kibana
* **Zipkin**: port **9411**. This service provides information about distributed tracking.

##### Flow
The following flow takes place in this project:
1. User via any REST Client (for instance Postman) sends request to Service HellWorld for content. This request is not sent directly but through Service Gateway. 
1. Service Gateway takes location of all services in system from Service Discovery.
1. This example system consists of two instances of Service HelloWorld. In such situation Service Gateway also performs load balancing - first request is sent to Service HelloWorld 1,
second to Service HelloWorld 2, third again to Service HelloWorld 1 etc. 
1. Service HelloWorld which receives request connects with Service Config for text of message. This text is taken from Github project
1. Service HelloWorld sends response to User via REST Client. This response contains message and application id of this exact instance of Servie HelloWorld. 
After every request this application id is changed because of Service Gateway and load balancing

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
1. In Command Line tool start services with `docker-compose -f docker-compose-fast.yml up --scale service-helloworld=2 --build`
1. In any REST Client (for instance Postman) connect with Service HelloWorld via Service Gateway with (method GET): `http://localhost:8762/service-helloworld`
1. Check distributed tracking in Zipkin with `http://localhost:9411`
1. Check centralized logs in Kibana with `http://localhost:5601` 
1. (Optional) In any browser check services list with `http://localhost:8761`
1. (Optional) In any Rest Client run following request many times to check load balancing (application id should be changed every request) (method GET): `http://localhost:8762/service-helloworld`
1. In Command Line stop services with `ctrl + C`
1. In Command Line remove containers with `docker-compose down`


USAGE SLOW (DOES NOT REQUIRE LOCALLY INSTALLED JAVA AND MAVEN)
--------------------------------------------------------------

Usage steps:
1. In Command Line tool start services with `docker-compose up --scale service-helloworld=2 --build`
1. In any REST Client (for instance Postman) connect with Service HelloWorld via Service Gateway with (method GET): `http://localhost:8762/service-helloworld`
1. Check distributed tracking in Zipkin with `http://localhost:9411`
1. Check centralized logs in Kibana with `http://localhost:5601` 
1. (Optional) In any browser check services list with `http://localhost:8761`
1. (Optional) In any Rest Client run following request many times to check load balancing (application id should be changed every request) (method GET): `http://localhost:8762/service-helloworld`
1. In Command Line stop services with `ctrl + C`
1. In Command Line remove containers with `docker-compose down`


USAGE ZIPKIN
------------

![My Image](zipkin-1.png)

![My Image](zipkin-2.png)

![My Image](zipkin-3.png)


USAGE ELK
------------

![My Image](elk-1.png)

![My Image](elk-2.png)

![My Image](elk-3.png)

![My Image](elk-4.png)