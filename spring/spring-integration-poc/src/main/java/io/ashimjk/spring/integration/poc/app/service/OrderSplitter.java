package io.ashimjk.spring.integration.poc.app.service;

import io.ashimjk.spring.integration.poc.app.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderSplitter extends AbstractMessageSplitter {

    @Override
    protected Object splitMessage(Message<?> message) {

        LOG.debug("*** [OrderSplitter] splitting Order into it's constituent OrderItems : number of OrderItems: " + ((Order) message.getPayload()).getOrderItems().size() + " ****");

        return ((Order) message.getPayload()).getOrderItems();
    }

}
