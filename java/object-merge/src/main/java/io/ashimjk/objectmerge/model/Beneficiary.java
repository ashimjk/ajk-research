package io.ashimjk.objectmerge.model;

import io.ashimjk.objectmerge.model.authorizedsignature.AuthorizedSignature;
import io.ashimjk.objectmerge.model.correspondentbank.CorrespondentBank;
import io.ashimjk.objectmerge.model.deal.TypeOfDealing;
import io.ashimjk.objectmerge.model.shared.Address;
import io.ashimjk.objectmerge.model.shared.BeneficiaryType;
import io.ashimjk.objectmerge.model.shared.IdType;
import io.ashimjk.objectmerge.model.userdecision.UserDecision;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
public class Beneficiary implements Serializable {

    private static final long serialVersionUID = 8899862948712874865L;

    private Long id;
    private String reference;
    private String uniqueId;
    private String suffixEnglish;
    private String fullName;
    private String arabicName;
    private String suffixArabic;
    private String email;
    private String phonePrefix;
    private String phoneMobileNo;
    private String nationality;
    private BeneficiaryType beneficiaryType;
    private String idType;

    private LocalDate startingDate;
    private LocalDate reviewDate;
    private LocalDate endingDate;

    private LocalDateTime activationDate;
    private LocalDateTime deactivationDate;

    private LocalDateTime creationDate;
    private LocalDateTime lastUpdatedDate;
    private List<UserInfo> userInfos = new ArrayList<>();

    private boolean onTheFly;
    private String sourceSystem;

    private String processInstanceId;
    private String currentProcess;
    private String currentTask;
    private Status status;

    private List<ContactPerson> contactPersons = new ArrayList<>();
    private List<KeyManagement> keyManagements = new ArrayList<>();
    private List<ShareHolder> shareHolders = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();
    private List<AuthorizedSignature> authorizedSignatories = new ArrayList<>();
    private List<CorrespondentBank> correspondentBanks = new ArrayList<>();
    private List<IdType> idTypes = new ArrayList<>();
    private List<TypeOfDealing> typeOfDealings = new ArrayList<>();
    private List<UserDecision> userDecisions = new ArrayList<>();

}
