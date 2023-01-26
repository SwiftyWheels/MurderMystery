FROM ubuntu:latest

# Install OpenJDK 11
RUN apt-get update && apt-get install openjdk-18-jdk -y && apt-get install openjdk-18-jre -y

# Create a new directory for the application
RUN mkdir /app

# Copy the application jar file to the container
COPY MurderMystery-0.0.1-SNAPSHOT.jar /app/

# Make the jar file executable
RUN chmod +x /app/MurderMystery-0.0.1-SNAPSHOT.jar

# Set the working directory
WORKDIR /app

# Start the application
ENTRYPOINT ["java", "-jar", "MurderMystery-0.0.1-SNAPSHOT.jar"]

# Expose the port on which the application runs
EXPOSE 8080