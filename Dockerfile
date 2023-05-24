# Use the official OpenJDK 17 image as the base image for both stages
FROM openjdk:17-jdk AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle Wrapper files
COPY gradlew .
COPY gradle gradle

# Change the permission of the Gradle Wrapper script
RUN chmod +x gradlew

# Copy the build.gradle and settings.gradle files
COPY build.gradle settings.gradle ./

# Copy the source code
COPY src ./src

# Run the Gradle build command using the Gradle Wrapper
RUN ./gradlew build

# Create a new image with JRE 17 only
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Set the Entrypoint command to start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
