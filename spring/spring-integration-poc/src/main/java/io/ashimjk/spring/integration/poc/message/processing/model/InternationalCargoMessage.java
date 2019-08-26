package io.ashimjk.spring.integration.poc.message.processing.model;

/**
 * Models International Cargo Message(s)
 */
public class InternationalCargoMessage extends CargoMessage {

    public enum DeliveryOption {
        NEXT_FLIGHT, PRIORITY, ECONOMY, STANDARD
    }

    private final DeliveryOption deliveryOption;

    public InternationalCargoMessage(Cargo cargo, DeliveryOption deliveryOption) {
        super(cargo);
        this.deliveryOption = deliveryOption;
    }

    @Override
    public String toString() {
        return "InternationalCargoMessage [cargo=" + super.toString() + ", deliveryOption=" + deliveryOption + "]";
    }

}
