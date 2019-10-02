package io.ashimjk.kafkacloudstream;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.cloud.stream.messaging.Sink.INPUT;
import static org.springframework.cloud.stream.messaging.Source.OUTPUT;

@EnableBinding(Processor.class)
@SpringBootApplication
@Slf4j
public class KafkaCloudStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaCloudStreamApplication.class, args);
    }

    @StreamListener(INPUT)
    @SendTo(OUTPUT)
    public String process(String s) {
        LOG.info("Inside process()");
        String s1 = s.toUpperCase();

        LOG.info(s1);

        return s1;
    }

    @RestController
    @RequiredArgsConstructor
    static class MessageHandler {

        private final Source source;
        private final Sink sink;

        @GetMapping
        public String handle(@RequestParam String value) {
            LOG.info("Inside MessageHandler.handle()");

            source.output().send(MessageBuilder.withPayload(value).build());

            return value;
        }
    }

}
