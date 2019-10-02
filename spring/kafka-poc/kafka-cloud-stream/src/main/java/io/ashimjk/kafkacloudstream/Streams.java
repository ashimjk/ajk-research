package io.ashimjk.kafkacloudstream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Streams {

    String INPUT_CHANNEL = "input-channel";
    String OUTPUT_CHANNEL = "output-channel";

    @Input(Streams.INPUT_CHANNEL)
    SubscribableChannel input();

    @Output(Streams.OUTPUT_CHANNEL)
    MessageChannel output();
}
