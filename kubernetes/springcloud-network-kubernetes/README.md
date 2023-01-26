DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to implement **microservices** using **Java** programming language and **Spring Boot Cloud** framework. This project consists of few microservices implemented as independent **Maven modules**. In the system there are two Hello World modules - Display and Storage - which are connected in the **network**. Network means that Service HelloWorld Display displays message received from Service HelloWorld Storage. The rest of services in the system are provided by Spring Boot Cloud and they are used for system management.

All services are dockerized and manged by docker orchestration tool **docker compose**. It means that user does not have to start up manually all services one by one. It's done automatically by orchestration tool. 

##### Service
This project consists of following services:
* **Service Discovery**: port **8761**. This service displays list of all active services in system
* **Service Config**: port **8888**. This service provides flexible configuration variables. These variables can be taken for instance from Github
* **Service HelloWorld Storage**: port **random**: This service provides JSON with message and port
* **Service HelloWorld Display**: port **8080**. This service displays to the user three information: message from Storage, uuid from Storage and uuid from Display
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
* **Docker**: `https://docs.google.com/document/d/1tKdfZIrNhTNWjlWcqUkg4lteI91EhBvaj6VDrhpnCnk/edit?usp=sharing`
* **Docker Compose**: `https://docs.google.com/document/d/1SPrCS5OS_G0je_wmcLGrX8cFv7ZkQbb5uztNc9kElS4/edit?usp=sharing`


PRECONDITIONS
-------------

##### Preconditions - Tools
* Installed **Operating System** (tested on Windows 10)
* Installed **Java** (tested on version 11.0.16.1). Tool details: `https://docs.google.com/document/d/119VYxF8JIZIUSk7JjwEPNX1RVjHBGbXHBKuK_1ytJg4/edit?usp=sharing`
* Installed **Maven** (tested on version 3.8.5). Tool details: `https://docs.google.com/document/d/1cfIMcqkWlobUfVfTLQp7ixqEcOtoTR8X6OGo3cU4maw/edit?usp=sharing`
* Installed **Git** (tested on version 2.33.0.windows.2). Tool details: `https://docs.google.com/document/d/1Iyxy5DYfsrEZK5fxZJnYy5a1saARxd5LyMEscJKSHn0/edit?usp=sharing`
* Installed **Docker** (tested on version 20.10.21). Tool details: `https://docs.google.com/document/d/1tKdfZIrNhTNWjlWcqUkg4lteI91EhBvaj6VDrhpnCnk/edit?usp=sharing`
* Installed **Docker Compose** (tested on version v2.12.2). Tool details: `https://docs.google.com/document/d/1SPrCS5OS_G0je_wmcLGrX8cFv7ZkQbb5uztNc9kElS4/edit?usp=sharing`

##### Preconditions - Actions
* **Launched** Docker and Docker Compose tools on your local machine
* **Download** source code using Git 
* Open any **Command Line** (for instance "Windonw PowerShell" on Windows OS) tool on **project's folder** (exact localization of project you can check in GIT repositories on page `https://github.com/wisniewskikr/chrisblog-it-cloud`)


USAGE
-----

Usage steps:
1. In Command Line tool start all microservices (it takes about 5 minutes) with `kubectl apply -f kubernetes.yaml`
1. (Optional) In Command Line tool monitor if all Kubernetes Pods are ready (it takes about 5 minutes - expected "READY 1/1" for all Pods) with `kubectl get pods`
1. In Command Line tool start Gateway Service in browser with `minikube service service-gateway`
1. In browser add suffix **/service-helloworld-display** to call HelloWorld Service via Gateway Service. It should look like this: `http://192.168.1.66:31000/service-helloworld-display` 
1. (Optional) In Command Line tool start Discovery Service in browser wiht `minikube service service-discovery-display`
1. (Optional) In Command Line tool start HelloWorld Display Service in browser wiht `minikube service service-helloworld-display-display`
1. (Optional) In Command Line tool start HelloWorld Storage Service in browser wiht `minikube service service-helloworld-storage-display`
1. Clean up environment:

    * In Command Line remove all microservices with `kubectl delete -f kubernetes.yaml`


(OPTIONAL) BUILD IMAGES AND PUSH THEM TO REMOTE REPOSITORY
----------------------------------------------------------

**Note!**:
* Please replace my **hub-docker-id** - **wisniewskikr** - with your unique **hub-docker-id**. In this way images will be pushed to your realm in the repository Hub Docker. 

