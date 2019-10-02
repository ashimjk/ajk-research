package io.ashimjk.kafkacloudstream.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Streams {

    String OUTPUT_CHANNEL = "input-channel";

    @Output(Streams.OUTPUT_CHANNEL)
    MessageChannel output();
}
