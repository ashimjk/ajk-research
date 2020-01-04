package io.ashimjk.liquibase.model;

import io.ashimjk.liquibase.model.authorizedsignature.AuthorizedSignature;
import io.ashimjk.liquibase.model.contractdetail.ContractDetail;
import io.ashimjk.liquibase.model.correspondentbank.CorrespondentBank;
import io.ashimjk.liquibase.model.deal.TypeOfDealing;
import io.ashimjk.liquibase.model.shared.*;
import io.ashimjk.liquibase.model.userdecision.UserDecisionLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Beneficiary implements Serializable {

    private static final long serialVersionUID = 8899862948712874865L;

    private Long id;
    private String fullName;
    private String reference;
    private String idType;
    private LocalDateTime createdDate;
    private String sourceSystem;
    private BeneficiaryType beneficiaryType;
    private ContractDetail contractDetail;
    private Workflow workflow;
    private TransactionDate transactionDate;
    private List<ContactPerson> contactPersons;
    private List<KeyManagement> keyManagements;
    private List<ShareHolder> shareHolders;
    private List<Address> addresses;
    private List<AuthorizedSignature> authorizedSignatories;
    private List<CorrespondentBank> correspondentBanks;
    private List<IdType> idTypes;
    private List<TypeOfDealing> typeOfDealings;
    private List<UserDecisionLog> userDecisions;

    public void updateFullName(String fullName) {
        this.fullName = fullName;
    }

}
