package io.ashimjk.entityrelationship.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
public class Address extends BaseEntity {

    private String addressName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String poBox;

}
