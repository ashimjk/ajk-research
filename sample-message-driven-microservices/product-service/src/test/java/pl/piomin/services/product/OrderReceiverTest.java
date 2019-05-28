package pl.piomin.services.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringRunner;
import pl.piomin.services.messaging.Order;
import pl.piomin.services.messaging.OrderStatus;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderReceiverTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderReceiverTest.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Processor processor;

    @Autowired
    private MessageCollector messageCollector;

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public void testProcessing() {
        Order o = new Order();
        o.setId(1L);
        o.setAccountId(1L);
        o.setCustomerId(1L);
        o.setPrice(500);
        o.setProductIds(Collections.singletonList(2L));

        processor.input().send(MessageBuilder.withPayload(o).build());

        Message<String> received = (Message<String>) messageCollector.forChannel(processor.output()).poll();

        LOGGER.info("Order response received: {}", received.getPayload());

        Order receivedOrder = objectMapper.readValue(received.getPayload(), Order.class);

        assertNotNull(received.getPayload());
        assertEquals(OrderStatus.ACCEPTED, receivedOrder.getStatus());
    }

}
