FROM openjdk:8-jdk-alpine

VOLUME /tmp
EXPOSE 8080

ARG JAR_FILE=build/libs/app.jar
ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]
