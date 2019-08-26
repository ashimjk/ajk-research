package io.ashimjk.spring.integration.poc.message.processing.integration;

import io.ashimjk.spring.integration.poc.message.processing.model.Cargo;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Splitter;
import org.springframework.messaging.Message;

import java.util.List;

/**
 * Cargo Splitter breaks incoming Cargo List into Cargo message(s) and send them
 * to the output channel.
 */
@MessageEndpoint
public class CargoSplitter {

    /**
     * Splits Cargo List to Cargo message(s)
     *
     * @param message SI Message covering Cargo List payload and Batch Cargo Id header.
     * @return cargo list
     */
    @Splitter(inputChannel = "cargoGWDefaultRequestChannel", outputChannel = "cargoSplitterOutputChannel")
    public List<Cargo> splitCargoList(Message<List<Cargo>> message) {
        return message.getPayload();
    }
}
