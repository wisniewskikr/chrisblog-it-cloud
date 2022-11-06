- http://localhost:8761

- mvn -f ./service-discovery spring-boot:run
- mvn -f ./service-config spring-boot:run
- mvn -f ./service-helloworld-1 spring-boot:run
- mvn -f ./service-helloworld-2 spring-boot:run
- mvn -f ./service-gateway spring-boot:run