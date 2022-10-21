LOCALHOST URL
-------------

* **URL EUREKA**: http://localhost:8761
* **URL GET Greeting**: http://localhost:8080/greeting/lang/{lang}/name/{name} . For instance: http://localhost:8080/greeting/lang/pl/name/Chris 
* **URL GET Text**: http://localhost:9090/text/lang/{lang} . For instance: http://localhost:9090/text/lang/pl


DESCRIPTION
-----------

#####Goal
The goal of this project is to show how Eureka Naming Server works with network of services. Eureka enables following all available services in network.

We have here 2 REST API Spring Boot projects:
* **Custom Text Service**: provides greeting text in many languages;
* **Custom Greeting Service**: connects greeting text with name.

#####Used technologies:
* **BE**: Spring Boot API


IMPLEMENTATION
--------------

Prerequisites:
* N/A

Details:
* create system-eureka-naming-server-service: add Eureka server dependency in pom.xml and annotation @EnableEurekaServer;
* update custom services: add there Eureka client dependencies.
  

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