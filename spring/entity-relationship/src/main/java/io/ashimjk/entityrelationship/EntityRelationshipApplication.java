package io.ashimjk.entityrelationship;

import io.ashimjk.entityrelationship.sample.model.OrderItem;
import io.ashimjk.entityrelationship.sample.model.ProductOrder;
import io.ashimjk.entityrelationship.sample.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
@RequiredArgsConstructor
public class EntityRelationshipApplication implements CommandLineRunner {

    private final OrderRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(EntityRelationshipApplication.class, args);
    }

    @Override
    public void run(String... args) {
        ProductOrder productOrder = new ProductOrder();
        productOrder.setOrderNbr("123");
//        productOrder.addOrderItem("desc", 12L);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemQty(12L);
        orderItem.setOrderItemDesc("desc");

        productOrder.setOrderItems(Collections.singletonList(orderItem));

        repository.save(productOrder);

        repository.findAll().forEach(System.out::println);

        update();

        delete();
    }

    private void delete() {
        repository.delete(repository.findAll().get(0));

        repository.findAll().forEach(System.out::println);
    }

    void update() {
        ProductOrder productOrder = repository.findAll().get(0);
        productOrder.setOrderNbr("456");

        OrderItem orderItem = productOrder.getOrderItems().get(0);
        orderItem.setOrderItemQty(34L);
        orderItem.setOrderItemDesc("desc12");

        repository.save(productOrder);
        repository.findAll().forEach(System.out::println);
    }
}
