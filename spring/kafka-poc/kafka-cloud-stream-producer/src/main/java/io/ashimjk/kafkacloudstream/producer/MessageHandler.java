package io.ashimjk.kafkacloudstream.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class MessageHandler {

    private final Streams streams;

    @PostMapping
    public void handle(@RequestBody Person person) {
        LOG.info("Inside MessageHandler.handle()");
        streams.output().send(MessageBuilder.withPayload(person).build());
    }
}
