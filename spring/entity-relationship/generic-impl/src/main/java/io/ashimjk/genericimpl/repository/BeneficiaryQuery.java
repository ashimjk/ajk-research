package io.ashimjk.genericimpl.repository;

import io.ashimjk.genericimpl.domain.Beneficiary;
import io.ashimjk.genericimpl.domain.BeneficiaryType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class BeneficiaryQuery {

    private String reference;
    private String fullName;
    private BeneficiaryType beneficiaryType;
    private String nationalNumber;
    private LocalDate startingDate;
    private LocalDate endingDate;
    private LocalDate reviewDate;
    private Beneficiary.Status status;

}
