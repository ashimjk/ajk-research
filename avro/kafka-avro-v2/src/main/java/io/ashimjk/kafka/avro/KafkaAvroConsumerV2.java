package io.ashimjk.kafka.avro;

import io.ashimjk.avro.model.Customer;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaAvroConsumerV2 {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.0.100:9092");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "avro-consumer-group-v2");
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        properties.setProperty("schema.registry.url", "http://192.168.0.100:8081");
        properties.setProperty("specific.avro.reader", "true");

        final KafkaConsumer<String, Customer> customerKafkaConsumer = new KafkaConsumer<>(properties);

        customerKafkaConsumer.subscribe(Collections.singleton("customer-avro"));

        System.out.println("waiting for data");

        while (true) {
            final ConsumerRecords<String, Customer> consumerRecords = customerKafkaConsumer.poll(Duration.ofMillis(500));

            for (ConsumerRecord<String, Customer> consumerRecord : consumerRecords) {
                final Customer customer = consumerRecord.value();
                System.out.println(customer);
            }

            customerKafkaConsumer.commitSync();
        }

//        customerKafkaConsumer.close();
    }

}
