package io.ashimjk.entityrelationship.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EmbeddableAddress implements Serializable {

    private String addressName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String poBox;

    EmbeddableAddress(String addressName, String addressLine1, String addressLine2, String city, String country, String poBox) {
        this.addressName = addressName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.country = country;
        this.poBox = poBox;
    }

}
