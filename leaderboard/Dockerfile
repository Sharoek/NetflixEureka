# Use a base image with Java
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built application JAR into the container
COPY target/leaderboard-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application uses
EXPOSE 8080

# Command to run your application
ENTRYPOINT ["java", "-jar", "app.jar"]
