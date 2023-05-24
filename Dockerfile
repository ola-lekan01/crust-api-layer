# Use the official Gradle image as the base image
FROM gradle:jdk17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the build.gradle and settings.gradle files
COPY build.gradle settings.gradle ./

# Copy the source code
COPY src ./src

# Build the application
RUN gradle build --no-daemon

# Create a new image with JRE 17 only
FROM adoptopenjdk/openjdk17:alpine-jre

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Set the Entrypoint command to start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
