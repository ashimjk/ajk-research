package io.ashimjk.spring.streamlab;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface BeneficiaryCreateSource {

    @Output("beneficiaryProducer")
    MessageChannel createBeneficiary();

}
