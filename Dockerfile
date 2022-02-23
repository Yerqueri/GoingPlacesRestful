FROM openjdk:19-jdk-alpine
VOLUME /tmp
ADD target/go-places-rest-0.1.0.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar