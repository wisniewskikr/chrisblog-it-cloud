USAGE
-----

> **NOTE:** Tools **Java**, **Maven** and **Docker** have to be installed. Tool **Docker** has to be up and running. Please open Command Line tool on **main folder of project**.

Usage steps:
1. Build packages with `mvn clean package -D maven.test.skip`
1. Start services with `docker-compose up -d --build`
1. Visit service HelloWorld via service Gateway with `http://localhost:8762`
     * Expected Result: JSON with message "Hello World 1", port "8080" and uuid - data from job started automatically on load
1. Visit again service HelloWorld via service Gateway with `http://localhost:8762/job`
      * Expected Result: JSON with message "Hello World 1,Hello World 2", port "8080" and uuid - data from job started manually
1. (Optional) Check service Batch database (url: jdbc:h2:mem:db-embedded;DB_CLOSE_DELAY=-1, user: admin, password: admin123) with `http://localhost:9090/console`
1. (Optional) Visit service HelloWorld directly with `http://localhost:8080`
1. (Optional) Visit service Discovery with `http://localhost:8761`
1. Clear local environment
     * Remove services with `docker-compose down --rmi local`


DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to implement **microservices** in **Java** programming language with usage **Spring Boot Cloud** framework. These microservices use **jobs** from library **batch**. Jobs are log and time consuming tasks which can be run in asynchronous way in the background - user does not wait for results. One job consists of one or more steps.

Types of batch:
* **RPW (Read-Process-Write)**: in this type steps use: reader, processor and writer. It's good for large amount of data because they divide them into "chunks" 
* **Tasklet**: in this type step is one action. It's good for simple tasks

Batch can be executed in following ways:
* **On load**: jobs are executed when application sets up
* **Manually**: jobs are executed on calling some endpoint by user
* **By scheduler**: jobs are executed by scheduler on some specific time

This example shows how to use batch in types RPW and Tasklet. RPW reads message "Hello World" from CSV file and stores it in database. Tasklet reads messages from database and adds them id. These steps will be run "on load" and "manually". 

Project will be configured and run by orchestration tool called **Docker Compose**.

##### Services
This project consists of following services:
* **Service Discovery**: port **8761**. This service contains information about all services registered in the system. Main tasks:
     * **Checking status**: this service can be used to check status of services in the system 
     * **Load balancing**: this service can be used by load balancers to identify services and their instances in the system
* **Service Gateway**: port **8762**. This service redirects traffic from outside system to inside system. Main tasks:
     * **Redirecting**: this service can redirect requests from outside system to some services inside system
     * **Load balancing**: this service can take care of load balancing requests from outside system to services inside system basing on information from service Discovery
* **Service HelloWorld**: port **8080**. This service provides message, port and uuid
* **Service Batch**: port **9090**. This service contains jobs to run

##### Flow
The following flow takes place in this project:
1. Service Batch runs job on start. It takes message from CSV file and store it in database
1. User via Browser sends request to Service Gateway for content
1. Service Gateway sends request to Service HelloWorld for content
1. Service HelloWorld sends request to Service Batch for content
1. Service Batch reads data from database. These data were created from CSV file on start by predefined job
1. Service Batch sends back response to Service HelloWorld with message
1. Service HelloWorld sends back response to Service Gateway with message, port and uuid
1. Service Gateway sends back response to User via Browser with message, port and uuid

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