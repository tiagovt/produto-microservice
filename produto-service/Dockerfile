FROM java:8-jre
MAINTAINER Tiago Vaz Teixeira <tiago.vt@gmail.com>
VOLUME /tmp
ENV environment=docker
#ADD target/*.jar app.jar
ADD build/libs/*.jar app.jar
ENV JAVA_OPTS="-XX:+UseConcMarkSweepGC -XX:+CMSIncrementalMode"
ENTRYPOINT java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$environment -jar /app.jar
EXPOSE 8080