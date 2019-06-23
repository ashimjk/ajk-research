package io.ashimjk.json_merge;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Address {
    private String street;
    private String city;
    private String zipCode;

    static Address of(String street, String city, String zipCode) {
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setZipCode(zipCode);
        return address;
    }

    @Override
    public String toString() {
        return "Address{"
            + "street='"
            + street
            + '\''
            + ", city='"
            + city
            + '\''
            + ", zipCode="
            + zipCode
            + '}';
    }
}
