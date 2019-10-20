package io.ashimjk.entityrelationship.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TypeOfDealing extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private DealType dealType;

    @ElementCollection
    private List<String> services;

    public enum DealType {
        PAY, RECEIVE, BOTH, ONLY_CORRESPONDENCE
    }

}
