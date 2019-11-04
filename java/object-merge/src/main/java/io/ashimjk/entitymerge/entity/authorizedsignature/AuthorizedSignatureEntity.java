package io.ashimjk.entitymerge.entity.authorizedsignature;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
public class AuthorizedSignatureEntity {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String fullName;
    private String nationalNumber;
    private String title;
    private String signature;
    private String document;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    private List<ServiceEntity> serviceEntities = new ArrayList<>();

    @OneToOne(cascade = ALL, orphanRemoval = true)
    private DelegatedPersonEntity delegatedPersonEntity;

}
