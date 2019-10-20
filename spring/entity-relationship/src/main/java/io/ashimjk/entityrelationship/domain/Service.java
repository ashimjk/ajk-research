package io.ashimjk.entityrelationship.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Service extends BaseEntity {

    private String name;

    @Embedded
    private Tier tier;

}
