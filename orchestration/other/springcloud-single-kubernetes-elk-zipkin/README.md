USAGE
-----

> **NOTE:**  Please open Command Line tool as **administrator** on **main folder of project**.

Usage steps:
1. Start Minikube with `minikube start --cpus 4 --memory 7000`
1. Connect Minikube and Docker with (Windows) `minikube docker-env | Invoke-Expression`
1. Pull Elasticsearch image with `docker pull docker.elastic.co/elasticsearch/elasticsearch:7.12.1`
1. Pull Kibana image with `docker pull docker.elastic.co/kibana/kibana:7.12.1`
1. Pull Filebeat image with `docker pull docker.elastic.co/beats/filebeat:7.12.0`
1. Pull Zipkin image with `docker pull openzipkin/zipkin`
1. Build package with `mvn clean package -D maven.test.skip`
1. Build Service Discovery image with `docker build -f service-discovery/Dockerfile-Fast -t wisniewskikr/discovery-image ./service-discovery`
1. Build Service Config image with `docker build -f service-config/Dockerfile-Fast -t wisniewskikr/config-image ./service-config`
1. Build Service HelloWorld image with `docker build -f service-helloworld/Dockerfile-Fast -t wisniewskikr/helloworld-image ./service-helloworld`
1. Build Service Gateway image with `docker build -f service-gateway/Dockerfile-Fast -t wisniewskikr/gateway-image ./service-gateway`
1. (Optional) Check images in Minikube:

     * Run Minikube SSH with `minikube ssh`
     * Display Minikube images (expected new images from this project) with `docker images`
     * Close Minikube SSH with `exit`

1. Start Elasticsearch service with `kubectl apply -f 1-elasticsearch.yaml`
1. Start Kibana service with `kubectl apply -f 2-kibana.yaml`
1. Start Logstash service with `kubectl apply -f 3-filebeat.yaml`
1. Start Zipkin service with `kubectl apply -f 4-zipkin.yaml`
1. Start Discovery service with `kubectl apply -f 5-discovery.yaml`
1. Start Config service with `kubectl apply -f 6-config.yaml`
1. Start Storage service with `kubectl apply -f 7-helloworld.yaml`
1. Start Gateway service with `kubectl apply -f 8-gateway.yaml`
1. (Optional) Check status of services with `kubectl get pods`
1. Launch Gateway Service in browser with `minikube service service-gateway-show`
1. Launch Zipkin Service in browser with `minikube service service-zipkin-show`
1. (Optional) Launch Discovery Service in browser with `minikube service service-discovery-show`
1. (Optional) Launch HelloWorld Service in browser with `minikube service service-helloworld-show`
1. Clean up environment:
    
    * Remove Gateway service with `kubectl delete -f 8-gateway.yaml`
    * Remove HelloWorld service with `kubectl delete -f 7-helloworld.yaml`
    * Remove Config service with `kubectl delete -f 6-config.yaml`
    * Remove Discovery service with `kubectl delete -f 5-discovery.yaml`
    * Remove Zipkin service with `kubectl delete -f 4-zipkin.yaml`
    * Remove Logstash service with `kubectl delete -f 3-logstash.yaml`
    * Remove Kibana service with `kubectl delete -f 2-kibana.yaml`
    * Remove Elasticsearch service with `kubectl delete -f 1-elasticsearch.yaml`
    * Remove Service Discovery image with `docker rmi wisniewskikr/discovery-image`
    * Remove Service HelloWorld image with `docker rmi wisniewskikr/helloworld-image`
    * Remove Service Gateway image with `docker rmi wisniewskikr/gateway-image`
    * Remove Service Config image with `docker rmi wisniewskikr/config-image`
    * Remove Service Elasticsearch image with `docker rmi docker.elastic.co/elasticsearch/elasticsearch:7.12.1`
    * Remove Service Kibana image with `docker rmi docker.elastic.co/kibana/kibana:7.12.1`
    * Remove Service Logstash image with `docker rmi docker.elastic.co/logstash/logstash:7.12.1`
    * Remove Service Zipkin image with `docker rmi openzipkin/zipkin`


