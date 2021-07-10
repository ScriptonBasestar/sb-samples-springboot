FROM frolvlad/alpine-oraclejdk8:slim
ADD build/tmp/app.jar app.jar
RUN sh -c 'touch /app.jar'

ENV TIME_ZONE              Asia/Seoul
#ENV SPRING_PROFILES_ACTIVE=dev
#ENV JAVA_OPTS=""
#ENV JAVA_OPTS="$JAVA_OPTS -Dspring.profiles.active=dev"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
