#!/bin/sh

echo "========> Engaging the Tracing Service profile -> $PROFILE"
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT -Dspring.profiles.active=$PROFILE -Dspring.zipkin.baseUrl=$TRACING_SERVICE_URI -jar /usr/local/tracingservice/@project.build.finalName@.jar