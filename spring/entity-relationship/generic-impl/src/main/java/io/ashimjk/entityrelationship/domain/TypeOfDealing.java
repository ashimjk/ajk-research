package io.ashimjk.entityrelationship.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
class TypeOfDealing extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private DealType dealType;

    @ElementCollection
    private List<String> services;

    public enum DealType {
        PAY, RECEIVE, BOTH, ONLY_CORRESPONDENCE
    }

}
