package io.ashimjk.entityrelationship.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CorrespondentBank extends BaseEntity {

    private String name;
    private String bicCode;

    @Embedded
    private EmbeddableAddress address;

    @ElementCollection
    private List<String> services;

    @OneToMany
    private Set<Account> accounts;

}
