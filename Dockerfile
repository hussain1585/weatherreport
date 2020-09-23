FROM openjdk:8-jre
EXPOSE 8080
MAINTAINER HussainAkhtarWahid
VOLUME /tmp
ARG folder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} folder/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]