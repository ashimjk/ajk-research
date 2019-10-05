# Kafka Plain Client

```shell script
kafka-topics --zookeeper 192.168.0.100:2181 --list
kafka-topics --zookeeper 192.168.0.100:2181 --create --topic first-topic --partitions 3 --replication-factor 1
kafka-console-producer --broker-list 192.168.0.100:2181 --topic first-topic

kafka-console-consumer --bootstrap-server localhost:9092 --topic first-topic
kafka-console-consumer --bootstrap-server localhost:9092 --topic first-topic --group consumer-group

docker run -it --network=host edenhill/kafkacat:1.5.0 -b kafka1:9092 -L
docker run -it --network=host edenhill/kafkacat:1.5.0 -b 192.168.0.100:9092 -L
```

## Reference
- [Kafka UI](http://localhost:9000)
- [KafkaCat](https://github.com/edenhill/kafkacat)
- [Kafka Listeners Explained](https://www.confluent.io/blog/kafka-listeners-explained)
- [Kafka Stack Docker Compose](https://github.com/simplesteph/kafka-stack-docker-compose)