TODO
----

Tasks to do:
* `kubectl apply -f 0-volumes.yaml`: does not work - implement PersistentVolume and PersistentVolumeClaim
* `kubectl apply -f 1-elasticsearch.yaml`: does not work - problem with pulling. Connect minikube with kubernetes? Try on kubernetes?
* `kubectl apply -f 2-kibana.yaml`: does not work - problem with pulling. Connect minikube with kubernetes? Try on kubernetes?
* `kubectl apply -f 3-logstash.yaml`: does not work - problem with pulling. Connect minikube with kubernetes? Try on kubernetes?
* `kubectl apply -f 4-zipkin.yaml`: works ok
* comment logback-spring.xml and check if Zipkin works ok
* Check container building on Docker Kubernetes, not on Minikube - maybe there won't be any problem with pulling images


DESCRIPTION
-----------

##### Goal
The goal of this project is to present how to implement **microservices** using **Java** programming language and **Spring Boot Cloud** framework. This project consists of few microservices implemented as independent **Maven modules**. In the system there is only one custom service - Service HelloWorld. The rest of services in the system are provided by Spring Boot Cloud and they are used for system management.

All services are dockerized and manged by docker orchestration tool **Kubernetes**. It means that user does not have to start up manually all services one by one. It's done automatically by orchestration tool. All Kubernetes configuration is included in file **kubernetes.yaml**.

##### Services
This project consists of following services:
* **Service Discovery**: port **8761**. This service displays list of all active services in system
* **Service Config**: port **8888**. This service provides flexible configuration variables. These variables can be taken for instance from Github
* **Service HelloWorld**: port **8080**. This service provides JSON with message and application id
* **Service Gateway**: port **8762**. This service redirects request from outside system to service inside system. It also takes care of load balancing

##### Flow
The following flow takes place in this project:
1. User via browser sends request to Service HellWorld for content. This request is not sent directly but through Service Gateway. 
1. Service Gateway takes location of all services in system from Service Discovery.
1. Service HelloWorld connects with Service Config for text of message. This text is taken from Github project
1. Service HelloWorld sends response to User via browser. This response contains message and application id of this exact instance of Servie HelloWorld. 

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
* Installed **kubectl**

##### Preconditions - Actions
* **Launched** Docker
* **Launched** Minikube
* **Download** source code using Git 
* Open any **Command Line** (for instance "Windonw PowerShell" on Windows OS) tool on **project's folder** (exact localization of project you can check in GIT repositories on page `https://github.com/wisniewskikr/chrisblog-it-cloud`)


USAGE
-----

Usage steps:
1. In Command Line tool start all microservices (it takes about 5 minutes) with `kubectl apply -f kubernetes.yaml`
1. (Optional) In Command Line tool monitor if all Kubernetes Pods are ready (it takes about 5 minutes - expected "READY 1/1" for all Pods) with `kubectl get pods`
1. In Command Line tool start Gateway Service in browser with `minikube service service-gateway`
1. In browser add suffix **/service-helloworld** to call HelloWorld Service via Gateway Service. It should look like this: `http://192.168.1.66:31000/service-helloworld` 
1. (Optional) In Command Line tool start Discovery Service in browser wiht `minikube service service-discovery-display`
1. (Optional) In Command Line tool start HelloWorld Service in browser wiht `minikube service service-helloworld-display`
1. Clean up environment:

    * In Command Line remove all microservices with `kubectl delete -f kubernetes.yaml`


(OPTIONAL) BUILD IMAGES AND PUSH THEM TO REMOTE REPOSITORY
----------------------------------------------------------

**Note!**:
* Please replace my **hub-docker-id** - **wisniewskikr** - with your unique **hub-docker-id**. In this way images will be pushed to your realm in the repository Hub Docker. 

