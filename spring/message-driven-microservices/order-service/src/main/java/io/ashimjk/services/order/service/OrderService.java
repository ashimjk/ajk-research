package io.ashimjk.services.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ashimjk.services.messaging.Order;
import io.ashimjk.services.messaging.OrderStatus;
import io.ashimjk.services.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository repository;
    private ObjectMapper mapper = new ObjectMapper();

    public OrderService(OrderRepository repository) {this.repository = repository;}

    public void process(final Order order) throws JsonProcessingException {
        LOGGER.info("Order processed: {}", mapper.writeValueAsString(order));

        Order o = repository.findById(order.getId());

        if (o.getStatus() != OrderStatus.REJECTED) {

            o.setStatus(order.getStatus());

            repository.update(o);

            LOGGER.info("Order status updated: {}", mapper.writeValueAsString(order));
        }
    }

}
