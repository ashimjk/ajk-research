package io.ashimjk.entitymerge.domain;

import io.ashimjk.entitymerge.domain.authorizedsignature.AuthorizedSignature;
import io.ashimjk.entitymerge.domain.correspondentbank.CorrespondentBank;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Beneficiary implements Serializable {

    private static final long serialVersionUID = -6277460762817498392L;

    private String reference;
    private String fullName;
    private String nationalNumber;
    private LocalDate startingDate;
    private LocalDate endingDate;
    private LocalDate reviewDate;

    private List<Address> addresses = new ArrayList<>();
    private List<AuthorizedSignature> authorizedSignatories = new ArrayList<>();
    private List<CorrespondentBank> correspondentBanks = new ArrayList<>();

}
