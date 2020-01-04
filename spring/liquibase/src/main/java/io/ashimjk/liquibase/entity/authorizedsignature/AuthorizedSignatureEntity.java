package io.ashimjk.liquibase.entity.authorizedsignature;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name = "authorized_signature")
public class AuthorizedSignatureEntity implements Serializable {

    private static final long serialVersionUID = -7014140480873521622L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String fullName;
    private String nationalNumber;
    private String jobTitle;
    private Integer limitAmount;
    private String profile;
    private String signature;
    private String document;

    @ElementCollection
    private List<String> services = new ArrayList<>();

    @OneToOne(cascade = ALL, orphanRemoval = true)
    private DelegatedPersonEntity delegatedPerson;

}
