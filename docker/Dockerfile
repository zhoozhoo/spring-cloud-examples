FROM openjdk:11-jre as builder
WORKDIR /app
ADD maven/*.jar ./app.jar
RUN java -Djarmode=layertools -jar app.jar extract

ARG DOCKERIZE_VERSION
RUN wget https://github.com/jwilder/dockerize/releases/download/${DOCKERIZE_VERSION}/dockerize-linux-amd64-${DOCKERIZE_VERSION}.tar.gz \
    && tar xzf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && chmod +x dockerize

FROM adoptopenjdk:15-jre-hotspot
LABEL maintainer="zhoozhoo@yahoo.com"
WORKDIR /app
ARG EXPOSED_PORT
EXPOSE ${EXPOSED_PORT}
ENV SPRING_PROFILES_ACTIVE docker
COPY --from=builder /app/dockerize ./
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
