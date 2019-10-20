package io.ashimjk.entityrelationship.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
class CorporateBeneficiary extends BaseEntity {

    @Embedded
    private CorporateId corporateId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<KeyManagement> keyManagements;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ShareHolder> shareHolders;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ContactPerson> contactPersons;

}
