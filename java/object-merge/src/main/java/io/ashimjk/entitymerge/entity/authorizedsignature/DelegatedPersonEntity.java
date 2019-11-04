package io.ashimjk.entitymerge.entity.authorizedsignature;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
class DelegatedPersonEntity {

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

}
