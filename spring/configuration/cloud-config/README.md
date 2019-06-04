# Spring Cloud Configuration

## How it works?
- `department-service` requests the property from the `spring-cloud-config-server` 
- `spring-cloud-config-server` access the configured property source (here it is the the Git repository)
- `spring-cloud-config-server` receives the latest configurations from `Git Repository` (property source).
- `department-service` receives the latest properties from config-server.
- `admin/user/configuration_updater` has made a changed to property files and updated the Git repository.
- invoke the `/actuator/refresh` endpoint on the relevant service to get the change reflected in the targeted service.

## The property file naming rule
- The name should start with the application name as declared in the relevant service.

    e.g:-  spring.application.name = department-service

- If there different profiles, the profile name should comes after the application name.

    e.g:- department-service-uat.properties , department-service-qa.properties  (or .yml)

> The spring cloud config server will pick the correct property file based on the application name and the activated profile name. If no profile is activated/mentioned explicitly, it will pick the property file with no profile suffix.
    e.g:- department-service.properties (or .yml)

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

e.g. : http://localhost:8080/department-service/default

- Response:
    - department-service default configuration details
    - application.properties (.yml) configuration if available

