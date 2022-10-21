SPRING CLOUD ROUTING RIBBON LOCALHOST
=====================================


LOCALHOST URL
-------------

* **URL GET Greeting**: http://localhost:8080/greeting/lang/{lang}/name/{name} . For instance: http://localhost:8080/greeting/lang/pl/name/Chris 
* **URL GET Text**: http://localhost:9090/text/lang/{lang} . For instance: http://localhost:9090/text/lang/pl


DESCRIPTION
-----------

#####Goal
The goal of this project is to present Spring Cloud Ribbon. Ribbon is Load Balancer - redirects routing to different client servers.

In this project Ribbon is included in "custom-greeting=service". It enables connection with many services "custom-text-service".

We have here 2 REST API Spring Boot projects:
* **Custom Text Service**: provides greeting text in many languages;
* **Custom Greeting Service**: connects greeting text with name.

#####Used technologies:
* **BE**: Spring Boot API


IMPLEMENTATION
--------------

Prerequisites:
* This project is based on **spring-cloud-localhost**.

Implementation steps in "custom-greeting-service":
* Update file pom.xml: add dependency for Ribbon;
* Create class RibbonConfig: configuration for Ribbon;
* Update class RestConfig: add annotation @LoadBalanced;
* Update class Application: add annotation @RibbonClient. **ATTENTION!** name can not be the same as definied in application.yml
* Update file application.yml: add Ribbon configuration - for instance location of servers.
  

LAUNCH
------

For **every project**:

To launch project please run following class: 
* Application.java

You can also launch project using Maven command:
* mvn spring-boot:run