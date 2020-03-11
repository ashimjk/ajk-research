package io.ashimjk.objectmerge;

import io.ashimjk.objectmerge.model.*;
import io.ashimjk.objectmerge.model.authorizedsignature.AuthorizedSignature;
import io.ashimjk.objectmerge.model.authorizedsignature.DelegatedPerson;
import io.ashimjk.objectmerge.model.correspondentbank.Account;
import io.ashimjk.objectmerge.model.correspondentbank.CorrespondentBank;
import io.ashimjk.objectmerge.model.deal.TypeOfDealing;
import io.ashimjk.objectmerge.model.shared.Address;
import io.ashimjk.objectmerge.model.shared.IdType;
import io.ashimjk.objectmerge.model.userdecision.UserDecision;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.ashimjk.objectmerge.model.Status.ACTIVE;
import static io.ashimjk.objectmerge.model.UserInfo.UserAction.AMMEND;
import static io.ashimjk.objectmerge.model.UserInfo.UserAction.INITIAL;
import static io.ashimjk.objectmerge.model.deal.DealType.ONLY_CORRESPONDENCE;
import static io.ashimjk.objectmerge.model.shared.BeneficiaryType.CORPORATE;
import static io.ashimjk.objectmerge.model.shared.IdTypeEnum.IdTypeConstants.ID_CARD;
import static io.ashimjk.objectmerge.model.userdecision.ApprovalStatus.APPROVED;
import static io.ashimjk.objectmerge.model.userdecision.ApprovalStatus.REJECTED;

@UtilityClass
public class DataProvider {

    public static Beneficiary originalBeneficiary() {
        UserDecision userDecision = new UserDecision();
        userDecision.setApprovalStatus(REJECTED);

        Beneficiary originalBeneficiary = new Beneficiary();
        originalBeneficiary.setId(123L);
        originalBeneficiary.setReference("REF");
        originalBeneficiary.setProcessInstanceId("PID");
        originalBeneficiary.setCurrentProcess("PROC");
        originalBeneficiary.setCurrentTask("TASK");
        originalBeneficiary.setStatus(ACTIVE);
        originalBeneficiary.setOnTheFly(true);
        originalBeneficiary.setCreationDate(LocalDateTime.now());
        originalBeneficiary.setSourceSystem("trade-finance");

        Address address = new Address();
        address.setAddressName("bkt");
        address.setAddressLine1("bkt2");
        address.setCity("bkt");
        address.setCountry("Nepal");
        address.setPoBox("P-456");

        ContactPerson contactPerson = new ContactPerson();
        contactPerson.setId(1L);
        contactPerson.setReference("CP-REF");
        contactPerson.setFullName("ashimjk");
        contactPerson.setJobTitle("Chief Executive Officer");
        contactPerson.setNationalNumber("1365");
        contactPerson.setPhoneNo("463212");
        contactPerson.setIdType("ID Card");
        contactPerson.setServices(toList("trade-finance"));
        contactPerson.setAddress(address);

        KeyManagement keyManagement = new KeyManagement();
        keyManagement.setReference("KM-REF");

        ShareHolder shareHolder = new ShareHolder();
        shareHolder.setReference("SH-REF");

        AuthorizedSignature authorizedSignature = new AuthorizedSignature();
        authorizedSignature.setId(1L);
        authorizedSignature.setReference("AS-REF");

        DelegatedPerson delegatedPerson = new DelegatedPerson();
        delegatedPerson.setId(1L);
        delegatedPerson.setReference("DP-REF");
        authorizedSignature.setDelegatedPersons(toList(delegatedPerson));

        CorrespondentBank correspondentBank = new CorrespondentBank();
        correspondentBank.setId(1L);
        correspondentBank.setReference("CB-REF");
        correspondentBank.setAddress(address);

        Account account = new Account();
        account.setReference("AC-REF");
        correspondentBank.setAccounts(toList(account));

        IdType idType = new IdType();
        idType.setReference("ID-REF");

        TypeOfDealing typeOfDealing = new TypeOfDealing();
        typeOfDealing.setId(1L);
        typeOfDealing.setReference("TD-REF");

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("123");
        userInfo.setUserName("ashimjk");

        originalBeneficiary.setContactPersons(toList(contactPerson));
        originalBeneficiary.setKeyManagements(toList(keyManagement));
        originalBeneficiary.setShareHolders(toList(shareHolder));
        originalBeneficiary.setAddresses(toList(address));
        originalBeneficiary.setAuthorizedSignatories(toList(authorizedSignature));
        originalBeneficiary.setCorrespondentBanks(toList(correspondentBank));
        originalBeneficiary.setIdTypes(toList(idType));
        originalBeneficiary.setTypeOfDealings(toList(typeOfDealing));

        return originalBeneficiary;
    }

