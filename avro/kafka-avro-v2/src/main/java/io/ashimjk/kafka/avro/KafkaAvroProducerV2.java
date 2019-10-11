package io.ashimjk.kafka.avro;

import io.ashimjk.avro.model.Customer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaAvroProducerV2 {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.0.100:9092");
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "1");
        properties.setProperty(ProducerConfig.RETRIES_CONFIG, "10");

        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        properties.setProperty("schema.registry.url", "http://192.168.0.100:8081");

        final Customer customer = Customer.newBuilder()
                .setFirstName("krishna")
                .setLastName("karki")
                .setAge(40)
                .setHeight(5)
                .setWeight(65)
                .setPhoneNumber("9841542010")
                .setEmail("krishna.karki@gmail.com")
                .build();

        final KafkaProducer<String, Customer> customerKafkaProducer = new KafkaProducer<>(properties);

        final ProducerRecord<String, Customer> stringCustomerProducerRecord = new ProducerRecord<>("customer-avro", customer);

        customerKafkaProducer.send(stringCustomerProducerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    System.out.println(recordMetadata);
                } else {
                    System.out.println("Error while sending data");
                }
            }
        });

        customerKafkaProducer.flush();
        customerKafkaProducer.close();

    }
}
