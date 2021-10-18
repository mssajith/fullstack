FROM java:openjdk-8-jdk
EXPOSE 8761

# Specify JAR location
ARG JAR_FILE=target/EurekaServer-0.0.1-SNAPSHOT.jar
# Copy the JAR
COPY ${JAR_FILE} eurekaserver.jar
ENTRYPOINT ["java","-jar","/eurekaserver.jar"]