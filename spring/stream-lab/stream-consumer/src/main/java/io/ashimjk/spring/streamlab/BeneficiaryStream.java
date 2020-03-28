package io.ashimjk.spring.streamlab;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface BeneficiaryStream {

    String INPUT = "beneficiaryConsumer";

    @Input(BeneficiaryStream.INPUT)
    SubscribableChannel beneficiaryConsumer();

}
