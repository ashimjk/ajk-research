## Spring Boot-JPA App using Vagrant and Docker

## Database Configuration - PostgreSQL
Update application properties for database properties `src/main/resources/application.properties`.

- dbName : postgres
- username : postgres
- password : postgres

## Project Setup Goal
- [x] Install Vagrant using Ubuntu 16 Distro.
- [x] Share project file with vagrant.
- [x] Install docker and docker compose.
- [x] Run postgres image and test application using it
- [x] Get maven image from '3-jdk-8'
- [x] Create docker file and add all necessary setup.
- [x] Run maven install command.
- [x] Add cmd for spring boot run.
- [x] Create docker compose file
- [x] Build and run using docker compose file.

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

## Prerequiste
- Install Virtual Box
- Install Vagrant

## How to run
- Development Environment
```bash
vagrant up // setup virtual os and install docker
vagrant ssh
docker pull postgres
docker run -d --name postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -v pgdata:/var/lib/postgresql/data postgres
```

- Staging Environment
```bash
vagrant up
vagrant ssh
docker pull postgres
docker pull maven:3-jdk-8
docker image build -t spring-jpa-demo -f app/docker/app.dockerfile .
docker container run -d --name postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -v pgdata:/var/lib/postgresql/data postgres
docker container run -d --name spring-jpa-demo -p 8080:8080 spring-jpa-demo
```

- Production Environment
```bash
vagrant up
vagrant ssh
docker pull postgres
docker pull maven:3-jdk-8
docker image build -t spring-jpa-demo -f app/docker/app.dockerfile .
docker-compose -f app/docker/docker-compose.yml up -d
docker-compose -f app/docker/docker-compose.yml down
```
