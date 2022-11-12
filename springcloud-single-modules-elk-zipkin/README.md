DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to implement **microservices** using **Java** programming language and **Spring Boot Cloud** framework. This project consists of few microservices implemented as independent **Maven modules**. But in the system there is only **single module** of custom service - Service HelloWorld. This service is run as two instances to present load balancing usage. The rest of services in the system are provided by Spring Boot Cloud and they are used for system management. 

This project also presents how to configure and use **Zipkin** - tool for distributed tracking. It enables to track every request/response sent in system - which services were involved in this communication and how long takes every service to handle request/response.

This project also presents how to configure and use **EKL** (Elasticsearch, Kibana, Logstash) - tool for centralized logging. It enables to display in one place logs from all services in system. 

#### Service
This project consists of following services:
* **Service Discovery**: port **8761**. This service displays list of all active services in system
* **Service Config**: port **8888**. This service provides flexible configuration variables. These variables can be taken for instance from Github
* **Service HelloWorld 1**: port **8080**. First instance of Service HelloWorld which provides JSON with message and port
* **Service HelloWorld 2**: port **8081**. Second instance of Service HelloWorld which provides JSON with message and port
* **Service Gateway**: port **8762**. This service redirects request from outside system to service inside system. It also takes care of load balancing

##### Flow
The following flow takes place in this project:
1. User via any REST Client (for instance Postman) sends request to Service HellWorld for content. This request is not sent directly but through Service Gateway. 
1. Service Gateway takes location of all services in system from Service Discovery.
1. This example system consists of two Services HelloWorld. In such situation Service Gateway also performs load balancing - first request is sent to Service HelloWorld 1,
second to Service HelloWorld 2, third again to Service HelloWorld 1 etc. 
1. Service HelloWorld which receives request connects with Service Config for text of message. This text is taken from Github project
1. Service HelloWorld sends response to User via REST Client. This response contains message and port of this exact instance of Servie HelloWorld. 
After every request this port is changed because of Service Gateway and load balancing

##### Launch
To launch this application please make sure that the **Preconditions** are met and then follow instructions from **Usage** section.

##### Technologies
This project uses following technologies:
* **Spring Boot** framework: `https://docs.google.com/document/d/1mvrJT5clbkr9yTj-AQ7YOXcqr2eHSEw2J8n9BMZIZKY/edit?usp=sharing`
* **Microservices**: `https://docs.google.com/document/d/1j_lwf5L0-yTPew75RIWcA6AGeAnJjx0M4Bk4DrUcLXc/edit?usp=sharing`
* **ELK**: `https://docs.google.com/document/d/1vpxqHJIp0rLkpNlkKdyn69dOBHEvQGN7Rmzs8GlKjp0/edit?usp=sharing`
* **Zipkin**: `https://docs.google.com/document/d/1OxtemDYGN2rQSa3lMbdTI7GdFadt496mGlccbHEukaY/edit?usp=sharing`


PRECONDITIONS
-------------

##### Preconditions - Tools
* Installed **Operating System** (tested on Windows 10)
* Installed **Java** (tested on version 11.0.16.1). Tool details: `https://docs.google.com/document/d/119VYxF8JIZIUSk7JjwEPNX1RVjHBGbXHBKuK_1ytJg4/edit?usp=sharing`
* Installed **Maven** (tested on version 3.8.5). Tool details: `https://docs.google.com/document/d/1cfIMcqkWlobUfVfTLQp7ixqEcOtoTR8X6OGo3cU4maw/edit?usp=sharing`
* Installed **Git** (tested on version 2.33.0.windows.2). Tool details: `https://docs.google.com/document/d/1Iyxy5DYfsrEZK5fxZJnYy5a1saARxd5LyMEscJKSHn0/edit?usp=sharing`

##### Preconditions - Actions
* **Download** and **install** tool **Otp** (required on Windows by Zipkin):
    * Download installation file from: `https://www.erlang.org/downloads`
    * Run downloaded file
* **Download**, **install** and **run** tool **RabbitMQ** (required by Zipkin):
     * Download installation file from: `https://www.rabbitmq.com/download.html`
     * Run downloaded file as administrator
     * Run RabbitMQ service with (Command Line tool in location: "{rabbitmq_home}/bin"): `rabbitmq-service.bat start`
     * Check RabbitMQ service with `http://localhost:15672`
