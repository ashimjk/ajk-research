package io.ashimjk.liquibase.model;

import io.ashimjk.liquibase.model.authorizedsignature.AuthorizedSignature;
import io.ashimjk.liquibase.model.contractdetail.ContractDetail;
import io.ashimjk.liquibase.model.correspondentbank.CorrespondentBank;
import io.ashimjk.liquibase.model.deal.TypeOfDealing;
import io.ashimjk.liquibase.model.shared.*;
import io.ashimjk.liquibase.model.userdecision.UserDecisionLog;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class BeneficiaryFactory {

    public static Beneficiary create(
            String fullName,
            String idType,
            BeneficiaryType beneficiaryType,
            TransactionDate transactionDate,
            ContractDetail contractDetail,
            List<ContactPerson> contactPersons,
            List<KeyManagement> keyManagements,
            List<ShareHolder> shareHolders,
            List<Address> addresses,
            List<AuthorizedSignature> authorizedSignatories,
            List<CorrespondentBank> correspondentBanks,
            List<IdType> idTypes,
            List<TypeOfDealing> typeOfDealings
    ) {
        return Beneficiary
                .builder()
                .fullName(fullName)
                .idType(idType)
                .beneficiaryType(beneficiaryType)
                .contractDetail(contractDetail)
                .workflow(new Workflow(null, null, null, null))
                .transactionDate(transactionDate)
                .contactPersons(contactPersons)
                .keyManagements(keyManagements)
                .shareHolders(shareHolders)
                .addresses(addresses)
                .authorizedSignatories(authorizedSignatories)
                .correspondentBanks(correspondentBanks)
                .idTypes(idTypes)
                .typeOfDealings(typeOfDealings)
                .userDecisions(new ArrayList<>())
                .build();
    }

    public static Beneficiary beneficiary(
            Long id,
            String fullName,
            String reference,
            String idType,
            LocalDateTime createdDate,
            String sourceSystem,
            BeneficiaryType beneficiaryType,
            ContractDetail contractDetail,
            Workflow workflow,
            TransactionDate transactionDate,
            List<ContactPerson> contactPersons,
            List<KeyManagement> keyManagements,
            List<ShareHolder> shareHolders,
            List<Address> addresses,
            List<AuthorizedSignature> authorizedSignatories,
            List<CorrespondentBank> correspondentBanks,
            List<IdType> idTypes,
            List<TypeOfDealing> typeOfDealings,
            List<UserDecisionLog> userDecisions
    ) {
        return Beneficiary.builder()
                .id(id)
                .fullName(fullName)
                .reference(reference)
                .idType(idType)
                .createdDate(createdDate)
                .sourceSystem(sourceSystem)
                .beneficiaryType(beneficiaryType)
                .contractDetail(contractDetail)
                .workflow(workflow)
                .transactionDate(transactionDate)
                .contactPersons(contactPersons)
                .keyManagements(keyManagements)
                .shareHolders(shareHolders)
                .addresses(addresses)
                .authorizedSignatories(authorizedSignatories)
                .correspondentBanks(correspondentBanks)
                .idTypes(idTypes)
                .typeOfDealings(typeOfDealings)
                .userDecisions(userDecisions)
                .build();
    }

}
