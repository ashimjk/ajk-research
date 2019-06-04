# Spring Cloud Configuration using Cloud Bus

## What is Spring Cloud Bus ?
Spring Cloud Bus links the independent services in the micro-services 
environment through a light weight message broker (e.g:- RabbitMQ or Kafka).  
This message broker can be used to broadcast the configuration changes and events.
In addition, it can be used as a communication channel among independent services.

A key idea is that the Bus is like a distributed Actuator for a Spring Boot 
application that is scaled out, but it can also be used as a communication 
channel between applications.

### Spring Cloud Bus and Spring Cloud Stream

Spring Cloud Bus is built on Spring Cloud Stream. Therefore Spring Cloud 
Bus can be identified as the application use of Spring Cloud Stream.

## How it works?
- `product-service` requests the property from the `spring-cloud-config-server` 

- `spring-cloud-config-server` access the configured property source 
(here it is the the Git repository)

- `spring-cloud-config-server` receives the latest configurations 
from `Git Repository` (property source).

- `product-service` receives the latest properties from config-server.

- `admin/user/configuration_updater` has made a changed to property 
files and updated the Git repository.

- when user triggers the refresh event for any service 
(here it is product-service), the Spring Cloud Bus will receive the refresh event.
Then it will broadcast the refresh event across all the connected clients
through the underlying message broker (e.g:- RabbitMQ).

- refresh event is triggered by invoking the endpoint `/actuator/bus-refresh`
of the department-service.


## The property file naming rule
- The name should start with the application name as declared in the relevant service.

    e.g:-  spring.application.name = product-service

- If there different profiles, the profile name should comes after the application name.

    e.g:- product-service-uat.properties , product-service-qa.properties  (or .yml)

> The spring cloud config server will pick the correct property file based on the 
application name and the activated profile name. If no profile is 
activated/mentioned explicitly, it will pick the property file with no profile suffix.
    e.g:- product-service.properties (or .yml)

## Config Server Endpoints
- REST endpoints to get the application specific configuration properties.
```
GET /{application}/{profile}[/{label}]
GET /{application}-{profile}.yml
GET /{label}/{application}-{profile}.yml
GET /{application}-{profile}.properties
GET /{label}/{application}-{profile}.properties
```

- `{application}` refers to value of spring.config.name property
- `{profile}` is an active profile
- `{label}` is an optional git label (defaults to “master”).

e.g. : http://localhost:8080/product-service/default

- Response:
    - product-service default configuration details
    - application.properties (.yml) configuration if available

## Product Service

### Dependency
- `Config Client`: for retrieving the properties from the Config Server.

- `Actuator`: for invoking the `/actuator/bus-refresh` endpoint for retrieving the 
latest property changes from Config Server.

- `Spring Cloud Bus`: for communication between services using rabbitmq

### RabbitMQ Setup
```
docker pull rabbitmq:3-management
docker run -d --hostname my-rabbit --name rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq:3-management
```

## What we do?
Here we are going to use the `Spring Cloud Bus` to broadcast the refresh event across all services.
Therefore whenever the property is changed, we need to trigger the refresh event
for the `Spring Cloud Bus`. This can be done with invoking  `/actuator/bus-refresh`
endpoint through any of the connected services (any service that is connected 
to the `Spring Cloud Bus`). Then the `Spring Cloud Bus` will broadcast the `refresh 
event` across all the connected services.

In this way, it is possible to trigger the refresh event in one service and get 
it reflected in all other connected services.