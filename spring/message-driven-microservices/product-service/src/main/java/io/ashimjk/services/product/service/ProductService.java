package io.ashimjk.services.product.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ashimjk.services.messaging.Order;
import io.ashimjk.services.messaging.OrderStatus;
import io.ashimjk.services.product.messaging.OrderSender;
import io.ashimjk.services.product.model.Product;
import io.ashimjk.services.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderSender orderSender;
    private ObjectMapper mapper = new ObjectMapper();

    public void process(final Order order) throws JsonProcessingException {
        LOGGER.info("Order processed: {}", mapper.writeValueAsString(order));
        for (Long productId : order.getProductIds()) {
            Product product = productRepository.findById(productId);
            if (product.getCount() == 0) {
                order.setStatus(OrderStatus.REJECTED);
                break;
            }
            product.setCount(product.getCount() - 1);
            productRepository.update(product);
            LOGGER.info("Product updated: {}", mapper.writeValueAsString(product));
        }
        if (order.getStatus() != OrderStatus.REJECTED) {
            order.setStatus(OrderStatus.ACCEPTED);
        }
        LOGGER.info("Order response sent: {}", mapper.writeValueAsString(Collections.singletonMap("status", order.getStatus())));
        orderSender.send(order);
    }

}
