FROM maven:3.9.9-eclipse-temurin-23 AS builder
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip

FROM eclipse-temurin:23-jre
COPY --from=builder /home/app/target/*.jar  /opt/app/*.jar
RUN apt-get update && apt-get install -y curl
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar"]