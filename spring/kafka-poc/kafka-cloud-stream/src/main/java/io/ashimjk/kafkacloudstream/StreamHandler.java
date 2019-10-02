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
    public String process(String s) {
        LOG.info("Inside StreamHandler.process()");
        String s1 = s.toUpperCase();

        LOG.info(s1);

        return s1;
    }

}
