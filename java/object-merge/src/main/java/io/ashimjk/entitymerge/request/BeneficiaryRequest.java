package io.ashimjk.entitymerge.request;

import io.ashimjk.entitymerge.request.authorizedsignature.AuthorizedSignatureRequest;
import io.ashimjk.entitymerge.request.correspondentbank.CorrespondentBankRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class BeneficiaryRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fullName;
    private String nationalNumber;
    private String startingDate;
    private String reviewDate;
    private String endingDate;

    private List<AddressRequest> addresses = new ArrayList<>();
    private List<AuthorizedSignatureRequest> authorizedSignatories = new ArrayList<>();
    private List<CorrespondentBankRequest> correspondentBanks = new ArrayList<>();

}

