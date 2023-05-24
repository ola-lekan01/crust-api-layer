# Use a base image with JDK 17
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle configuration files
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle gradle

# Copy the source code
COPY src src

# Change permissions for gradlew
RUN chmod +x gradlew

# Set the environment variable for Gradle heap size
ENV GRADLE_OPTS="-Xmx2g"

# Build the application using Gradle and run tests
RUN ./gradlew build --stacktrace --info

# Expose the port on which the Spring Boot application will listen
EXPOSE 8080

# Specify the command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
