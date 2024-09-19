FROM eclipse-temurin:21-jre-alpine
LABEL Author="zhoozhoo@yahoo.com"
ENV SPRING_PROFILES_ACTIVE docker
COPY maven/*.jar application.jar
ENTRYPOINT ["java","-jar","/application.jar"]