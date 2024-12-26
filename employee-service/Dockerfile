# Stage 1: use maven for building
FROM maven:3.8.5-openjdk-17-slim as build
WORKDIR /app

COPY ./employee-service ./employee-service
COPY ./common-service ./common-service

WORKDIR /app/common-service
RUN mvn clean install -DskipTests

WORKDIR /app/employee-service
RUN mvn clean package -DskipTests

# Stage 2: use openjdk for running
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/employee-service/target/*.jar employee-service.jar
EXPOSE 8882
ENTRYPOINT ["java", "-jar", "employee-service.jar"]