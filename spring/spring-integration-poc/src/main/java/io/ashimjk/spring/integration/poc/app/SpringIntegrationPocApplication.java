package io.ashimjk.spring.integration.poc.app;

import io.ashimjk.spring.integration.poc.app.domain.*;
import io.ashimjk.spring.integration.poc.app.service.ShopGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringIntegrationPocApplication implements CommandLineRunner {

    @Autowired
    private ShopGateway shop;

    public static void main(String[] args) {
        SpringApplication.run(SpringIntegrationPocApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Order order = createOrder();

        shop.placeOrder(order);
    }

    private static Order createOrder() {
        Book book = new Book("Of Mice & Men", "Bluebird", new BigDecimal("100"), 1988, "John Steinbeck");
        MusicCD cd = new MusicCD("Off the Wall", "publisher", new BigDecimal("100"), 1975, "Michael Jackson");
        Software macos = new Software("Mavericks", "publisher", new BigDecimal("100"), 2014, "10.9.3");

        OrderItem bookItems = new OrderItem(book);
//		bookItems.incrementQuantity();

        OrderItem cdItems = new OrderItem(cd);
//		cdItems.incrementQuantity();
//		cdItems.incrementQuantity();

        OrderItem swItems = new OrderItem(macos);

        final List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(bookItems);
        orderItems.add(cdItems);
        orderItems.add(swItems);

        Order order = new Order();
        order.setOrderItems(orderItems);
        LOG.debug("Order: " + order);
//		LOG.debug("Total : "+ order.getTotalCost());

        return order;
    }
}
