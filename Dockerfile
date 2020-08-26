FROM openjdk:8-jdk-slim

WORKDIR /service
COPY ./target/*.jar ./mybel.jar

ENTRYPOINT ["java", "-jar", "./mybel.jar"]