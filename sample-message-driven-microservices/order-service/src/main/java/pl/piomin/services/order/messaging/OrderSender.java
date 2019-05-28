package pl.piomin.services.order.messaging;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;
import pl.piomin.services.messaging.Order;

@Service
public class OrderSender {

    private final Source source;

    public OrderSender(Source source) {this.source = source;}

    public boolean send(Order order) {
        return this.source.output().send(MessageBuilder.withPayload(order).build());
    }

}
