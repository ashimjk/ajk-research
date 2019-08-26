package io.ashimjk.spring.integration.poc.message.processing.model;

import lombok.RequiredArgsConstructor;

/**
 * Parent class of Domestic or International Cargo Messages.
 */
@RequiredArgsConstructor
public class CargoMessage {

    private final Cargo cargo;

    public Cargo getCargo() {
        return cargo;
    }

    @Override
    public String toString() {
        return cargo.toString();
    }
}
