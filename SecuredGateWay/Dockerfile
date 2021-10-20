FROM java:openjdk-8-jdk
EXPOSE 2020

# Specify JAR location
ARG JAR_FILE=target/SecuredGateWay-0.0.1-SNAPSHOT.jar
# Copy the JAR
COPY ${JAR_FILE} apigateway.jar
ENTRYPOINT ["java","-jar","/apigateway.jar"]
