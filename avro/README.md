# Avro

## Install Avro Tools
```shell script
wget http://central.maven.org/maven2/org/apache/avro/avro-tools/1.8.2/avro-tools-1.8.2.jar
```

## Run Avro Tools
```shell script
java -jar avro-tools-1.8.2.jar tojson --pretty customer-generic.avro
java -jar avro-tools-1.8.2.jar tojson --pretty customer-specific.avro

java -jar avro-tools-1.8.2.jar getschema customer-specific.avro
```

## Access Kafka Cluster
```shell script
docker run -it --rm --net=host confluentinc/cp-schema-registry:5.3.1 bash
kafka-avro-console-consumer
```

## Access UI
http://localhost:3030/

## Reference
- [Landoop-Kafka Cluster](https://hub.docker.com/r/landoop/fast-data-dev)
- [Confluent Schema Registry](https://hub.docker.com/r/confluentinc/cp-schema-registry)