package io.ashimjk.liquibase.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name = "beneficiary")
public class BeneficiaryEntity implements Serializable {

    private static final long serialVersionUID = -3077133425024752731L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String fullName;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "beneficiary_id", nullable = false)
    @OrderColumn(name = "correspondent_bank_order_id")
    private List<CorrespondentBankEntity> correspondentBanks = new ArrayList<>();

}
