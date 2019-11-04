package io.ashimjk.entitymerge.entity.authorizedsignature;

import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
class ServiceEntity {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;

    @Embedded
    private TierEntity tierEntity;

}
