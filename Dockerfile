# Use a base image with JDK and Gradle pre-installed
FROM gradle:jdk17-alpine AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle build files to the container
COPY build.gradle .
COPY settings.gradle .
COPY gradle ./gradle

# Copy only the Gradle files necessary for dependency resolution
COPY gradlew .
COPY gradlew.bat .
COPY --chown=gradle:gradle . .

# Build and package the application using Gradle
RUN ./gradlew build --no-daemon

# Create a new image based on OpenJDK with JRE only
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage to the container
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the port on which the Spring Boot application will listen
EXPOSE 8080

# Specify the command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
