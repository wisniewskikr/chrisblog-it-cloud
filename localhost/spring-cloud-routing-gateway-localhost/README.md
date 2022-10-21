LOCALHOST URL
-------------

* **URL GET Greeting**: http://localhost:8090/greeting/lang/{lang}/name/{name} . For instance: http://localhost:8090/greeting/lang/pl/name/Chris 
* **URL GET Text**: http://localhost:8090/text/lang/{lang} . For instance: http://localhost:8090/text/lang/pl


DESCRIPTION
-----------

#####Goal
The goal of this project is to show how api gateway works for Spring Boot application in localhost environment.

We have here 2 REST API Spring Boot projects:
* **Custom Text Service**: provides greeting text in many languages;
* **Custom Greeting Service**: connects greeting text with name.

#####Used technologies:
* **BE**: Spring Boot API


IMPLEMENTATION
--------------

Prerequisites:
* Create copy of project "spring-cloud-localhost".

Implementation details:
* Create module "system-api-gateway-service";
* In module "system-api-gateway-service" in file "pom.xml" add dependency "spring-cloud-starter-gateway";
* In module "system-api-gateway-service" in file "application.yml" add "cloud -> gateway -> routes".

  

LAUNCH
------

To launch project please run following class: 
* Application.java

You can also launch project using Maven command:
* mvn spring-boot:run