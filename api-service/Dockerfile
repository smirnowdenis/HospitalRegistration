FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/api-service-1.0-SNAPSHOT.jar api-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "api-service.jar"]