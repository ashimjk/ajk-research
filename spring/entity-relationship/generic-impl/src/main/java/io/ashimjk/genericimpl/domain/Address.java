package io.ashimjk.genericimpl.domain;

import io.ashimjk.genericimpl.exception.DomainAsserts;
import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

import static io.ashimjk.genericimpl.exception.DomainViolationConstants.*;

@Getter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Address implements Serializable {

    private static final long serialVersionUID = 6716882928409769243L;

    private String addressName;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String country;

    private String poBox;

    public static Address create(String addressName, String addressLine1, String addressLine2,
                                 String city, String country, String poBox) {

        DomainAsserts.isNotBlank(addressName, ADDRESS_NAME_SHOULD_NOT_BE_NULL);
        DomainAsserts.isNotBlank(city, CITY_SHOULD_NOT_BE_NULL);
        DomainAsserts.isNotBlank(country, COUNTRY_SHOULD_NOT_BE_NULL);
        DomainAsserts.isNotBlank(poBox, POBOX_SHOULD_NOT_BE_BLANK);

        Address address = new Address();

        address.addressName = addressName;
        address.addressLine1 = addressLine1;
        address.addressLine2 = addressLine2;
        address.city = city;
        address.country = country;
        address.poBox = poBox;

        return address;
    }
}
