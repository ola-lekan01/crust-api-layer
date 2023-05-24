# Use an appropriate base image with Java and the necessary dependencies
FROM openjdk:latest

# Copy the .jar file into the container
COPY ./build/libs/crust-api-layer-0.0.1-SNAPSHOT.jar /app/crustcoreapi.jar

# Expose the port for remote debugging
EXPOSE 8089

# Start the Java application with remote debugging enabled
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,address=0.0.0.0:8089,server=y,suspend=n", "-jar", "/app/crustcoreapi.jar"]
