package io.ashimjk.entityrelationship.domain;

import io.ashimjk.entityrelationship.domain.userdecision.UserDecisionLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Beneficiary implements Serializable {

    @Id
    private String id;

    @OneToMany
    private List<Address> addresses;

    @Enumerated(EnumType.STRING)
    private BeneficiaryType beneficiaryType;

    @OneToMany
    private List<AuthorizedSignature> authorizedSignatories;

    @Embedded
    private ContractDetail contractDetail;

    @OneToOne
    private CorporateBeneficiary corporateBeneficiary;

    @OneToMany
    private List<CorrespondentBank> correspondentBanks;

    private String currentProcess;

    private String currentTask;

    private LocalDate endingDate;

    private String fullName;

    @OneToMany
    private List<IdType> idTypes;

    private String nationalNumber;

    private String processInstanceId;

    private String reference;

    private LocalDate reviewDate;

    private LocalDate startingDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany
    private List<TypeOfDealing> typeOfDealings;

    @OneToMany
    private List<UserDecisionLog> userDecisions;

    enum Status {
        APPROVED, VERIFIED, CANCELLED, SUSPENDED
    }
}
