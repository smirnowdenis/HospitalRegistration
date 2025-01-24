FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/data-service-1.0-SNAPSHOT.jar data-service.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "data-service.jar"]