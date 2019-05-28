package pl.piomin.services.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.piomin.services.messaging.Order;
import pl.piomin.services.order.messaging.OrderSender;
import pl.piomin.services.order.repository.OrderRepository;

import java.util.Collections;

@RestController
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private ObjectMapper mapper = new ObjectMapper();

    private final OrderRepository repository;
    private final OrderSender sender;

    public OrderController(OrderRepository repository, OrderSender sender) {
        this.repository = repository;
        this.sender = sender;
    }

    @PostMapping
    public Order process(@RequestBody Order order) throws JsonProcessingException {
        Order dbOrder = repository.add(order);

        LOGGER.info("Order saved: {}", mapper.writeValueAsString(order));

        boolean isSent = sender.send(dbOrder);

        LOGGER.info("Order sent: {}", mapper.writeValueAsString(Collections.singletonMap("isSent", isSent)));

        return dbOrder;
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

}
