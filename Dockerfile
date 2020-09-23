FROM openjdk:8-jre
EXPOSE 8080
MAINTAINER HussainAkhtarWahid
ADD target/*.jar weather-report.jar
ENTRYPOINT ["java", "-jar","weather-report.jar"]