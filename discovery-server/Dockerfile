# Stage 1: use maven for building
FROM maven:3.8.5-openjdk-17-slim as build
WORKDIR /app
COPY discovery-server ./discovery-server
WORKDIR /app/discovery-server
RUN mvn clean package -DskipTests

# Stage 2: use openjdk for running
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/discovery-server/target/*.jar discovery-server.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "discovery-server.jar"]