package pl.piomin.services.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.piomin.services.messaging.Order;
import pl.piomin.services.messaging.OrderStatus;
import pl.piomin.services.order.repository.OrderRepository;

@Service
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private ObjectMapper mapper = new ObjectMapper();

    private final OrderRepository repository;

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
