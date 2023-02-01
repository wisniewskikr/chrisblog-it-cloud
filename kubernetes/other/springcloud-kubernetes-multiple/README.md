DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to implement application in **Java** programming language with usage **Spring Boot Cloud** framework which connects **multiple** services each other.

All services are dockerized and pushed to my public remote repository so you can run them using **Kubernetes** tool. 

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

> **NOTE:**  Please run all commands in the same Command Line tool where you connected Minikube to Docker (check section **Preconditions**). Provided YAML files have properties  **imagePullPolicy: Never** so it means that images have to be stored in Minikube. To do it Minikube has to be connected with Docker before building images.

Usage steps:
1. Build package with `mvn clean package -D maven.test.skip`
1. Build Service Display image with `docker build -f service-helloworld-display/Dockerfile-Fast -t wisniewskikr/springcloud-kubernetes-multiple-display-image ./service-helloworld-display`
1. Build Service Storage image with `docker build -f service-helloworld-storage/Dockerfile-Fast -t wisniewskikr/springcloud-kubernetes-multiple-storage-image ./service-helloworld-storage`
1. Start Storage service with `kubectl apply -f 1-storage.yaml`
1. Start Display service with `kubectl apply -f 2-display.yaml`
1. Launch Display Service in browser with `minikube service service-display-kubernetes-show`
1. (Optional) Launch Storage Service in browser with `minikube service service-storage-kubernetes-show`
1. Clean up environment:

    * Remove Display service with `kubectl delete -f 2-display.yaml`
    * Remove Storage service with `kubectl delete -f 1-storage.yaml`
    * Remove Service Display image with `docker rmi wisniewskikr/springcloud-kubernetes-multiple-display-image`
    * Remove Service Storage image with `docker rmi wisniewskikr/springcloud-kubernetes-multiple-storage-image`