Usage steps:
1. Build package with `mvn clean package -D maven.test.skip`
1. Build Service Config image with **docker build -f service-config/Dockerfile-Fast -t {hub-docker-id}/service-config-image ./service-config** . For instance with `docker build -f service-config/Dockerfile-Fast -t wisniewskikr/springcloud-network-kubernetes-service-config-image ./service-config`
1. Push Service Config image with **docker push {hub-docker-id}/springcloud-network-kubernetes-service-config-image** . For instance with `docker push wisniewskikr/springcloud-network-kubernetes-service-config-image`
1. Build Service Discovery image with **docker build -f service-discovery/Dockerfile-Fast -t {hub-docker-id}/service-discovery-image ./service-discovery** . For instance with `docker build -f service-config/Dockerfile-Fast -t wisniewskikr/springcloud-network-kubernetes-service-discovery-image ./service-discovery`
1. Push Service Discovery image with **docker push {hub-docker-id}/springcloud-network-kubernetes-service-discovery-image** . For instance with `docker push wisniewskikr/springcloud-network-kubernetes-service-discovery-image`
1. Build Service Gateway image with **docker build -f service-gateway/Dockerfile-Fast -t {hub-docker-id}/service-gateway-image ./service-gateway** . For instance with `docker build -f service-gateway/Dockerfile-Fast -t wisniewskikr/springcloud-network-kubernetes-service-gateway-image ./service-gateway`
1. Push Service Gateway image with **docker push {hub-docker-id}/springcloud-network-kubernetes-service-gateway-image** . For instance with `docker push wisniewskikr/springcloud-network-kubernetes-service-gateway-image`
1. Build Service Display image with **docker build -f service-helloworld-display/Dockerfile-Fast -t {hub-docker-id}/service-helloworld-display-image ./service-helloworld-display** . For instance with `docker build -f service-helloworld-display/Dockerfile-Fast -t wisniewskikr/springcloud-network-kubernetes-service-helloworld-display-image ./service-helloworld-display`
1. Push Service Display image with **docker push {hub-docker-id}/springcloud-network-kubernetes-service-helloworld-display-image** . For instance with `docker push wisniewskikr/springcloud-network-kubernetes-service-helloworld-display-image`
1. Build Service Storage image with **docker build -f service-helloworld-storage/Dockerfile-Fast -t {hub-docker-id}/service-helloworld-storage-image ./service-helloworld-storage** . For instance with `docker build -f service-helloworld-storage/Dockerfile-Fast -t wisniewskikr/springcloud-network-kubernetes-service-helloworld-storage-image ./service-helloworld-storage`
1. Push Service Storage image with **docker push {hub-docker-id}/springcloud-network-kubernetes-service-helloworld-storage-image** . For instance with `docker push wisniewskikr/springcloud-network-kubernetes-service-helloworld-storage-image`

1. Clean up environment:

    * Remove Service Config image with **docker rmi {hub-docker-id}/{image-name}**. For instance with `docker rmi wisniewskikr/springcloud-network-kubernetes-service-config-image`
    * Remove Service Config image with name **{image-name}** from your **hub-docker-id** remote repository `https://hub.docker.com`. For instance `springcloud-network-kubernetes-service-config-image`
    * Remove Service Discovery image with **docker rmi {hub-docker-id}/{image-name}**. For instance with `docker rmi wisniewskikr/springcloud-network-kubernetes-service-discovery-image`
    * Remove Service Discovery image with name **{image-name}** from your **hub-docker-id** remote repository `https://hub.docker.com`. For instance `springcloud-network-kubernetes-service-discovery-image`
    * Remove Service Gateway image with **docker rmi {hub-docker-id}/{image-name}**. For instance with `docker rmi wisniewskikr/springcloud-network-kubernetes-service-gateway-image`
    * Remove Service Gateway image with name **{image-name}** from your **hub-docker-id** remote repository `https://hub.docker.com`. For instance `springcloud-network-kubernetes-service-gateway-image`    
    * Remove Service Display image with **docker rmi {hub-docker-id}/{image-name}**. For instance with `docker rmi wisniewskikr/springcloud-network-kubernetes-service-helloworld-display-image`
    * Remove Service Display image with name **{image-name}** from your **hub-docker-id** remote repository `https://hub.docker.com`. For instance `springcloud-network-kubernetes-service-helloworld-display-image`    
    * Remove Service Storage image with **docker rmi {hub-docker-id}/{image-name}**. For instance with `docker rmi wisniewskikr/springcloud-network-kubernetes-service-helloworld-storage-image`
    * Remove Service Storage image with name **{image-name}** from your **hub-docker-id** remote repository `https://hub.docker.com`. For instance `springcloud-network-kubernetes-service-helloworld-storage-image`