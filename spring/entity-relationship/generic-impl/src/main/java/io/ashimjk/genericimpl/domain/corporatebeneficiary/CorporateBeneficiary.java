package io.ashimjk.genericimpl.domain.corporatebeneficiary;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static io.ashimjk.genericimpl.exception.DomainAsserts.nonNullList;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;

@Getter
@Entity
@EqualsAndHashCode(exclude = "id")
public class CorporateBeneficiary implements Serializable {

    private static final long serialVersionUID = 6286111548125257187L;

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Embedded
    private CorporateId corporateId;

    @ElementCollection
    private List<KeyManagement> keyManagements = new ArrayList<>();

    @ElementCollection
    private List<ShareHolder> shareHolders = new ArrayList<>();

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "corporate_beneficiary_id")
    private List<ContactPerson> contactPersons = new ArrayList<>();

    public static CorporateBeneficiary create(String corporateId,
                                              List<ContactPerson> contactPersons,
                                              List<KeyManagement> keyManagements,
                                              List<ShareHolder> shareHolders) {

        CorporateBeneficiary beneficiary = new CorporateBeneficiary();

        beneficiary.corporateId = CorporateId.create(corporateId);

        if (nonNullList(contactPersons)) {
            beneficiary.contactPersons.addAll(contactPersons);
        }

        if (nonNullList(keyManagements)) {
            beneficiary.keyManagements.addAll(keyManagements);
        }

        if (nonNullList(shareHolders)) {
            beneficiary.shareHolders.addAll(shareHolders);
        }

        return beneficiary;
    }

    public void updateId(Long corporateBeneficiaryId) {
        this.id = corporateBeneficiaryId;
    }
}
