FROM openjdk:8-jdk-alpine

RUN apk update \
 && apk add procps \
 && apk add vim
 
RUN apk update \
 && apk add wget \
 && apk add curl

RUN apk add --no-cache --upgrade bash
 
COPY ./oauth2-sample-realm-config.json /opt/keycloak/config/oauth2-sample-realm-config.json
COPY ./run.sh /opt/keycloak/run.sh
COPY ./run2.sh /opt/keycloak/run2.sh

COPY ./keycloak-7.0.1/ /opt/keycloak/keycloak-7.0.1/
#COPY ./keycloak-7.0.1.zip/ /opt/keycloak/keycloak-7.0.1.zip/

#VOLUME ["/opt/tyk-gateway/"]

WORKDIR /opt/keycloak/

EXPOSE 8080
EXPOSE 9990
EXPOSE 80

#notepad++ ->edit->EOL conversion 

CMD ["sh","./run2.sh"]
#ENTRYPOINT ["sh","./run2.sh"]