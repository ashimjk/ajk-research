package io.ashimjk.json_merge;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Address {
    private Long id;
    private String street;
    private String city;
    private String zipCode;

    static Address of(Long id, String street, String city, String zipCode) {
        Address address = new Address();
        address.setId(id);
        address.setStreet(street);
        address.setCity(city);
        address.setZipCode(zipCode);
        return address;
    }

    @Override
    public String toString() {
        return "Address{"
                + "id='"
                + id
                + '\''
                + ", street='"
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
