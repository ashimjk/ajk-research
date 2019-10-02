package io.ashimjk.kafkacloudstream.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class MessageHandler {

    private final Streams streams;

    @GetMapping
    public String handle(@RequestParam String value) {
        LOG.info("Inside MessageHandler.handle()");

        streams.output().send(MessageBuilder.withPayload(value).build());

        return value;
    }
}
