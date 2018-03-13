#!/bin/sh

echo "========> Waiting for Discovery Service on port $DISCOVERY_SERVICE_PORT"
while ! `nc -z discoveryservice $DISCOVERY_SERVICE_PORT`; do sleep 3; done
echo "========> Discovery Service fired up"

echo "========> Engaging Config Service via discovery -> $DISCOVERY_SERVICE_URI"
java -Djava.security.egd=file:/dev/./urandom -Deureka.client.serviceUrl.defaultZone=$DISCOVERY_SERVICE_URI -jar /usr/local/configservice/@project.build.finalName@.jar