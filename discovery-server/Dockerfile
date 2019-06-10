FROM java:8-jdk-alpine
COPY ./target/discovery-server-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "discovery-server-0.0.1-SNAPSHOT.jar"]