package io.ashimjk.spring.integration.poc.message.processing.integration;

import io.ashimjk.spring.integration.poc.message.processing.model.CargoMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;

/**
 * Cargo Service Activator is a generic endpoint for connecting service instance to the messaging system.
 */
@Slf4j
@MessageEndpoint
public class CargoServiceActivator {

    /**
     * Gets processed domestic and international cargo request(s) and logs.
     *
     * @param cargoMessage domestic/international cargo message
     * @param batchId      message header shows cargo batch id
     */
    @ServiceActivator(inputChannel = "cargoTransformerOutputChannel")
    public void getCargo(CargoMessage cargoMessage, @Header("CARGO_BATCH_ID") long batchId) {
        LOG.debug("Message in Batch[" + batchId + "] is received with payload : " + cargoMessage);
    }

}
