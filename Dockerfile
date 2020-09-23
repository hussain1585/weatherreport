FROM openjdk:8-jre
EXPOSE 8090
VOLUME /tmp
MAINTAINER Hussain Akhtar Wahid
ADD target/*.jar weather-report.jar
ENTRYPOINT ["java", "-jar","weather-report.jar"]