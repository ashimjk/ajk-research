package io.ashimjk.spring.integration.poc.message.processing.integration;

import io.ashimjk.spring.integration.poc.message.processing.model.Cargo;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

import java.util.List;

/**
 * Cargo Gateway Interface exposes domain-specific method to the application. In
 * other words, it provides an application access to the messaging system.
 */
@MessagingGateway(name = "cargoGateway", defaultRequestChannel = "cargoGWDefaultRequestChannel")
public interface ICargoGateway {

    /**
     * Processes Cargo Request
     *
     * @param message SI Message covering Cargo List payload and Batch Cargo Id header.
     * @return operation result
     */
    @Gateway
    void processCargoRequest(Message<List<Cargo>> message);
}
