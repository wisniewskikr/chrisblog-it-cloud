DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to implement **service** using **Java** programming language and **Spring Boot Cloud** framework. This project consists of few services implemented as independent **Maven modules**. In the system there are two Hello World modules - Display and Storage - which are connected in the **network**. Network means that Service HelloWorld Display displays message received from Service HelloWorld Storage. The rest of services in the system are provided by Spring Boot Cloud and they are used for system management.

All services are dockerized and manged by docker orchestration tool **Kubernetes**. It means that user does not have to start up manually all services one by one. It's done automatically by orchestration tool. 

##### Service
This project consists of following services:
* **Service Discovery**: port **8761**. This service displays list of all active services in system
* **Service Config**: port **8888**. This service provides flexible configuration variables. These variables can be taken for instance from Github
* **Service HelloWorld Storage**: port **random**: This service provides JSON with message and port
* **Service HelloWorld Display**: port **8080**. This service displays to the user three information: message from Storage, uuid from Storage and uuid from Display
* **Service Gateway**: port **8762**. This service redirects request from outside system to service inside system. It also takes care of load balancing

##### Flow
The following flow takes place in this project:
1. User via browser sends request to Service HellWorld Display for content. This request is not sent directly but through Service Gateway. 
1. Service Gateway takes location of all services in system from Service Discovery.
1. Service HelloWorld Display sends request for content to Service HelloWorld Storage. In this example system there are two instances of Storage. In such situation Service Gateway performs load balancing - first request is sent to Service HelloWorld Storage 1, second to Service HelloWorld Storage 2, third again to Service HelloWorld Storage 1 etc. 
1. Service HelloWorld Storage connects with Service Config for text of message. This text is taken from Github project
1. Service HelloWorld Storage sends response to Service HelloWorld Display
1. Service HelloWorld Display sends response to User via browser. This response contains message, port of Display and port of instance of Storage. 
After every request this port is changed because of Service Gateway and load balancing

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
* **Minikube**: `https://docs.google.com/document/d/1GfgN7tJNTIJCaSzexJdR_Lm_S9pF2YykcpgSQzAZWZo/edit?usp=sharing`


PRECONDITIONS
-------------

##### Preconditions - Tools
* Installed **Operating System** (tested on Windows 10)
* Installed **Java** (tested on version 11.0.16.1)
* Installed **Maven** (tested on version 3.8.5)
* Installed **Git** (tested on version 2.33.0.windows.2)
* Installed **Docker** (tested on version 20.10.21)
* Installed **Minikube** (tested on version v1.28.0)
* Installed **kubectl** (tested on version v4.5.4)

##### Preconditions - Actions
* **Launched** Docker on your local machine
* **Launched** Minikube on your local machine with (as administrator) `minikube start`
* **Connected** Minikube with Docker with (Windows - as administrator): `minikube docker-env` and `minikube docker-env | Invoke-Expression` or with (Linux) `eval $(minikube docker-env)` 
* **Download** source code using Git 
* Open any **Command Line** (for instance "Windonw PowerShell" on Windows OS) tool on **project's folder** (exact localization of project you can check in GIT repositories on page `https://github.com/wisniewskikr/chrisblog-it-cloud`)


USAGE
-----

> **NOTE:**  Please open Command Line tool as **administrator** on **main folder of project**.

Usage steps:
1. Connect Minikube and Docker with (Windows) `minikube docker-env | Invoke-Expression`
1. Build package with `mvn clean package -D maven.test.skip`
1. Build Service Discovery image with `docker build -f service-discovery/Dockerfile-Fast -t wisniewskikr/discovery-image ./service-discovery`
1. Build Service Config image with `docker build -f service-config/Dockerfile-Fast -t wisniewskikr/config-image ./service-config`
1. Build Service Storage image with `docker build -f service-helloworld-storage/Dockerfile-Fast -t wisniewskikr/helloworld-storage-image ./service-helloworld-storage`
1. Build Service Display image with `docker build -f service-helloworld-display/Dockerfile-Fast -t wisniewskikr/helloworld-display-image ./service-helloworld-display`
1. Build Service Gateway image with `docker build -f service-gateway/Dockerfile-Fast -t wisniewskikr/gateway-image ./service-gateway`
1. (Optional) Check images in Minikube:

     * Run Minikube SSH with `minikube ssh`
     * Display Minikube images (expected new images from this project) with `docker images`
     * Close Minikube SSH with `exit`

1. Start Discovery service with `kubectl apply -f 1-discovery.yaml`
1. Start Config service with `kubectl apply -f 2-config.yaml`
1. Start Storage service with `kubectl apply -f 3-storage.yaml`
1. Start Display service with `kubectl apply -f 4-display.yaml`
1. Start Gateway service with `kubectl apply -f 5-gateway.yaml`
1. (Optional) Check status of services with `kubectl get pods`
1. Launch Gateway Service in browser with `minikube service service-gateway-show`
1. (Optional) Launch Discovery Service in browser with `minikube service service-discovery-show`
1. (Optional) Launch Storage Service in browser with `minikube service service-helloworld-storage-show`
1. (Optional) Launch Display Service in browser with `minikube service service-helloworld-display-show`
1. Clean up environment:
    
    * Remove Gateway service with `kubectl delete -f 5-gateway.yaml`
    * Remove Display service with `kubectl delete -f 4-display.yaml`
    * Remove Storage service with `kubectl delete -f 3-storage.yaml`
    * Remove Config service with `kubectl delete -f 2-config.yaml`
    * Remove Discovery service with `kubectl delete -f 1-discovery.yaml`
    * Remove Service Discovery image with `docker rmi wisniewskikr/discovery-image`
    * Remove Service Dispay image with `docker rmi wisniewskikr/helloworld-display-image`
    * Remove Service Storage image with `docker rmi wisniewskikr/helloworld-storage-image`
    * Remove Service Gateway image with `docker rmi wisniewskikr/gateway-image`
    * Remove Service Config image with `docker rmi wisniewskikr/config-image`