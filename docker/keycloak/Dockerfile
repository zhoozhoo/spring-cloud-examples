FROM jboss/keycloak
COPY spring-cloud-gateway-realm.json /tmp
ENV KEYCLOAK_USER=user
ENV KEYCLOAK_PASSWORD=password
ENV KEYCLOAK_IMPORT=/tmp/spring-cloud-gateway-realm.json
