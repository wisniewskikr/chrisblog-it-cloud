LOCALHOST URL
-------------

* **URL EUREKA**: http://localhost:8761
* **URL GATEWAY: GET Greeting**: http://localhost:8090/greeting/lang/{lang}/name/{name} . For instance: http://localhost:8090/greeting/lang/pl/name/Chris 
* **URL GATEWAY: GET Text**: http://localhost:8090/text/lang/{lang} . For instance: http://localhost:8090/text/lang/pl
* **URL GET Greeting**: http://localhost:8080/greeting/lang/{lang}/name/{name} . For instance: http://localhost:8080/greeting/lang/pl/name/Chris 
* **URL GET Text**: http://localhost:9090/text/lang/{lang} . For instance: http://localhost:9090/text/lang/pl


ORDER
-----

You have to run projects if following order:
- Eureka: (contains Loadbalancer Ribbon);
- Text;
- Greeting (contains REST Client Operfeign);
- Gateway.


DIFFERENCES TO LOCALHOST
-----------------------

Differences:
- Eureka location has to be added in all services in application.yml;
- JPA and Database dependencies have to be added to all custom services.


DESCRIPTION
-----------

#####Goal
The goal of this project is to show how spring cloud microservices work in localhost environment.

We have here 2 REST API Spring Boot projects:
* **Custom Text Service**: provides greeting text in many languages;
* **Custom Greeting Service**: connects greeting text with name.

#####Used technologies:
* **BE**: Spring Boot API


LAUNCH
------

To launch project please run following class: 
* Application.java

You can also launch project using Maven command:
* mvn spring-boot:run