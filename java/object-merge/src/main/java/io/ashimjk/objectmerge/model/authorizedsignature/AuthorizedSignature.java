package io.ashimjk.objectmerge.model.authorizedsignature;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(of = "reference")
public class AuthorizedSignature implements Serializable {

    private static final long serialVersionUID = 4895934224691399039L;

    private Long id;
    private String reference;
    private String fullName;
    private String jobTitle;
    private String nationalNumber;
    private String signature;
    private Integer limitAmount;
    private String profile;
    private String document;
    private List<String> services = new ArrayList<>();
    private List<DelegatedPerson> delegatedPersons = new ArrayList<>();

}
