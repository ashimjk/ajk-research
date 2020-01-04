package io.ashimjk.liquibase.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name = "beneficiary")
public class BeneficiaryEntity implements Serializable {

    private static final long serialVersionUID = -3077133425024752731L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = ALL, orphanRemoval = true)
    private CorporateBeneficiaryEntity corporateBeneficiary;

}
