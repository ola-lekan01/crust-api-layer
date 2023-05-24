# Use the official AdoptOpenJDK base image for JDK 17
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the build.gradle and settings.gradle files
COPY build.gradle settings.gradle ./

# Copy the source code
COPY src ./src

# Build the application
RUN ./gradlew build

# Create a new image with a smaller base image
FROM adoptopenjdk:17-jre-hotspot

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Set the Entrypoint command to start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
