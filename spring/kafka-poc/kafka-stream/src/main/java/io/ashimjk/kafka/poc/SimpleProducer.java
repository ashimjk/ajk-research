package io.ashimjk.kafka.poc;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleProducer {

    private final KafkaTemplate<String, String> producer;

    void send(String message) {
        producer.send("simple-message", message);
    }
}
