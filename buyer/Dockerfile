FROM java:openjdk-8-jdk
EXPOSE 8103

# Specify JAR location
ARG JAR_FILE=target/buyer-0.0.1-SNAPSHOT.jar
# Copy the JAR
COPY ${JAR_FILE} buyer.jar
ENTRYPOINT ["java","-jar","/buyer.jar"]
