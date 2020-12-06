FROM openjdk:11-buster
#MAINTAINER experto.dev <experto.dev>
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring

#VOLUME /var/lib/user-service
VOLUME /tmp
WORKDIR /app
COPY ./build/libs/user-service-0.0.1-SNAPSHOT.jar .
EXPOSE 8081

#ENTRYPOINT ["java","-jar","/app/libs/user-service-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/user-service-0.0.1-SNAPSHOT.jar"]
