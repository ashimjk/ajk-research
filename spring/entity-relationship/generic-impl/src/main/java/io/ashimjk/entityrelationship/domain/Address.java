package io.ashimjk.entityrelationship.domain;

import lombok.*;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Address extends BaseEntity {

    private String addressName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String poBox;

}