* **Download** and **run** tool **Zipkin**:
      * Download package with tool from `https://zipkin.io/pages/quickstart.html`
      * Extract package
      * Start Zipkin with (GIT bash tool in location: "{zipkin_home}/bin"): `RABBIT_URI=amqp://localhost java -jar zipkin.jar`
      * Check Zipkin service with `http://localhost:9411`
* **Download** source code using Git 
* Open any **Command Line** (for instance "Windonw PowerShell" on Windows OS) tool on **project's folder** (exact localization of project you can check in GIT repositories on page `https://github.com/wisniewskikr/chrisblog-it-cloud`)


USAGE
-----

Usage steps:
1. First Command Line: Start Service Discovery with `mvn -f ./service-discovery spring-boot:run`
1. Second Command Line: Start Service Config with `mvn -f ./service-config spring-boot:run`
1. Third Command Line: Start Service HelloWorld 1 with `mvn -f ./service-helloworld-1 spring-boot:run`
1. Fourth Command Line: Start Service HelloWorld 2 with `mvn -f ./service-helloworld-2 spring-boot:run`
1. Fifth Command Line: Start Service Gateway with `mvn -f ./service-gateway spring-boot:run`
1. (Optional) In any browser check services list with `http://localhost:8761`
1. In any REST Client (for instance Postman) connect with Service HelloWorld via Service Gateway with (method GET): `http://localhost:8762/service-helloworld`
1. (Optional) In any Rest Client run following request many times to check load balancing (response port should be changed every request) (method GET): `http://localhost:8762/service-helloworld`
1. Fifth Command Line: Clean up environment with `ctrl + C`
1. Fourth Command Line: Clean up environment with `ctrl + C`
1. Third Command Line: Clean up environment with `ctrl + C`
1. Second Command Line: Clean up environment with `ctrl + C`
1. First Command Line: Clean up environment with `ctrl + C`








































Endpoint:
- http://localhost:8761

Launch:
- mvn -f ./service-discovery spring-boot:run
- mvn -f ./service-config spring-boot:run
- mvn -f ./service-helloworld-1 spring-boot:run
- mvn -f ./service-helloworld-2 spring-boot:run
- mvn -f ./service-gateway spring-boot:run

Ports:
- service-discovery: 8761
- service-config: 8888
- service-helloworld-1: 8080
- service-helloworld-2: 8081
- service-gateway: 8762
- service-traking: 9411
- service-logging:

You can start custom service by:
- Custom Service
- Discovery Service
- Gateway Service

Zipkin:
- download and install otp
- download and install rabbitmq (as administrator)
- use command: rabbitmq-plugins enable rabbitmq_management
- check rabbitmq: http://localhost:15672
- download zipkin: curl -sSL https://zipkin.io/quickstart.sh | bash -s
- start zipkin (GIT bash) with: RABBIT_URI=amqp://localhost java -jar zipkin.jar

OTP

Link: https://www.erlang.org/downloads

RABBITMQ

Link: https://www.rabbitmq.com/download.html

ZIPKIN

Link: https://zipkin.io/pages/quickstart.html


EKL

Elasticsearch:
- Download: https://www.elastic.co/downloads/elasticsearch
- Run: bin/elasticsearch.bat
- URL: http://localhost:9200

Kibana:
- Download: https://www.elastic.co/downloads/kibana
- Run: bin/kibana.bat
- URL: http://localhost:5601
- Kibana -> Dev Tools

PUT /javatechie
{
  "settings": {
  "index": {
    "number_of_shards" : 3,
    "number_of_replicas" : 2
  }
  }
}

POST /javatechie/default
{
  "name": "event_processing",
  "instructor": {
    "firstName": "java",
    "lastName": "techie"
  }
}

- Kibana -> Management - Index Pattern -> Create Index Pattern -> Pattern "javatechie*" -> "I don't want to use the Time Filter"
- Kibana -> Discover

Logstash:
- Download: https://www.elastic.co/downloads/logstash
- Create bin/logstash.conf

input {
	file {
		path => "C:\IT\logs\springcloud-simple-modules.log"
		start_position => "beginning"
	}
}

output {
	
	stdout {
		codec => rubydebug
	}
	
	elasticsearch {
		hosts => ["localhost:9200"]
		index => "javatechie-%{+yyyy.MM.dd}"
	}
	
}

- Run: bin/logstash.bat -f logstash.conf

