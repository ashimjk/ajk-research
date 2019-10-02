package io.ashimjk.kafkacloudstream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StreamHandler {

    @StreamListener(Streams.INPUT_CHANNEL)
    @SendTo(Streams.OUTPUT_CHANNEL)
    public Person process(Person person) {
        LOG.info("Inside StreamHandler.process() : [{}]", person);

        return person;
    }

}
