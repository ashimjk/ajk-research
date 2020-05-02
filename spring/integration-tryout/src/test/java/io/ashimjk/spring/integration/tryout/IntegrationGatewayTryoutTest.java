package io.ashimjk.spring.integration.tryout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = IntegrationGatewayTryout.class)
class IntegrationGatewayTryoutTest {

    @Autowired
    private IntegrationGatewayTryout.ControlBusGateway controlBusGateway;

    @Autowired
    private PollableChannel gatewayOutputChannel;

    @Test
    void testGatewayIntegration() {
        controlBusGateway.send("test");

        Message<?> receive = gatewayOutputChannel.receive(10_000);

        assertNotNull(receive);
        assertEquals("test", receive.getPayload().toString());
    }

}
