package io.ashimjk.entityrelationship.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CorporateBeneficiary extends BaseEntity{

    @Embedded
    private CorporateId corporateId;

    @OneToMany
    private List<KeyManagement> keyManagements;

    @OneToMany
    private List<ShareHolder> shareHolders;

    @OneToMany
    private List<ContactPerson> contactPersons;

}
