package io.ashimjk.services.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ashimjk.services.messaging.Order;
import io.ashimjk.services.product.model.Product;
import io.ashimjk.services.product.repository.ProductRepository;
import io.ashimjk.services.product.service.ProductService;
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
public class ProductApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductApplication.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ProductService service;

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @StreamListener(Processor.INPUT)
    public void receiveOrder(Order order) throws JsonProcessingException {
        LOGGER.info("Order received: {}", mapper.writeValueAsString(order));
        service.process(order);
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

    @Bean
    ProductRepository repository() {
        ProductRepository repository = new ProductRepository();
        repository.add(new Product("Test1", 1000, 20));
        repository.add(new Product("Test2", 1500, 10));
        repository.add(new Product("Test3", 2000, 20));
        repository.add(new Product("Test4", 3000, 20));
        repository.add(new Product("Test5", 1300, 10));
        repository.add(new Product("Test6", 2700, 10));
        repository.add(new Product("Test7", 3500, 10));
        repository.add(new Product("Test8", 1250, 10));
        repository.add(new Product("Test9", 2450, 10));
        repository.add(new Product("Test10", 800, 10));
        return repository;
    }

}
