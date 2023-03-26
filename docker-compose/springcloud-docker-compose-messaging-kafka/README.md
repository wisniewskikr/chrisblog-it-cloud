USAGE
-----

> **NOTE:** Tools **Java**, **Maven** and **Docker** have to be installed. Tool **Docker** has to be up and running. Please open Command Line tool on **main folder of project**.

Usage steps:
1. Build packages with `mvn clean package -D maven.test.skip`
1. Start elements using Docker Compose with `docker-compose up --build -d`
     * (Optional) Check if all Containers are up and ready with `docker ps -a` 
1. Publish message via service Gateway with `http://localhost:8762/publish`
1. Subscribe message via service Gateway with `http://localhost:8762/subscribe`
1. (Optional) Without service Gateway:
    * (Optional) Publish message directly with `http://localhost:9090/publish`
    * (Optional) Subscribe message directly with `http://localhost:8080/subscribe`
    * (Optional) Check services in service Discovery with `http://localhost:8761`
1. Clear local environment
     * Remove services with `docker-compose down --rmi local`


DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to implement **message archirecture** with **Kafka broker** in **Java microservice system**. Publisher and Subscriber are applications in **Java** programming language with usage **Spring Boot Cloud** framework.

Project will be configured and run by orchestration tool called **Docker Compose**.

##### Services
This project consists of following services:
* **Service Discovery**: port **8761**. This service contains information about all services registered in the system. Main tasks:
     * **Checking status**: this service can be used to check status of services in the system 
     * **Load balancing**: this service can be used by load balancers to identify services and their instances in the system
* **Service Gateway**: port **8762**. This service redirects traffic from outside system to inside system. Main tasks:
     * **Redirecting**: this service can redirect requests from outside system to some services inside system
     * **Load balancing**: this service can take care of load balancing requests from outside system to services inside system basing on information from service Discovery
* **Service Publisher**: port **9090**. This service publishes message, port and uuid
* **Service Subscriber**: port **8080**. This service subscribes message and displays message, port of BE, uuid of BE, port of FE and uuid of FE
* **Kafka**: port **9092**. This is Event Broker which provides messages from Publisher to Subscriber
* **Zookeeper**: port **2181**. This is discovery service for Kafka. All Kafka elements are registered here

##### Flow
The following flow takes place in this project:
1. User via Browser sends request to Service Gateway for content
1. Service Gateway sends request to Service Publisher for publishing message
1. Service Publisher publishes message. Service Subscriber contains listener which stored message in List
1. Service Subscriber subscribes message. Message is read from List
1. Service Subscriber sends back response to Service Gateway with message, port of BE, uuid of BE, port of FE and uuid of FE
1. Service Gateway sends back response to User via Browser with message, port of BE, uuid of BE, port of FE and uuid of FE

##### Launch
To launch this application please make sure that the **Preconditions** are met and then follow instructions from **Usage** section.

##### Technologies
This project uses following technologies:
* **Java**: `https://docs.google.com/document/d/119VYxF8JIZIUSk7JjwEPNX1RVjHBGbXHBKuK_1ytJg4/edit?usp=sharing`
* **Maven**: `https://docs.google.com/document/d/1cfIMcqkWlobUfVfTLQp7ixqEcOtoTR8X6OGo3cU4maw/edit?usp=sharing`
* **Git**: `https://docs.google.com/document/d/1Iyxy5DYfsrEZK5fxZJnYy5a1saARxd5LyMEscJKSHn0/edit?usp=sharing`
* **Spring Boot**: `https://docs.google.com/document/d/1mvrJT5clbkr9yTj-AQ7YOXcqr2eHSEw2J8n9BMZIZKY/edit?usp=sharing`
* **Microservices**: `https://docs.google.com/document/d/1j_lwf5L0-yTPew75RIWcA6AGeAnJjx0M4Bk4DrUcLXc/edit?usp=sharing`
* **Docker**: `https://docs.google.com/document/d/1tKdfZIrNhTNWjlWcqUkg4lteI91EhBvaj6VDrhpnCnk/edit?usp=sharing`
* **Docker Compose**: `https://docs.google.com/document/d/1SPrCS5OS_G0je_wmcLGrX8cFv7ZkQbb5uztNc9kElS4/edit?usp=sharing`
* **Kafka**: `https://docs.google.com/document/d/1pDBnFbpvo0mNaIgxLCV--3qUn-wf0vHZiTYRQL05Wes/edit?usp=sharing`


PRECONDITIONS
-------------

##### Preconditions - Tools
* Installed **Operating System** (tested on Windows 10)
* Installed **Java** (tested on version 17.0.5)
* Installed **Maven** (tested on version 3.8.5)
* Installed **Git** (tested on version 2.33.0.windows.2)
* Installed **Docker** (tested on version 20.10.21)
* Installed **Docker Compose** (tested on version v2.12.2)

##### Preconditions - Actions
* **Launched** Docker on your local machine
* **Download** source code using Git command `git clone https://github.com/wisniewskikr/chrisblog-it-cloud.git`
* Open any **Command Line** tool (for instance "Windonw PowerShell" on Windows OS) on **project's main folder**