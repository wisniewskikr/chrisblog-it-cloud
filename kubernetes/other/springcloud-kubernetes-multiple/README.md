DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to implement application in **Java** programming language with usage **Spring Boot Cloud** framework which connects **multiple** services each other.

All services are dockerized so you can run them using **Docker** tool. 

##### Service
This project consists of following services:
* **Service HelloWorld Storage**: port **9090**: This service provides JSON with message and port
* **Service HelloWorld Display**: port **8080**. This service displays to the user three information: message from Storage, uuid from Storage and uuid from Display

##### Flow
The following flow takes place in this project:
1. User via browser sends request to service Display
1. Service Display sends request to service Storage for text "Hello World", storage uuid and storage port
1. Service Storage sends back response to service Display
1. Service Display sends back response to User via browser with text, display uuid, display port, storage uuid and storage port 

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
1. Build Service Storage HelloWorld image with `docker build -f service-helloworld-storage/Dockerfile-Fast -t service-helloworld-storage-image ./service-helloworld-storage`
1. Start Service Storage HelloWorld container with `docker run -d -p 9090:9090 --network helloworld-network -e spring.application.name=service-helloworld-storage -e server.port=9090 -e management.endpoints.jmx.exposure.include=health,info,env,beans -e management.endpoints.web.exposure.include=health,info,env,beans -e service.helloworld.message="Hello World" --name service-helloworld-storage-container service-helloworld-storage-image`
1. Build Service Display HelloWorld image with `docker build -f service-helloworld-display/Dockerfile-Fast -t service-helloworld-display-image ./service-helloworld-display`
1. Start Service Display HelloWorld container with `docker run -d -p 8080:8080 --network helloworld-network -e spring.application.name=service-helloworld-display -e server.port=8080 -e management.endpoints.jmx.exposure.include=health,info,env,beans -e management.endpoints.web.exposure.include=health,info,env,beans -e service.helloworld.storage.name=service-helloworld-storage-container -e service.helloworld.storage.url=http://service-helloworld-storage-container:9090 --name service-helloworld-display-container service-helloworld-display-image`
1. Visit `http://localhost:8080` 
1. Clean up environment:

    * Remove Service HelloWorld container with `docker rm -f service-helloworld-storage-container`
    * Remove Service HelloWorld container with `docker rm -f service-helloworld-display-container`
    * Remove Service HelloWorld image with `docker rmi service-helloworld-storage-image`
    * Remove Service HelloWorld image with `docker rmi service-helloworld-display-image`
    * Remove network with `docker network rm helloworld-network`