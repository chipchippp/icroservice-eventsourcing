# Stage 1: use maven for building
FROM maven:3.8.5-openjdk-17-slim as build
WORKDIR /app

COPY ./book-service ./book-service
COPY ./common-service ./common-service

WORKDIR /app/common-service
RUN mvn clean install -DskipTests

WORKDIR /app/book-service
RUN mvn clean package -DskipTests

# Stage 2: use openjdk for running
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/book-service/target/*.jar book-service.jar
EXPOSE 8881
ENTRYPOINT ["java", "-jar", "book-service.jar"]