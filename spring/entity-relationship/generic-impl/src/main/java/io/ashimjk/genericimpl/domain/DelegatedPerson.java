package io.ashimjk.genericimpl.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
class DelegatedPerson extends BaseEntity {

    private String fullName;
    private String nationalNumber;
    private String title;
    private String signature;
    private String document;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Service> services;

}
