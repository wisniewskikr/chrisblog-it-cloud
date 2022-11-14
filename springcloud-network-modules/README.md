DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to implement **microservices** using **Java** programming language and **Spring Boot Cloud** framework. This project consists of few microservices implemented as independent **Maven modules**. In the system there are two Hello World modules - Display and Storage - which are connected in the **network**. Network means that Service HelloWorld Display displays message received from Service HelloWorld Storage. Additionally service Storage is run as two instances to present load balancing usage. The rest of services in the system are provided by Spring Boot Cloud and they are used for system management. 

##### Service
This project consists of following services:
* **Service Discovery**: port **8761**. This service displays list of all active services in system
* **Service Config**: port **8888**. This service provides flexible configuration variables. These variables can be taken for instance from Github
* **Service HelloWorld Storage 1**: port **8081**. First instance of Service HelloWorld Storage which provides JSON with message and port
* **Service HelloWorld Storage 2**: port **8082**. Second instance of Service HelloWorld Storage which provides JSON with message and port
* **Service HelloWorld Display**: port **8080**. This service displays to the user three information: message from Storage, port from Storage and port from Display
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


PRECONDITIONS
-------------

##### Preconditions - Tools
* Installed **Operating System** (tested on Windows 10)
* Installed **Java** (tested on version 11.0.16.1). Tool details: `https://docs.google.com/document/d/119VYxF8JIZIUSk7JjwEPNX1RVjHBGbXHBKuK_1ytJg4/edit?usp=sharing`
* Installed **Maven** (tested on version 3.8.5). Tool details: `https://docs.google.com/document/d/1cfIMcqkWlobUfVfTLQp7ixqEcOtoTR8X6OGo3cU4maw/edit?usp=sharing`
* Installed **Git** (tested on version 2.33.0.windows.2). Tool details: `https://docs.google.com/document/d/1Iyxy5DYfsrEZK5fxZJnYy5a1saARxd5LyMEscJKSHn0/edit?usp=sharing`

##### Preconditions - Actions
* **Download** source code using Git 
* Open any **Command Line** (for instance "Windonw PowerShell" on Windows OS) tool on **project's folder** (exact localization of project you can check in GIT repositories on page `https://github.com/wisniewskikr/chrisblog-it-cloud`)


USAGE
-----

Usage steps:
1. First Command Line: Start Service Discovery with `mvn -f ./service-discovery spring-boot:run`
1. Second Command Line: Start Service Config with `mvn -f ./service-config spring-boot:run`
1. Third Command Line: Start Service HelloWorld Storage 1 with `mvn -f ./service-helloworld-storage-1 spring-boot:run`
1. Fourth Command Line: Start Service HelloWorld Storage 2 with `mvn -f ./service-helloworld-storage-2 spring-boot:run`
1. Fifth Command Line: Start Service HelloWorld Display with `mvn -f ./service-helloworld-display spring-boot:run`
1. Sixth Command Line: Start Service Gateway with `mvn -f ./service-gateway spring-boot:run`
1. (Optional) In any browser check services list with `http://localhost:8761`
1. In any REST Client (for instance Postman) connect with Service HelloWorld via Service Gateway with (method GET): `http://localhost:8762/service-helloworld-display`
1. (Optional) In any Rest Client run following request many times to check load balancing (port of Storage should be changed every request) (method GET): `http://localhost:8762/service-helloworld-display`
1. Sixth Command Line: Clean up environment with `ctrl + C`
1. Fifth Command Line: Clean up environment with `ctrl + C`
1. Fourth Command Line: Clean up environment with `ctrl + C`
1. Third Command Line: Clean up environment with `ctrl + C`
1. Second Command Line: Clean up environment with `ctrl + C`
1. First Command Line: Clean up environment with `ctrl + C`