package io.ashimjk.entityrelationship.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@EqualsAndHashCode
@Entity
public class DelegatedPerson extends BaseEntity {

    private String fullName;
    private String nationalNumber;
    private String title;
    private String signature;
    private String document;

    @OneToMany
    private List<Service> services;

}
