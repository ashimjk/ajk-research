package io.ashimjk.liquibase.entity;

import io.ashimjk.liquibase.entity.authorizedsignature.AuthorizedSignatureEntity;
import io.ashimjk.liquibase.entity.contractdetail.ContractDetailEntity;
import io.ashimjk.liquibase.entity.correspondentbank.CorrespondentBankEntity;
import io.ashimjk.liquibase.entity.deal.TypeOfDealingEntity;
import io.ashimjk.liquibase.entity.shared.AddressEntity;
import io.ashimjk.liquibase.entity.shared.IdTypeEntity;
import io.ashimjk.liquibase.entity.shared.TransactionDateEntity;
import io.ashimjk.liquibase.entity.shared.WorkflowEntity;
import io.ashimjk.liquibase.entity.userdecision.UserDecisionLogEntity;
import io.ashimjk.liquibase.model.shared.BeneficiaryType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    private String reference;
    private String idType;
    private LocalDateTime createdDate;
    private String sourceSystem;

    @Enumerated(EnumType.STRING)
    private BeneficiaryType beneficiaryType;

    @Embedded
    private ContractDetailEntity contractDetail;

    @Embedded
    private WorkflowEntity workflow;

    @Embedded
    private TransactionDateEntity transactionDate;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "beneficiary_id", nullable = false)
    @OrderColumn(name = "contact_person_order_id")
    private List<ContactPersonEntity> contactPersons = new ArrayList<>();

    @ElementCollection
    @OrderColumn(name = "key_management_order_id")
    private List<KeyManagementEntity> keyManagements = new ArrayList<>();

    @ElementCollection
    @OrderColumn(name = "shared_holder_order_id")
    private List<ShareHolderEntity> shareHolders = new ArrayList<>();

    @ElementCollection
    @OrderColumn(name = "address_order_id")
    private List<AddressEntity> addresses = new ArrayList<>();

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "beneficiary_id", nullable = false)
    @OrderColumn(name = "authorized_signature_order_id")
    private List<AuthorizedSignatureEntity> authorizedSignatories = new ArrayList<>();

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "beneficiary_id", nullable = false)
    @OrderColumn(name = "correspondent_bank_order_id")
    private List<CorrespondentBankEntity> correspondentBanks = new ArrayList<>();

    @ElementCollection
    @OrderColumn(name = "idTypes_order_id")
    private List<IdTypeEntity> idTypes = new ArrayList<>();

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "beneficiary_id", nullable = false)
    @OrderColumn(name = "type_of_dealing_order_id")
    private List<TypeOfDealingEntity> typeOfDealings = new ArrayList<>();

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "beneficiary_id", nullable = false)
    @OrderColumn(name = "user_decision_order_id")
    private List<UserDecisionLogEntity> userDecisions = new ArrayList<>();

}
