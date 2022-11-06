SPRING CLOUD DISCOVERY OPENFEIGN
================================


LOCALHOST URL
-------------

* **URL GET Greeting**: http://localhost:8080/greeting/lang/{lang}/name/{name} . For instance: http://localhost:8080/greeting/lang/pl/name/Chris 
* **URL GET Text**: http://localhost:9090/text/lang/{lang} . For instance: http://localhost:9090/text/lang/pl


DESCRIPTION
-----------

#####Goal
The goal of this project is to present Spring Cloud Openfeign. Openfeign is REST client tool - it enables connection with REST API.
It's something like RestTemplate.

In this project Openfeign is incluted in "custom-greeting=service". It enables connection with "custom-text-service".

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
* Update file pom.xml: add dependency for Openfeign;
* Remove class RestConfig: RestTemplate won't be used any more;
* Update class Application: add annotation @EnableFeignClients with path to root package (or package with clients);
* Create interface CustomTextServiceClient: this client should have exactly the same method as CustomTextService;
* Update class GreetingController: remove RestTEmplate and use CustomTextServiceClient.
  

LAUNCH
------

For **every project**:

To launch project please run following class: 
* Application.java

You can also launch project using Maven command:
* mvn spring-boot:run