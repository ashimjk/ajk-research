# Kafka Stream POC

## Produce message using the Kafka console producer

```shell script
docker exec -it kafka /bin/bash
kafka-console-producer.sh --broker-list localhost:9092 --topic simple-message
```

The console will now block and you can write your message and hit enter, for
each time you do this one message will be produced to the simple-topic.
Try sending a few messages and watch the application standard output in the
shell where you are running your Spring Boot application processing the messages
and printing them.

## Send some messages with curl
```shell script
curl -X POST http://localhost:8080/api/message -d "some more fun" -H "Content-Type: text/plain"
```

## Note
There's a good chance you will get and error when running the application on your
development machine now, this happens because your application is running inside
your normal host network and Kafka and zookeeper are running inside the "docker network".

There are some ways to solve this, the best is to pass in your development machine
hostname to docker-compose when starting the containers if you open the docker-compose
file from this project it has an entry at the `KAFKA_ADVERTISED_LISTENERS: ... LISTENER_DOCKER_EXTERNAL`
like `${DOCKER_HOST_IP:-kafka}:9092` this tells compose to try to use the passed in hostname or Kafka
by default, check the comments in the compose file to find out how to fix it and check the references
section below for more details.


## Reference
- [Docker Compose Environment Variable](https://docs.docker.com/compose/environment-variables/)
