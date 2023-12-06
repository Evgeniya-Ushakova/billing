FROM openjdk:17-jdk-slim-buster

ARG APP_HOME=/app
WORKDIR $APP_HOME
COPY build/libs/billing.jar $APP_HOME/billing.jar

ENTRYPOINT exec java $JAVA_OPTS -jar ./billing.jar