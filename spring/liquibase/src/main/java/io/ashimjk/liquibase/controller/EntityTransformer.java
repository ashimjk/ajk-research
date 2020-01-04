package io.ashimjk.liquibase.controller;

import io.ashimjk.liquibase.entity.BeneficiaryEntity;
import io.ashimjk.liquibase.entity.ContactPersonEntity;
import io.ashimjk.liquibase.entity.KeyManagementEntity;
import io.ashimjk.liquibase.entity.ShareHolderEntity;
import io.ashimjk.liquibase.entity.authorizedsignature.AuthorizedSignatureEntity;
import io.ashimjk.liquibase.entity.authorizedsignature.DelegatedPersonEntity;
import io.ashimjk.liquibase.entity.contractdetail.ContractDetailEntity;
import io.ashimjk.liquibase.entity.correspondentbank.AccountEntity;
import io.ashimjk.liquibase.entity.correspondentbank.CorrespondentBankEntity;
import io.ashimjk.liquibase.entity.deal.TypeOfDealingEntity;
import io.ashimjk.liquibase.entity.shared.AddressEntity;
import io.ashimjk.liquibase.entity.shared.IdTypeEntity;
import io.ashimjk.liquibase.entity.shared.TransactionDateEntity;
import io.ashimjk.liquibase.entity.shared.WorkflowEntity;
import io.ashimjk.liquibase.entity.userdecision.UserDecisionLogEntity;
import io.ashimjk.liquibase.model.*;
import io.ashimjk.liquibase.model.authorizedsignature.AuthorizedSignature;
import io.ashimjk.liquibase.model.authorizedsignature.DelegatedPerson;
import io.ashimjk.liquibase.model.contractdetail.ContractDetail;
import io.ashimjk.liquibase.model.correspondentbank.Account;
import io.ashimjk.liquibase.model.correspondentbank.CorrespondentBank;
import io.ashimjk.liquibase.model.deal.TypeOfDealing;
import io.ashimjk.liquibase.model.shared.Address;
import io.ashimjk.liquibase.model.shared.IdType;
import io.ashimjk.liquibase.model.shared.TransactionDate;
import io.ashimjk.liquibase.model.shared.Workflow;
import io.ashimjk.liquibase.model.userdecision.UserDecisionLog;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EntityTransformer {

    private static TypeOfDealing typeOfDealing(TypeOfDealingEntity entity) {
        return new TypeOfDealing(entity.getId(), entity.getDealType(), entity.getServices());
    }

    private static Workflow workflow(WorkflowEntity entity) {
        return Workflow.builder()
                .processInstanceId(entity.getProcessInstanceId())
                .currentProcess(entity.getCurrentProcess())
                .currentTask(entity.getCurrentTask())
                .status(entity.getStatus())
                .build();
    }

    private static UserDecisionLog userDecisionLog(UserDecisionLogEntity entity) {
        return UserDecisionLog.builder()
                .userId(entity.getUserId())
                .action(entity.getAction())
                .comment(entity.getComment())
                .date(entity.getDate())
                .approvalStatus(entity.getApprovalStatus())
                .build();
    }

    private static IdType idType(IdTypeEntity entity) {
        return new IdType(
                entity.getName(),
                entity.getNumber(),
                entity.getDocument(),
                safeDate(entity.getExpiryDate()));
    }

    private static CorrespondentBank correspondentBank(CorrespondentBankEntity entity) {
        return new CorrespondentBank(
                entity.getId(),
                entity.getName(),
                entity.getSwiftCode(),
                safeObject(entity.getAddress(), EntityTransformer::address),
                entity.getServices(),
                safeList(entity.getAccounts(), EntityTransformer::account)
        );
    }

    private static Account account(AccountEntity entity) {
        return new Account(entity.getIban(), entity.getCurrency(), entity.getAccountNumber(), entity.getAccountAlias());
    }

    private static DelegatedPerson delegatedPerson(DelegatedPersonEntity entity) {
        return new DelegatedPerson(
                entity.getId(),
                entity.getJobTitle(),
                entity.getFullName(),
                entity.getNationalNumber(),
                entity.getSignature(),
                entity.getProfile(),
                entity.getDocument(),
                entity.getLimitAmount(),
                entity.getServices()
        );
    }

    private static KeyManagement keyManagement(KeyManagementEntity entity) {
        return new KeyManagement(
                entity.getJobTitle(),
                entity.getFullName(),
                entity.getNationalNumber(),
                entity.getProfile()
        );
    }

    private static ShareHolder shareHolder(ShareHolderEntity entity) {
        return new ShareHolder(
                entity.getFullName(),
                entity.getNationalNumber(),
                entity.getProfile()
        );
    }

    private static ContactPerson contactPerson(ContactPersonEntity entity) {
        return new ContactPerson(
                entity.getId(),
                entity.getJobTitle(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getPhoneNo(),
                entity.getNationalNumber(),
                safeObject(entity.getAddress(), EntityTransformer::address),
                entity.getServices(),
                entity.getProfile(),
                entity.getPrimaryContact()
        );
    }

    private static Address address(AddressEntity entity) {
        return new Address(
                entity.getAddressName(),
                entity.getAddressLine1(),
                entity.getAddressLine2(),
                entity.getCity(),
                entity.getMapUrl(),
                entity.getCountry(),
                entity.getPoBox(),
                entity.isDefaultAddress());
    }

    private static ContractDetail contractDetail(ContractDetailEntity entity) {
        return new ContractDetail(
                entity.getNumber(),
                safeDate(entity.getFrom()),
                safeDate(entity.getTo()),
                entity.getDetail(),
                entity.getAmount(),
                safeObject(entity.getBalance(), BigDecimal::toString),
                safeObject(entity.getFeesAndCharges(), BigDecimal::toString),
                entity.getTermsOfPayment()
        );
    }

    private static TransactionDate transactionDate(TransactionDateEntity entity) {
        return new TransactionDate(safeDate(entity.getStartingDate()), safeDate(entity.getReviewDate()), safeDate(entity.getEndingDate()));
    }

    private static <T, R> R safeObject(T input, Function<T, R> function) {
        return Optional.ofNullable(input)
                .map(function)
                .orElse(null);
    }

    private static <T, R> List<R> safeList(List<T> input, Function<T, R> transformerFunc) {
        return Optional.ofNullable(input).orElseGet(Collections::emptyList)
                .stream()
                .map(transformerFunc)
                .collect(Collectors.toList());
    }

    private static <T, R> List<R> safeSet(Set<T> input, Function<T, R> transformerFunc) {
        return Optional.ofNullable(input).orElseGet(Collections::emptySet)
                .stream()
                .map(transformerFunc)
                .collect(Collectors.toList());
    }

    private static String safeDate(LocalDate localDate) {
        return DateTimeFormatter.ISO_LOCAL_DATE.format(localDate);
    }

    private static AuthorizedSignature authorizedSignature(AuthorizedSignatureEntity entity) {
        return new AuthorizedSignature(
                entity.getId(),
                entity.getJobTitle(),
                entity.getFullName(),
                entity.getNationalNumber(),
                entity.getSignature(),
                entity.getLimitAmount(),
                entity.getProfile(),
                entity.getDocument(),
                safeObject(entity.getDelegatedPerson(), EntityTransformer::delegatedPerson),
                entity.getServices()
        );
    }

    public Beneficiary toBeneficiary(BeneficiaryEntity entity) {
        if (entity == null) {
            return null;
        }

        return BeneficiaryFactory.beneficiary(
                entity.getId(),
                entity.getFullName(),
                entity.getReference(),
                entity.getIdType(),
                entity.getCreatedDate(),
                entity.getSourceSystem(),
                entity.getBeneficiaryType(),

                safeObject(entity.getContractDetail(), EntityTransformer::contractDetail),
                safeObject(entity.getWorkflow(), EntityTransformer::workflow),
                safeObject(entity.getTransactionDate(), EntityTransformer::transactionDate),
                safeList(entity.getContactPersons(), EntityTransformer::contactPerson),
                safeList(entity.getKeyManagements(), EntityTransformer::keyManagement),
                safeList(entity.getShareHolders(), EntityTransformer::shareHolder),

                safeList(entity.getAddresses(), EntityTransformer::address),
                safeList(entity.getAuthorizedSignatories(), EntityTransformer::authorizedSignature),
                safeList(entity.getCorrespondentBanks(), EntityTransformer::correspondentBank),
                safeList(entity.getIdTypes(), EntityTransformer::idType),
                safeList(entity.getTypeOfDealings(), EntityTransformer::typeOfDealing),
                safeList(entity.getUserDecisions(), EntityTransformer::userDecisionLog)
        );
    }

}
