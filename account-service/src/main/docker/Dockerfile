FROM openjdk:8-jdk-alpine
RUN	apk update && apk upgrade && apk add netcat-openbsd && apk add dos2unix --update-cache --repository http://dl-3.alpinelinux.org/alpine/edge/community/ --allow-untrusted
RUN	mkdir -p /usr/local/accountservice
ADD @project.build.finalName@.jar /usr/local/accountservice/
ADD run.sh run.sh
RUN dos2unix run.sh
RUN chmod +x run.sh
CMD [ "/bin/sh", "./run.sh" ]