FROM openjdk:8-jdk-alpine

VOLUME /tmp
EXPOSE 8080

ARG JAR_FILE=app/build/libs/app-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]