Usage steps:
1. Build package with `mvn clean package -D maven.test.skip`
1. Build Service Config image with **docker build -f service-config/Dockerfile-Fast -t {hub-docker-id}/service-config-image ./service-config** . For instance with `docker build -f service-config/Dockerfile-Fast -t wisniewskikr/springcloud-single-kubernetes-elk-zipkin-service-config-image ./service-config`
1. Push Service Config image with **docker push {hub-docker-id}/springcloud-single-kubernetes-elk-zipkin-service-config-image** . For instance with `docker push wisniewskikr/springcloud-single-kubernetes-elk-zipkin-service-config-image`
1. Build Service Discovery image with **docker build -f service-discovery/Dockerfile-Fast -t {hub-docker-id}/service-discovery-image ./service-discovery** . For instance with `docker build -f service-config/Dockerfile-Fast -t wisniewskikr/springcloud-single-kubernetes-elk-zipkin-service-discovery-image ./service-discovery`
1. Push Service Discovery image with **docker push {hub-docker-id}/springcloud-single-kubernetes-elk-zipkin-service-discovery-image** . For instance with `docker push wisniewskikr/springcloud-single-kubernetes-elk-zipkin-service-discovery-image`
1. Build Service Gateway image with **docker build -f service-gateway/Dockerfile-Fast -t {hub-docker-id}/service-gateway-image ./service-gateway** . For instance with `docker build -f service-gateway/Dockerfile-Fast -t wisniewskikr/springcloud-single-kubernetes-elk-zipkin-service-gateway-image ./service-gateway`
1. Push Service Gateway image with **docker push {hub-docker-id}/springcloud-single-kubernetes-elk-zipkin-service-gateway-image** . For instance with `docker push wisniewskikr/springcloud-single-kubernetes-elk-zipkin-service-gateway-image`
1. Build Service HelloWorld image with **docker build -f service-helloworld/Dockerfile-Fast -t {hub-docker-id}/service-helloworld-image ./service-helloworld** . For instance with `docker build -f service-helloworld/Dockerfile-Fast -t wisniewskikr/springcloud-single-kubernetes-elk-zipkin-service-helloworld-image ./service-helloworld`
1. Push Service HelloWorld image with **docker push {hub-docker-id}/springcloud-single-kubernetes-elk-zipkin-service-helloworld-image** . For instance with `docker push wisniewskikr/springcloud-single-kubernetes-elk-zipkin-service-helloworld-image`
1. Clean up environment:

    * Remove Service Config image with **docker rmi {hub-docker-id}/{image-name}**. For instance with `docker rmi wisniewskikr/springcloud-single-kubernetes-elk-zipkin-service-config-image`
    * Remove Service Config image with name **{image-name}** from your **hub-docker-id** remote repository `https://hub.docker.com`. For instance `springcloud-single-kubernetes-elk-zipkin-service-config-image`
    * Remove Service Discovery image with **docker rmi {hub-docker-id}/{image-name}**. For instance with `docker rmi springcloud-single-kubernetes-elk-zipkin-service-discovery-image`
    * Remove Service Discovery image with name **{image-name}** from your **hub-docker-id** remote repository `https://hub.docker.com`. For instance `springcloud-single-kubernetes-elk-zipkin-service-discovery-image`
    * Remove Service Gateway image with **docker rmi {hub-docker-id}/{image-name}**. For instance with `docker rmi wisniewskikr/springcloud-single-kubernetes-elk-zipkin-service-gateway-image`
    * Remove Service Gateway image with name **{image-name}** from your **hub-docker-id** remote repository `https://hub.docker.com`. For instance `springcloud-single-kubernetes-elk-zipkin-service-gateway-image`
    * Remove Service HelloWorld image with **docker rmi {hub-docker-id}/{image-name}**. For instance with `docker rmi wisniewskikr/springcloud-single-kubernetes-elk-zipkin-service-helloworld-image`
    * Remove Service HelloWorld image with name **{image-name}** from your **hub-docker-id** remote repository `https://hub.docker.com`. For instance `springcloud-single-kubernetes-elk-zipkin-service-helloworld-image`