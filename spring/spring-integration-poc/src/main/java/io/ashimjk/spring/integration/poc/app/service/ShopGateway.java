package io.ashimjk.spring.integration.poc.app.service;

import io.ashimjk.spring.integration.poc.app.domain.Order;
import org.springframework.integration.annotation.Gateway;
import org.springframework.stereotype.Component;

@Component
public interface ShopGateway {

    @Gateway(requestChannel = "ordersChannel")
    void placeOrder(Order order);

}
