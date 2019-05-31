package io.ashimjk.services.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ashimjk.services.messaging.Order;
import io.ashimjk.services.order.messaging.OrderSender;
import io.ashimjk.services.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    private final OrderRepository repository;
    private final OrderSender sender;
    private ObjectMapper mapper = new ObjectMapper();

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

    @GetMapping
    public List<Order> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

}
