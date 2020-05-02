package io.ashimjk.spring.integration.tryout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = IntegrationTryout.class)
class IntegrationTryoutTest {

    @Autowired
    private MessageChannel inputSplit;

    @Autowired
    private PollableChannel outputSplit;

    @Test
    void testSplitterAndAggregatorFlow() {
        this.inputSplit.send(new GenericMessage<>("a,b,c,d"));

        Message<?> message = this.outputSplit.receive(10_000);

        assertNotNull(message);
        assertEquals("[A, B, C, D]", message.getPayload().toString());
    }

}
