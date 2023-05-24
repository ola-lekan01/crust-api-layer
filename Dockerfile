#docker build -t crust-core-api .
#sudo docker run -d -p 9880:8089 -p 9881:8080 --env-file=config.env -e name="crust-core-api" crust-core-api

# Use an appropriate base image with Java and the necessary dependencies
FROM openjdk:latest

# Copy the .jar file into the container
COPY ./build/libs/crust-api-layer-0.0.1-SNAPSHOT.jar /app/crustcoreapi.jar

# Copy the config.env file into the Docker image
COPY ./config.env /app/config.env

# Expose the port for remote debugging
EXPOSE 8089

# Start the Java application with remote debugging enabled
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,address=0.0.0.0:8089,server=y,suspend=n", "-jar", "/app/crustcoreapi.jar"]
