FROM openjdk:8-jre
EXPOSE 8080
MAINTAINER HussainAkhtarWahid
ADD target/*.jar app.jar
ENTRYPOINT ["java", "-jar","app.jar"]