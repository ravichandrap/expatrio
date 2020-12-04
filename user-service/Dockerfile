FROM adoptopenjdk
#MAINTAINER experto.dev <experto.dev>
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring

VOLUME /var/lib/user-service
EXPOSE 8081

ARG APP_NAME_JAR=/home/spring/user-service.jar
ARG JAR_FILE=gradle/wrapper/*.jar
ADD ${JAR_FILE} ${APP_NAME_JAR}

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/home/spring/user-service.jar"]