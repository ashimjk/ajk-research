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
docker-compose up -d

docker run -it --rm --net=host confluentinc/cp-schema-registry:5.3.1 bash
kafka-avro-console-consumer
```

## Access UI
http://localhost:3030/

## Avro Consumer and Producer
```shell script
kafka-avro-console-producer \
    --broker-list 192.168.0.100:9092 --topic test-avro \
    --property schema.registry.url=http://192.168.0.100:8081 \
    --property value.schema=\
'{"type":"record","name":"myrecord","fields":[{"name":"fullName","type":"string"}]}'

kafka-avro-console-consumer \
    --bootstrap-server 192.168.0.100:9092 --topic test-avro \
    --property schema.registry.url=http://192.168.0.100:8081 \
    --from-beginning
```

Input
```shell script
{"fullName":"ashim"}
{"fullName":"ashish"}
{"fullName":"pabitra"}
# gives an error
{"name":"ashim"}
{"fullName":1}
```

Update schema (incompatible schema)
```shell script
kafka-avro-console-producer \
    --broker-list 192.168.0.100:9092 --topic test-avro \
    --property schema.registry.url=http://192.168.0.100:8081 \
    --property value.schema='{"type":"int"}'
```



## Reference
- [Landoop-Kafka Cluster](https://hub.docker.com/r/landoop/fast-data-dev)
- [Confluent Schema Registry](https://hub.docker.com/r/confluentinc/cp-schema-registry)