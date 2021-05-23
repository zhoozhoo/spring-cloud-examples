FROM openjdk:11-jre as builder
WORKDIR /application
COPY maven/*.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

# Download dockerize and cache that layer
ARG DOCKERIZE_VERSION
ADD https://github.com/jwilder/dockerize/releases/download/${DOCKERIZE_VERSION}/dockerize-alpine-linux-amd64-${DOCKERIZE_VERSION}.tar.gz ./dockerize.tar.gz
RUN tar xzf dockerize.tar.gz
RUN chmod +x dockerize

FROM adoptopenjdk:16-jre-hotspot

LABEL maintainer="zhoozhoo@yahoo.com"

WORKDIR /application

 # Dockerize
COPY --from=builder application/dockerize ./

ARG EXPOSED_PORT
EXPOSE ${EXPOSED_PORT}

ENV SPRING_PROFILES_ACTIVE docker

COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]