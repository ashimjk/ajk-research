package io.ashimjk.spring.integration.poc.message.processing.integration;

import io.ashimjk.spring.integration.poc.message.processing.model.Cargo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;

/**
 * Discarded Cargo Message Listener listens the discarded Cargo messages by CargoFilter.
 */
@Slf4j
@MessageEndpoint
public class DiscardedCargoMessageListener {

    /**
     * Handles discarded domestic and international cargo request(s) and logs.
     *
     * @param cargo   domestic/international cargo message
     * @param batchId message header shows cargo batch id
     */
    @ServiceActivator(inputChannel = "cargoFilterDiscardChannel")
    public void handleDiscardedCargo(Cargo cargo, @Header("CARGO_BATCH_ID") long batchId) {
        LOG.debug("Message in Batch[" + batchId + "] is received with Discarded payload : " + cargo);
    }

}
