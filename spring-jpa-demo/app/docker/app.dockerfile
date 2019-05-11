FROM maven:3-jdk-8

WORKDIR /opt/app

COPY .. .

RUN 'mvn clean install'

CMD 'mvn spring-boot:run'
