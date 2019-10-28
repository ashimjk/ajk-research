package io.ashimjk.entityrelationship.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
class ContactPerson extends BaseEntity {

    private String title;
    private String fullName;
    private String email;
    private String phoneNo;

    @Embedded
    private EmbeddableAddress address;

    private String nationalNumber;

    @ElementCollection
    private List<String> services;

}
