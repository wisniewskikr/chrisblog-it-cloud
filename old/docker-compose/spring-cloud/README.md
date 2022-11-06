LOCALHOST URL
-------------

* **URL GET Greeting**: http://localhost:8080/greeting/lang/{lang}/name/{name} . For instance: http://localhost:8080/greeting/lang/pl/name/Chris 
* **URL GET Text**: http://localhost:9090/text/lang/{lang} . For instance: http://localhost:9090/text/lang/pl

**Starting command**:
* docker-compose up -d

**Additional starting command**:
* docker-compose up -d --build								: rebuild all projects
* docker-compose -f docker-compose-fast.yml up -d			: start fast (jar files already exist for all projects)
* docker-compose -f docker-compose-fast.yml up -d  --build	: start fast with rebuld all projects (jar files already exist for all projects)

**Refresh container commands**:
* **for custom-greeting-service**: mvn -f custom-greeting-service clean package -PrefreshContainer
* **for custom-text-service**: mvn -f custom-text-service clean package -PrefreshContainer


DESCRIPTION
-----------

#####Goal
The goal of this project is to show how docker compose works for many projects. 
And how in fast way refresh single container.

We have here 2 REST API Spring Boot projects:
* **Custom Text Service**: provides greeting text in many languages;
* **Custom Greeting Service**: connects greeting text with name.

#####Used technologies:
* **BE**: Spring Boot API


IMPLEMENTATION
--------------

Prerequisites:
* Docker

Implementation details:
* Add file docker-compose.yml
* Run it by command: docker-compose up

Most useful commands for docker compose:
* docker-compose up -d --build														: rebuild and start services
* docker-compose down																: stop and remove services
* docker-compose up -d --build --force-recreate --no-deps custom-text-service		: rebuild text-service
* docker-compose up -d --build --force-recreate --no-deps custom-greeting-service	: rebuild greeting-service

Other commands for docker compose:
* docker-compose version	: version of docker compose;
* docker-compose config 	: check if docker-compose.yml file is ok;
* docker-compose up		: starts containers;
* docker-compose up -d	: starts containers but in deteach mode. It means that you can work on console;
* docker-compose up -d --build: rebuilds and starts all containers;
* docker-compose up -d --build --force-recreate --no-deps mvc-thymeleaf-docker-compose	: rebuilds and starts one specific container;
* docker-compose down	: stops and removes containers;
* docker-compose stop		: stops containers;
* docker-compose ps		: list of containers connected with this compose;
* docker-compose up -d --scale mvc-thymeleaf-docker-compose=4: starts 4 containers with name “mvc-thymeleaf-docker-compose” (example ports:- 8080-8083:8080)
* docker-compose build : rebuild containers.

  

LAUNCH
------

To launch project please run following class: 
* Application.java

You can also launch project using Maven command:
* mvn spring-boot:run


USAGE
-----

Link to main UI:
* http://[server]/app/greeting