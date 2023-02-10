DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to implement **multiple** applications in **Java** programming language with usage **Spring Boot Cloud** framework which will communicate each other and will be configured by **separate Docker Compose** files. 

##### Service
This project consists of following services:
* **Service HelloWorld Storage**: port **8081**: This service provides JSON with message and port
* **Service HelloWorld Display**: port **8080**. This service displays to the user three information: message from Storage, uuid from Storage and uuid from Display

##### Flow
The following flow takes place in this project:
1. User via any browser sends request to Service Display for content
1. Service Display sends request to Service Storage for content
1. Service Storage sends back response to Service Display with message and storage port
1. Service Display sends back response to User via browser with message, storage port and display port

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
* **Launched** Docker on your local machine
* **Download** source code using Git with `git clone https://github.com/wisniewskikr/chrisblog-it-cloud.git`
* Open any **Command Line** (for instance "Windonw PowerShell" on Windows OS) tool on the **project's folder**.

USAGE

Usage steps:
1. Build packages with `mvn clean package -D maven.test.skip`
1. Start Storage service with `docker-compose -f docker-compose-storage.yml up -d --build`
1. Start Display service with `docker-compose -f docker-compose-display.yml up -d --build`
1. Visit `http://localhost:8080`
1. Clean local environment:
     * Remove Storage service with `docker-compose -f docker-compose-storage.yml down`
     * Remove Display service with `docker-compose -f docker-compose-display.yml down`