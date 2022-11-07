- http://localhost:8761

- mvn -f ./service-discovery spring-boot:run
- mvn -f ./service-config spring-boot:run
- mvn -f ./service-helloworld-1 spring-boot:run
- mvn -f ./service-helloworld-2 spring-boot:run
- mvn -f ./service-gateway spring-boot:run

Ports:
- service-discovery: 8761
- service-config: 8888
- service-helloworld-1: 8080
- service-helloworld-2: 8081
- service-gateway: 8762