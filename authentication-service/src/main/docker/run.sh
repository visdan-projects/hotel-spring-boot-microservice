#!/bin/sh

echo "========> Waiting for Discovery Service on port $DISCOVERY_SERVICE_PORT"
while ! `nc -z discoveryservice  $DISCOVERY_SERVICE_PORT`; do sleep 3; done
echo "========> Discovery Service fired up"

echo "========> Waiting for database on port $DATABASE_SERVER_PORT"
while ! `nc -z postgresql $DATABASE_SERVER_PORT`; do sleep 3; done
echo "========> PostgreSQL database fired up"

echo "========> Waiting for Config Service on port $CONFIG_SERVICE_PORT"
while ! `nc -z configservice $CONFIG_SERVICE_PORT`; do sleep 3; done
echo "========> Config Service fired up"

echo "========> Waiting for Tracing Service on port $TRACING_SERVICE_PORT"
while ! `nc -z tracingservice $TRACING_SERVICE_PORT`; do sleep 10; done
echo "========> Tracing Service fired up"

echo "========> Engaging Authentication Service for profile -> $PROFILE via discovery -> $DISCOVERY_SERVICE_URI:$SERVER_PORT"
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT -Deureka.client.serviceUrl.defaultZone=$DISCOVERY_SERVICE_URI -Dspring.cloud.config.uri=$CONFIG_SERVICE_URI -Dspring.zipkin.baseUrl=$TRACING_SERVICE_URI -Dspring.profiles.active=$PROFILE -jar /usr/local/authenticationservice/@project.build.finalName@.jar