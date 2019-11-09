package io.ashimjk.genericimpl.domain;

import io.ashimjk.genericimpl.domain.authorizedsignature.AuthorizedSignature;
import io.ashimjk.genericimpl.domain.contractdetail.ContractDetail;
import io.ashimjk.genericimpl.domain.corporatebeneficiary.CorporateBeneficiary;
import io.ashimjk.genericimpl.domain.correspondentbank.CorrespondentBank;
import io.ashimjk.genericimpl.domain.deal.TypeOfDealing;
import io.ashimjk.genericimpl.domain.userdecision.UserDecisionLog;
import io.ashimjk.genericimpl.exception.DomainAsserts;
import io.ashimjk.genericimpl.repository.BeneficiaryQuery;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static io.ashimjk.genericimpl.exception.DomainAsserts.nonNullList;
import static io.ashimjk.genericimpl.exception.DomainViolationConstants.*;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "reference")
public class Beneficiary implements Serializable {

    private static final long serialVersionUID = -6277460762817498392L;

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String reference;

    @ElementCollection
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "beneficiary_id")
    private List<AuthorizedSignature> authorizedSignatories = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private BeneficiaryType beneficiaryType;

    @Embedded
    private ContractDetail contractDetail;

    @OneToOne(cascade = ALL, orphanRemoval = true)
    private CorporateBeneficiary corporateBeneficiary;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "beneficiary_id")
    private List<CorrespondentBank> correspondentBanks = new ArrayList<>();

    private String fullName;

    @ElementCollection
    private List<IdType> idTypes = new ArrayList<>();

    private String nationalNumber;

    private LocalDate startingDate;

    private LocalDate endingDate;

    private LocalDate reviewDate;

    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    private Status status = Status.CREATED;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "beneficiary_id")
    private List<TypeOfDealing> typeOfDealings = new ArrayList<>();

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "beneficiary_id")
    private List<UserDecisionLog> userDecisions = new ArrayList<>();

    @Column(name = "is_deleted")
    private Boolean deleted = Boolean.FALSE;

    private String processInstanceId;
    private String currentProcess;
    private String currentTask;

    public static Beneficiary create(
            String fullName,
            BeneficiaryType beneficiaryType,
            String nationalNumber,
            List<IdType> idTypes,
            List<CorrespondentBank> correspondentBanks,
            List<TypeOfDealing> typeOfDealings,
            LocalDate startDate,
            LocalDate reviewDate,
            LocalDate endingDate,
            ContractDetail contractDetail,
            List<Address> addresses,
            List<AuthorizedSignature> authorizedSignatories,
            CorporateBeneficiary corporateBeneficiary) {

        DomainAsserts.isNotBlank(fullName, FULL_NAME_SHOULD_NOT_BE_EMPTY);
        DomainAsserts.isNull(beneficiaryType, TYPE_SHOULD_NOT_BE_NULL);
        DomainAsserts.isNotBlank(nationalNumber, NATIONAL_NUMBER_SHOULD_NOT_BE_EMPTY);

        Beneficiary beneficiary = new Beneficiary();

        if (nonNullList(correspondentBanks)) {
            beneficiary.correspondentBanks.addAll(correspondentBanks);
        }

        if (nonNullList(typeOfDealings)) {
            beneficiary.typeOfDealings.addAll(typeOfDealings);
        }

        beneficiary.reference = UUID.randomUUID().toString();
        beneficiary.fullName = fullName;
        beneficiary.beneficiaryType = beneficiaryType;
        beneficiary.nationalNumber = nationalNumber;
        beneficiary.idTypes.addAll(idTypes);
        beneficiary.startingDate = startDate;
        beneficiary.reviewDate = reviewDate;
        beneficiary.endingDate = endingDate;
        beneficiary.contractDetail = contractDetail;
        beneficiary.addresses.addAll(addresses);
        beneficiary.authorizedSignatories.addAll(authorizedSignatories);
        beneficiary.status = Status.CREATED;

        if (beneficiaryType == BeneficiaryType.CORPORATE) {
            beneficiary.corporateBeneficiary = corporateBeneficiary;
        }

        return beneficiary;
    }

    public static Beneficiary create(BeneficiaryQuery beneficiaryQuery) {
        Beneficiary beneficiary = new Beneficiary();

        beneficiary.reference = beneficiaryQuery.getReference();
        beneficiary.fullName = beneficiaryQuery.getFullName();
        beneficiary.beneficiaryType = beneficiaryQuery.getBeneficiaryType();
        beneficiary.nationalNumber = beneficiaryQuery.getNationalNumber();
        beneficiary.startingDate = beneficiaryQuery.getStartingDate();
        beneficiary.endingDate = beneficiaryQuery.getEndingDate();
        beneficiary.reviewDate = beneficiaryQuery.getReviewDate();
        beneficiary.status = beneficiaryQuery.getStatus();

        return beneficiary;
    }

    public void addProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public void updateCurrentProcess(String currentProcess) {
        this.currentProcess = currentProcess;
    }

    public void updateCurrentTask(String currentTask) {
        this.currentTask = currentTask;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    public void updateDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public void updateReference(String reference) {
        this.reference = reference;
    }

    public void initCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }

    public void clearProcessDetail() {
        this.processInstanceId = null;
        this.currentProcess = null;
        this.currentTask = null;
    }

    public void addIdType(IdType idType) {
        this.idTypes.add(idType);
    }

    public void addUserDecision(UserDecisionLog userDecisionLog) {
        this.userDecisions.add(userDecisionLog);
    }

    public void updateId(Long id) {
        this.id = id;
    }

    public enum Status {
        CREATED, APPROVED, VERIFIED, CANCELLED, REJECTED, SUSPENDED, SUBMITTED
    }

}
