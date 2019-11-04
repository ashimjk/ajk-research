package io.ashimjk.entitymerge.entity.correspondentbank;

import io.ashimjk.entitymerge.entity.AddressEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
public class CorrespondentBankEntity {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private String bicCode;

    @Embedded
    private AddressEntity addressEntity;

    @ElementCollection
    private List<String> services = new ArrayList<>();

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "correspondent_bank_id", nullable = false)
    private Set<AccountEntity> accountEntities = new HashSet<>();

}
