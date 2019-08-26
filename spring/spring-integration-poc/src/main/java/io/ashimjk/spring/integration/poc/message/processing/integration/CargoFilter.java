package io.ashimjk.spring.integration.poc.message.processing.integration;

import io.ashimjk.spring.integration.poc.message.processing.model.Cargo;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.MessageEndpoint;

/**
 * Cargo Filter determines whether the message should be passed to the message channel.
 */
@MessageEndpoint
public class CargoFilter {

    private static final long CARGO_WEIGHT_LIMIT = 1_000;

    /**
     * Checks weight of cargo and filters if it exceeds limit.
     *
     * @param cargo message
     * @return check result
     */
    @Filter(inputChannel = "cargoSplitterOutputChannel", outputChannel = "cargoFilterOutputChannel", discardChannel = "cargoFilterDiscardChannel")
    public boolean filterIfCargoWeightExceedsLimit(Cargo cargo) {
        return cargo.getWeight() <= CARGO_WEIGHT_LIMIT;
    }
}
