package io.ashimjk.entitymerge.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class AddressEntity {

    private String addressName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String poBox;

}
