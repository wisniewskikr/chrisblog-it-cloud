DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to implement **microservices** in **Java** programming language with usage **Spring Boot Cloud** framework which will use **Vault** tool to store secrets.

**Vault** enables to store secrets. Spring Cloud enables to load secrets together with external configuration from Git.

##### Service
This project consists of following services:
* **Service Discovery**: port **8761**. This service displays list of all active services in system
* **Service Config**: port **8888**. This service provides flexible configuration variables. These variables can be taken for instance from Github
* **Service HelloWorld**: port **8080**. Two instances of Service HelloWorld which provide JSON with message and application id
* **Service Gateway**: port **8762**. This service redirects request from outside system to service inside system. It also takes care of load balancing

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
* Installed **Java** (tested on version 11.0.16.1)
* Installed **Maven** (tested on version 3.8.5)
* Installed **Git** (tested on version 2.33.0.windows.2)
* Installed **Docker** (tested on version 20.10.21)
* Installed **Docker Compose** (tested on version v2.12.2)

##### Preconditions - Actions
* **Launched** Docker and Docker Compose tools on your local machine
* **Download** source code using Git 
* Open any **Command Line** (for instance "Windonw PowerShell" on Windows OS) tool on **project's folder** (exact localization of project you can check in GIT repositories on page `https://github.com/wisniewskikr/chrisblog-it-cloud`)


USAGE
-----

> NOTE: Please use **bash** command line tool. Commands for Vault **don't work** on PowerShell tool.

Usage steps:
1. Start Vault with `docker-compose -f docker-compose-vault.yml up -d`
1. Configure Vault (please check section **Usage Vault**)
1. Update property **spring.cloud.vault.token** in file **docker-compose.yml**
1. Build packages with `mvn clean package -D maven.test.skip`
1. Start services with `docker-compose up -d --build`
1. Visit: `http://localhost:8762`
1. (Optional) Check services list with `http://localhost:8761`
1. Clean local environment:
     * Remove services with `docker-compose down --rmi local`
     * Remove Vault with `docker-compose -f docker-compose-vault.yml down --rmi local`     


USAGE VAULT
-----------

> NOTE: Vault application is up and running on local environment. How to do it please check section **Usage**. Additionally **path** has te be **secret** and **path for this secret** has to be name of Spring Boot application.

Usage steps:
1. Open Vault console with `http:\\localhost:8200`

![My Image](vault-1.png)

![My Image](vault-2.png)

![My Image](vault-3.png)

![My Image](vault-4.png)

![My Image](vault-5.png)

![My Image](vault-6.png)

![My Image](vault-7.png)

![My Image](vault-8.png)

![My Image](vault-9.png)

![My Image](vault-10.png)

![My Image](vault-11.png)

![My Image](vault-12.png)