    public static Beneficiary updatedBeneficiary() {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setSourceSystem("trade-finance");
        beneficiary.setSuffixEnglish("Mr");
        beneficiary.setFullName("ashim");
        beneficiary.setArabicName("arabic");
        beneficiary.setSuffixArabic("Mr");
        beneficiary.setEmail("ashim@gmail.com");
        beneficiary.setPhonePrefix("+977");
        beneficiary.setPhoneMobileNo("9841959595");
        beneficiary.setUniqueId("567890");
        beneficiary.setNationality("Nepalese");
        beneficiary.setBeneficiaryType(CORPORATE);
        beneficiary.setIdType(ID_CARD);

        Address address = new Address();
        address.setAddressName("ktm");
        address.setAddressLine1("ktm1");
        address.setCity("ktm");
        address.setCountry("Nepal");
        address.setPoBox("P-123");

        Address address2 = new Address();
        address2.setAddressName("bkt");
        address2.setAddressLine1("bkt2");
        address2.setCity("bkt");
        address2.setCountry("Nepal");
        address2.setPoBox("P-456");

        ContactPerson contactPerson = new ContactPerson();
        contactPerson.setFullName("ashimjk");
        contactPerson.setJobTitle("Chief Executive Officer");
        contactPerson.setNationalNumber("1365");
        contactPerson.setPhoneNo("463212");
        contactPerson.setIdType("ID Card");
        contactPerson.setServices(toList("trade-finance"));
        contactPerson.setAddress(address);

        KeyManagement keyManagement = new KeyManagement();
        keyManagement.setJobTitle("Chief Information Officer");
        keyManagement.setFullName("Abhisekh");
        keyManagement.setNationalNumber("123");

        ShareHolder shareHolder = new ShareHolder();
        shareHolder.setFullName("Saurav");
        shareHolder.setNationalNumber("456");

        CorrespondentBank correspondentBank = new CorrespondentBank();
        correspondentBank.setName("shekar");
        correspondentBank.setAddress(address);
        correspondentBank.setSwiftCode("ACDENPKA");
        correspondentBank.setServices(toList("importer-lc"));

        Account account = new Account();
        account.setIban("KW89NBOK0000000000009235351490");
        account.setCurrency("KWD");

        Account account2 = new Account();
        account2.setIban("KW62NBOK0000000000009235351491");
        account2.setCurrency("KWD");

        correspondentBank.setAccounts(toList(account, account2));

        IdType idType = new IdType();
        idType.setName(ID_CARD);
        idType.setNumber("123");
        idType.setExpiryDate(LocalDate.now());
        idType.setDocument("DOC");

        TypeOfDealing typeOfDealing = new TypeOfDealing();
        typeOfDealing.setDealType(ONLY_CORRESPONDENCE);

        beneficiary.setContactPersons(toList(contactPerson));
        beneficiary.setKeyManagements(toList(keyManagement));
        beneficiary.setShareHolders(toList(shareHolder));
        beneficiary.setAddresses(toList(address, address2));
        beneficiary.setAuthorizedSignatories(toList(authorizedSignature(), authorizedSignature()));
        beneficiary.setCorrespondentBanks(toList(correspondentBank));
        beneficiary.setIdTypes(toList(idType));
        beneficiary.setTypeOfDealings(toList(typeOfDealing));

        return beneficiary;
    }

    public static UserInfo userInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("123");
        userInfo.setUserName("ashimjk");
        return userInfo;
    }

    public static AuthorizedSignature authorizedSignature() {
        AuthorizedSignature authorizedSignature = new AuthorizedSignature();
        authorizedSignature.setFullName("suraj");
        authorizedSignature.setJobTitle("Chief Product Officer");
        authorizedSignature.setNationalNumber("84231");
        authorizedSignature.setSignature("signature");
        authorizedSignature.setLimitAmount(640);
        authorizedSignature.setServices(toList("outgoing"));

        DelegatedPerson delegatedPerson = new DelegatedPerson();
        delegatedPerson.setFullName("shekhar");
        delegatedPerson.setJobTitle("Chief Technical Officer");
        delegatedPerson.setNationalNumber("9846231");
        delegatedPerson.setLimitAmount(890);
        delegatedPerson.setSignature("signature");
        delegatedPerson.setServices(toList("incoming-lg"));

        DelegatedPerson delegatedPerson2 = new DelegatedPerson();
        delegatedPerson2.setFullName("kushal");
        delegatedPerson2.setJobTitle("Chief Executive Officer");
        delegatedPerson2.setNationalNumber("897612");
        delegatedPerson2.setLimitAmount(540);
        delegatedPerson2.setSignature("signature");
        delegatedPerson2.setServices(toList("outgoing-lg"));

        authorizedSignature.setDelegatedPersons(toList(delegatedPerson, delegatedPerson2));
        return authorizedSignature;
    }

    public static UserDecision userDecisionLog() {
        UserDecision userDecision = new UserDecision();
        userDecision.setApprovalStatus(APPROVED);
        userDecision.setComments("just a comments");
        return userDecision;
    }

    public <T> List<T> toList(T... obj) {
        return new ArrayList<>(Arrays.asList(obj));
    }

}
