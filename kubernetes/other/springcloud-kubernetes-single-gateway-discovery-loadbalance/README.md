DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to implement application in **Java** programming language with usage **Spring Boot Cloud** framework which advantage of **Discovery"" and **Gateway** services.

**Discovery** service (f.e. Eureka) contains information about all applications and their instances in system. So Load Balancer can use information about instances to redirect traffic between them. 

**Gateway** service is entry point to system for users. Users should always use URL to Gateway and then Gateway will redirect them to specific serivce.

All services are dockerized so you can run them using **Kubernetes** tool. 

##### Service
This project consists of following services:
* **Service Discovery**: port **8761**. This service displays list of all active services in system
* **Service HelloWorld**: ports **random**. Two instances of Service HelloWorld which provide JSON with message and application id
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
* **Java**: `https://docs.google.com/document/d/119VYxF8JIZIUSk7JjwEPNX1RVjHBGbXHBKuK_1ytJg4/edit?usp=sharing`
* **Maven**: `https://docs.google.com/document/d/1cfIMcqkWlobUfVfTLQp7ixqEcOtoTR8X6OGo3cU4maw/edit?usp=sharing`
* **Git**: `https://docs.google.com/document/d/1Iyxy5DYfsrEZK5fxZJnYy5a1saARxd5LyMEscJKSHn0/edit?usp=sharing`
* **Spring Boot**: `https://docs.google.com/document/d/1mvrJT5clbkr9yTj-AQ7YOXcqr2eHSEw2J8n9BMZIZKY/edit?usp=sharing`
* **Microservices**: `https://docs.google.com/document/d/1j_lwf5L0-yTPew75RIWcA6AGeAnJjx0M4Bk4DrUcLXc/edit?usp=sharing`
* **Docker**: `https://docs.google.com/document/d/1tKdfZIrNhTNWjlWcqUkg4lteI91EhBvaj6VDrhpnCnk/edit?usp=sharing`
* **Kubernetes**: `https://docs.google.com/document/d/1jOsK3Lkbkoq-Xx7Ln9o_ozCt6XpcSElOwu1o2AfQnNc/edit?usp=sharing`
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
1. Build Service Display image with `docker build -f service-helloworld/Dockerfile-Fast -t wisniewskikr/helloworld-image ./service-helloworld`
1. Build Service Storage image with `docker build -f service-gateway/Dockerfile-Fast -t wisniewskikr/gateway-image ./service-gateway`
1. (Optional) Check images in Minikube:

     * Run Minikube SSH with `minikube ssh`
     * Display Minikube images (expected 3 from this project) with `docker images`
     * Close Minikube SSH with `exit`

1. Start Discovery microservices with `kubectl apply -f 1-discovery.yaml`
1. Start Storage microservices with `kubectl apply -f 2-helloworld.yaml`
1. Start Display microservices with `kubectl apply -f 3-gateway.yaml`
1. Launch Gateway Service in browser with `minikube service service-gateway-show`
1. (Optional) Launch Discovery Service in browser with `minikube service service-discovery-show`
1. (Optional) Launch HelloWorld Service in browser with `minikube service service-helloworld-show`
1. Clean up environment:
    
    * Remove Gateway service with `kubectl delete -f 3-gateway.yaml`
    * Remove HelloWorld service with `kubectl delete -f 2-helloworld.yaml`
    * Remove Discovery service with `kubectl delete -f 1-discovery.yaml`
    * Remove Service Discovery image with `docker rmi wisniewskikr/discovery-image`
    * Remove Service HelloWorld image with `docker rmi wisniewskikr/helloworld-image`
    * Remove Service Gateway image with `docker rmi wisniewskikr/gateway-image`