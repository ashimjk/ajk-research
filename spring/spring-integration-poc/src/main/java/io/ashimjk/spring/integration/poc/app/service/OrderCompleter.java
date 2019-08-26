package io.ashimjk.spring.integration.poc.app.service;

import io.ashimjk.spring.integration.poc.app.domain.Order;
import io.ashimjk.spring.integration.poc.app.domain.OrderItem;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Receives the collection of order items that have been processed
 * for the discount reduction.
 */
@Slf4j
public class OrderCompleter {

    public Order prepareDelivery(List<OrderItem> orderItems) {
        final Order order = new Order();
        order.setOrderItems(orderItems);

        LOG.debug("*** [OrderCompleter] CompletedOrder Discounted cost: " + order.getTotalDiscountedCost() + " ****");

        return order;
    }
}
