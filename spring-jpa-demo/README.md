## Spring Boot-JPA App using Vagrant and Docker

## Database Configuration - PostgreSQL
Update application properties for database properties `src/main/resources/application.properties`.
 
- dbName : postgres_demo
- username : postgres
- password : postgres

## Project Setup Goal
- [ ] Install Vagrant using Ubuntu 16 Distro.
- [ ] Share project file with vagrant.
- [ ] Install docker and docker compose.
- [ ] Run postgres image and test application using it
- [ ] Get maven image from '3-jdk-8'
- [ ] Create docker file and add all necessary setup.
- [ ] Run maven install command.
- [ ] Add cmd for spring boot run.
- [ ] Create docker compose file
- [ ] Build and run using docker compose file.

## Project Development
- [ ] Add unit testing
- [ ] Add integration testing
- [ ] Refactor code if necessary
- [ ] Code coverage should be more than 90%
- [ ] Convert all possible scenario to lombok

## Future Development
- [ ] Get user details from user-service
- [ ] Add response and request mapper
- [ ] Using wiremock for testing
- [ ] Use spring feign cloud service

## How to run
- run following cmd
    - vagrant up
    - vagrant ssh
    - create docker image
    - run docker container

## Note
- Use same network
- Use volume and bind mounts
- Use three steps verification
    - development
    - staging
    - production
