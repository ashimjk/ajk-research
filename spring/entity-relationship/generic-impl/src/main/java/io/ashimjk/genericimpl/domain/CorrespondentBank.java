package io.ashimjk.genericimpl.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
class CorrespondentBank extends BaseEntity {

    private String name;
    private String bicCode;

    @Embedded
    private EmbeddableAddress address;

    @ElementCollection
    private List<String> services;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Account> accounts;

}
