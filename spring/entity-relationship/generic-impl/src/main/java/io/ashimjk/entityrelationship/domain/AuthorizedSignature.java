package io.ashimjk.entityrelationship.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class AuthorizedSignature extends BaseEntity {

    private String fullName;
    private String nationalNumber;
    private String title;
    private String signature;
    private String document;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Service> services;

    @OneToOne(cascade = CascadeType.ALL)
    private DelegatedPerson delegatedPerson;

}
