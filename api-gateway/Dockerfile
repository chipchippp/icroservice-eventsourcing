# Stage 1: use maven for building
FROM maven:3.8.5-openjdk-17-slim as build
WORKDIR /app

COPY ./api-gateway ./api-gateway

WORKDIR /app/./api-gateway
RUN mvn clean package -DskipTests

# Stage 2: use openjdk for running
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/api-gateway/target/*.jar api-gateway.jar
EXPOSE 8880
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]