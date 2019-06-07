FROM java:8-jdk-alpine
COPY ./target/cloudingair-reservations-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "cloudingair-reservations-0.0.1-SNAPSHOT.jar"]