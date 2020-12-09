FROM openjdk:8-jdk-alpine
ENV JAVA_OPTS=""
VOLUME /tmp
ADD url-shortener-1.0.0-SNAPSHOT.jar /app.jar
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]
EXPOSE 8989