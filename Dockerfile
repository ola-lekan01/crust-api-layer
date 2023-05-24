# Use the official OpenJDK 17 image as the base image
FROM openjdk:17-jdk AS build

# Set the working directory in the container
WORKDIR /app

# Copy the build.gradle and settings.gradle files
COPY build.gradle settings.gradle ./

# Copy the source code
COPY src ./src

# Run the Gradle build command
RUN ./gradle build

# Create a new image with JRE 17 only
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Set the Entrypoint command to start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
