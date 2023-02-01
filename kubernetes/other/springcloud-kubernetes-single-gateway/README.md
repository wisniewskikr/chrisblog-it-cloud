DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to implement application in **Java** programming language with usage **Spring Boot Cloud** framework which will use **Gateway** to redirect traffic to other services.

**Gateway** service is entry point to system for users. Users should always use URL to Gateway and then Gateway will redirect them to specific service.

All services are dockerized so you can run them using **Kubernetes** tool. 

##### Service
This project consists of following services:
* **Service HelloWorld**: port **8080**. This service provides JSON with message and application id
* **Service Gateway**: port **8762**. This service redirects request from outside system to service inside system.

##### Flow
The following flow takes place in this project:
1. User via browser sends request to Service Gateway for content
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
* **Connected** Minikube with Docker with (Windows - as administrator) `minikube docker-env | Invoke-Expression` or with (Linux) `eval $(minikube docker-env)` 
* **Download** source code using Git 
* Open any **Command Line** (for instance "Windonw PowerShell" on Windows OS) tool on **project's folder** (exact localization of project you can check in GIT repositories on page `https://github.com/wisniewskikr/chrisblog-it-cloud`)


USAGE
-----

> **NOTE:**  Please open Command Line tool as **administrator** on **main folder of project**. Please make sure that **Docker** and **Minikube** are running. Please make sure that Docker and Minikube are connected with command **minikube docker-env | Invoke-Expression** (Windows) or **eval $(minikube docker-env)** (Linux) - it's required for Kubernetes property **imagePullPolicy: Never**.

Usage steps:
1. Build package with `mvn clean package -D maven.test.skip`
1. Build Service HelloWorld image with `docker build -f service-helloworld/Dockerfile-Fast -t wisniewskikr/springcloud-kubernetes-single-gateway-helloworld-image ./service-helloworld`
1. Build Service Gateway image with `docker build -f service-gateway/Dockerfile-Fast -t wisniewskikr/springcloud-kubernetes-single-gateway-gateway-image ./service-gateway`
1. Start Storage service with `kubectl apply -f 1-helloworld.yaml`
1. Start Display service with `kubectl apply -f 2-gateway.yaml`
1. (Optional) Check if services are running with (expected READY 1/1) `kubectl get pods` 
1. Launch Gateway Service in browser with `minikube service service-gateway-show`
1. (Optional) Launch HelloWorld Service in browser with `minikube service service-helloworld-show`
1. Clean up environment:

    * Remove Display service with `kubectl delete -f 2-gateway.yaml`
    * Remove Storage service with `kubectl delete -f 1-helloworld.yaml`
    * Remove Service Display image with `docker rmi wisniewskikr/springcloud-kubernetes-single-gateway-helloworld-image`
    * Remove Service Storage image with `docker rmi wisniewskikr/springcloud-kubernetes-single-gateway-gateway-image`