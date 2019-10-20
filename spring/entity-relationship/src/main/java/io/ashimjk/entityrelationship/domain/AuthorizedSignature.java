package io.ashimjk.entityrelationship.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class AuthorizedSignature extends BaseEntity {

    private String fullName;
    private String nationalNumber;
    private String title;
    private String signature;
    private String document;

    @OneToMany
    private List<Service> services;

    @OneToOne
    private DelegatedPerson delegatedPerson;

}
