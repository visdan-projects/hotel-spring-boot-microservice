#!/bin/sh

echo "========> Engaging the Discovery Service"
java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/discoveryservice/@project.build.finalName@.jar