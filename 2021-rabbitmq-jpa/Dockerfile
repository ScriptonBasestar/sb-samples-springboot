FROM openjdk:11-jdk-buster

VOLUME /tmp
EXPOSE 8080

#ARG JAR_FILE=app/build/libs/app-0.0.1-SNAPSHOT-all.jar
ARG JAR_FILE=app/build/libs/app.jar
ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
