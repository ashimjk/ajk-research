package io.ashimjk.kafka.poc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SimpleConsumer {

    @KafkaListener(id = "simple-consumer", topics = "simple-message")
    public void consumeMessage(String message) {
        LOG.info("Got message: " + message);
    }

}
