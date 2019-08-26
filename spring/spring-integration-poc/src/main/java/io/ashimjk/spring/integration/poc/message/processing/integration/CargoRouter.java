package io.ashimjk.spring.integration.poc.message.processing.integration;

import io.ashimjk.spring.integration.poc.message.processing.model.Cargo;
import io.ashimjk.spring.integration.poc.message.processing.model.Cargo.ShippingType;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

/**
 * Router determines what channel(s) should receive the Message next if it is available.
 */
@MessageEndpoint
public class CargoRouter {

    /**
     * Determines cargo request' s channel in the light of shipping type.
     *
     * @param cargo message
     * @return channel name
     */
    @Router(inputChannel = "cargoFilterOutputChannel")
    public String route(Cargo cargo) {
        if (cargo.getShippingType() == ShippingType.DOMESTIC) {
            return "cargoRouterDomesticOutputChannel";
        } else if (cargo.getShippingType() == ShippingType.INTERNATIONAL) {
            return "cargoRouterInternationalOutputChannel";
        }

        return "nullChannel";
    }

}
