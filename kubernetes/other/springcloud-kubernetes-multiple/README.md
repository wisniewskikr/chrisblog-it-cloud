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
* **Launched** Minikube on local machine
* **Download** source code using Git 
* Open any **Command Line** (for instance "Windonw PowerShell" on Windows OS) tool on **project's folder** (exact localization of project you can check in GIT repositories on page `https://github.com/wisniewskikr/chrisblog-it-cloud`)


USAGE
-----

Usage steps:
1. In Command Line tool start all microservices (it takes about 5 minutes) with `kubectl apply -f kubernetes.yaml`
1. (Optional) In Command Line tool monitor if all Kubernetes Pods are ready (it takes about 5 minutes - expected "READY 1/1" for all Pods) with `kubectl get pods`
1. In Command Line tool start Gateway Service in browser with `minikube service service-display-kubernetes-show`
1. (Optional) In Command Line tool start HelloWorld Storage Service in browser wiht `minikube service service-storage-kubernetes-show`
1. Clean up environment:

    * In Command Line remove all microservices with `kubectl delete -f kubernetes.yaml`

(OPTIONAL) BUILD IMAGES AND PUSH THEM TO REMOTE REPOSITORY
----------------------------------------------------------

**Note!**:
* Please replace my **hub-docker-id** - **wisniewskikr** - with your unique **hub-docker-id**. In this way images will be pushed to your realm in the repository Hub Docker. 

Usage steps:
1. Build package with `mvn clean package -D maven.test.skip`
1. Build Service Display image with `docker build -f service-helloworld-display/Dockerfile-Fast -t wisniewskikr/springcloud-kubernetes-multiple-display-image ./service-helloworld-display`
1. Push Service Display image with `docker push wisniewskikr/springcloud-kubernetes-multiple-display-image`
1. Build Service Storage image with `docker build -f service-helloworld-storage/Dockerfile-Fast -t wisniewskikr/springcloud-kubernetes-multiple-storage-image ./service-helloworld-storage`
1. Push Service Storage image with `docker push wisniewskikr/springcloud-kubernetes-multiple-storage-image`

1. Clean up environment:
 
    * Remove Service Display image with `docker rmi wisniewskikr/springcloud-kubernetes-multiple-display-image`
    * Remove Service Display image with name **{image-name}** from your **hub-docker-id** remote repository `https://hub.docker.com`. For instance `springcloud-kubernetes-multiple-display-image`    
    * Remove Service Storage image with `docker rmi wisniewskikr/springcloud-kubernetes-multiple-storage-image`
    * Remove Service Storage image with name **{image-name}** from your **hub-docker-id** remote repository `https://hub.docker.com`. For instance `springcloud-kubernetes-multiple-storage-image`