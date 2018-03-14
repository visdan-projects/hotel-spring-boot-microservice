# Spring Boot Microservice Example

This application, developed using Spring Boot and Spring Cloud, presents a microservice architecture example; in this instance, that of a hotel booking system. Using the application, users can authenticate against the authorization server, look up account information, search for, and book, available rooms etc. At the moment, the application does not contain a front end interface, but interactions with the microservices can be performed using a REST based client, such as Postman.

## Microservices Listing

1. [Account Service](account-service/README.md)
    - OAuth2 Client
    - Customer/Account information
    - PostgreSQL - RDBMS Store
2. [API Gateway Service](api-gateway-service/README.md)
    - OAuth2 SSO
    - Netflix Zuul
    - Securely exposes HTTP routes
3. [Authentication Service](authentication-service/README.md)
    - Resource Server
    - Authorization Server
    - OAuth2
    - PostgreSQL - RDBMS Store
4. [Configuration Service](config-service/README.md)
    - Spring Cloud Config
    - Centralized config management
5. [Discovery Service](discovery-service/README.md)
    - Registry of service information
    - Netflix Eureka
6. [Inventory Service](inventory-service/README.md)
    - OAuth2 Client
    - Inventory/Booking reservation service
    - MongoDB - Document Store
7. [Tracing Service](tracing-service/README.md)
    - Zipkin Server

## Microservices Architecture

![Architecture Diagram](https://i.imgur.com/F1exObk.jpg)

## Required Software

1. [Apache Maven](http://maven.apache.org/). Version 4.0.0 is used by the application. It is necessary to have Maven installed in order to successfully build and package the application. 
2. [Docker](https://www.docker.com/). Version used is Edge 18.02.0-ce. The application was tested while running inside the VirtualBox on a Windows 7 64 bit machine. Each service will be built into its own Docker container image. The application will run inside the Docker runtime virtualization engine. Additionally, Docker Compose is used to start the services as a group, after being compiled into Docker images.
3. [Git](https://git-scm.com/). All source code is stored within a GitHub repository. The cloud configuration for relevant services is also stored in a separate GitHub repository.
4. [Postman](https://www.getpostman.com/). Since there is no front end user interface for the application, all microservice testing was performed using a REST based client; in this case Postman. Within each microservice README there are examples of how to invoke and interface with the service.

## Building and Deployment

- Download the Zip file of the application from the GitHub repository, and extract the contents.
- Open a Docker terminal and navigate to the root directory of the project.
- Enter the below command which will execute the Spotify docker plugin:

```
$ mvn clean package docker:build
```

After the images are built, a build success message will appear:

![Build Success](https://i.imgur.com/JFAiuzG.jpg)

- After successful build, enter the below command to start the Docker image:

```
$ docker-compose -f docker/common/docker-compose.yml up
```

Docker will pull in necessary dependencies and start each of the services.

## Cleanup/Troubleshooting

#### Stopping Docker Containers

In order to determine which containers are currently running in Docker, the `docker ps -a` command can be used. 

To stop and remove any currently running containers, the below commands can be used (`-a` switch show the container and `-q` supplies the ID to the `stop` and `rm` commands):

```
$ docker stop $(docker ps -a -q)
$ docker rm $(docker ps -a -q)
```

#### Removing images/volumes

Whenever a container is removed, the corresponding image or volume is not automatically removed, since they are independent. In the case the list of images and volumes need to be removed manually, whether they are no longer used, or to free up disk space, the following [tutorial](https://www.digitalocean.com/community/tutorials/how-to-remove-docker-images-containers-and-volumes) provides some useful commands.

#### Rebuild docker machine

In the event the docker machine needs to be stopped or removed (in case some containers cannot be properly stopped) or to create a new docker machine with more CPUs or extra memory, the below commands can be used. 

- Find out the docker machine in use

```
$ docker-machine ls
```

- Stop and remove the existing machine

```
$ docker-machine stop default
$ docker-machine rm default
```

- Create a new docker machine instance (in this case named default with 2 CPUs, 4 gig of memory and a disk size of 50gb.

```
$ docker-machine create -d virtualbox --virtualbox-cpu-count=2 --virtualbox-memory=4096 --virtualbox-disk-size=50000 default
```

- Start the docker machine

```
docker-machine start default
```
