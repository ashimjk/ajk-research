FROM maven:3-jdk-8

WORKDIR /opt/app

EXPOSE 8080

COPY app /opt/app

COPY .m2/repository /root/.m2/repository

RUN mvn clean install

CMD ["mvn", "spring-boot:run"]
