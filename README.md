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

1. Maven
2. Docker
3. Git
4. Postman

## Building and Deployment

## Troubleshooting
