FROM java:openjdk-8-jdk
EXPOSE 8102

# Specify JAR location
ARG JAR_FILE=target/seller-0.0.1-SNAPSHOT.jar
# Copy the JAR
COPY ${JAR_FILE} seller.jar
ENTRYPOINT ["java","-jar","/seller.jar"]