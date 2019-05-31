package io.ashimjk.services.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ashimjk.services.messaging.Order;
import io.ashimjk.services.order.repository.OrderRepository;
import io.ashimjk.services.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
@EnableBinding(Processor.class)
public class OrderApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderApplication.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private OrderService service;

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @StreamListener(Processor.INPUT)
    public void receiveOrder(Order order) throws JsonProcessingException {
        LOGGER.info("Order received: {}", mapper.writeValueAsString(order));

        service.process(order);
    }

    @Bean
    OrderRepository repository() {
        return new OrderRepository();
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {

        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();

        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setMaxPayloadLength(1000);
        loggingFilter.setAfterMessagePrefix("REQ:");

        return loggingFilter;
    }

}
