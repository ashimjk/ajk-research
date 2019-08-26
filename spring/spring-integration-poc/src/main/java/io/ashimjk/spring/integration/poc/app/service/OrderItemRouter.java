package io.ashimjk.spring.integration.poc.app.service;

import io.ashimjk.spring.integration.poc.app.domain.Book;
import io.ashimjk.spring.integration.poc.app.domain.MusicCD;
import io.ashimjk.spring.integration.poc.app.domain.OrderItem;
import io.ashimjk.spring.integration.poc.app.domain.Software;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.Router;
import org.springframework.stereotype.Component;

/**
 * Performs routing based on item type.
 * Will redirect
 * to bookItemsChannel if the item is of type book
 * to musicItemsChannel if the item is of type musicCD
 * to softwareItemsChannel if the item is of type software
 */
@Slf4j
@Component
public class OrderItemRouter {

    @Router(inputChannel = "orderItemsChannel")
    public String routeOrder(OrderItem orderItem) {

        LOG.debug("*** [OrderItemRouter] ****");

        String channel = "";
        if (isBook(orderItem)) {
            channel = "bookItemsChannel";
        } else if (isMusic(orderItem)) {
            channel = "musicItemsChannel";
        } else if (isSoftware(orderItem)) {
            channel = "softwareItemsChannel";
        }

        LOG.debug("*** [OrderItemRouter] sending item : " + orderItem.getItem().getTitle() + " to " + channel + " ****");

        return channel;
    }

    private Boolean isBook(OrderItem orderItem) {
        return orderItem.getItem() instanceof Book;
    }

    private Boolean isMusic(OrderItem orderItem) {
        return orderItem.getItem() instanceof MusicCD;
    }

    private Boolean isSoftware(OrderItem orderItem) {
        return orderItem.getItem() instanceof Software;
    }
}
