package io.ashimjk.spring.integration.poc.app.domain;

import java.io.Serializable;

public class ShippingAddress extends Address implements Serializable {

    private static final long serialVersionUID = 7858082156191079323L;

    public ShippingAddress() {
        type = AddressType.SHIPPING_ADDRESS;
    }

    public ShippingAddress(final String firstLineOfAddress, final String city, final String postCode) {
        super(firstLineOfAddress, city, postCode);
        type = AddressType.SHIPPING_ADDRESS;
    }

}
