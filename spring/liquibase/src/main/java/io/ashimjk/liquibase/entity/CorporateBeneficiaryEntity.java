package io.ashimjk.liquibase.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name = "corporate_beneficiary")
public class CorporateBeneficiaryEntity implements Serializable {

    private static final long serialVersionUID = -6435432955457208913L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "corporate_beneficiary_id", nullable = false)
    @OrderColumn(name = "contact_person_order_id")
    private List<ContactPersonEntity> contactPersons = new ArrayList<>();

}
