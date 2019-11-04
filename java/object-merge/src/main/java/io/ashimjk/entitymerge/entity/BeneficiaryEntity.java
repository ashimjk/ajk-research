package io.ashimjk.entitymerge.entity;

import io.ashimjk.entitymerge.entity.authorizedsignature.AuthorizedSignatureEntity;
import io.ashimjk.entitymerge.entity.correspondentbank.CorrespondentBankEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
public class BeneficiaryEntity {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String reference;
    private String fullName;
    private String nationalNumber;
    private LocalDate startingDate;
    private LocalDate reviewDate;
    private LocalDate endingDate;
    private LocalDateTime createdDate;

    @ElementCollection
    private List<AddressEntity> addressEntities = new ArrayList<>();

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "beneficiary_id", nullable = false)
    private List<AuthorizedSignatureEntity> authorizedSignatories = new ArrayList<>();

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "beneficiary_id", nullable = false)
    private List<CorrespondentBankEntity> correspondentBankEntities = new ArrayList<>();


}
