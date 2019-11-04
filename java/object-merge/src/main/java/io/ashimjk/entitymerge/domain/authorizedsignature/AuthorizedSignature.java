package io.ashimjk.entitymerge.domain.authorizedsignature;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class AuthorizedSignature implements Serializable {

    private static final long serialVersionUID = 2056849173358942133L;

    private String fullName;
    private String nationalNumber;
    private String title;
    private String signature;
    private String document;
    private List<Service> services = new ArrayList<>();
    private DelegatedPerson delegatedPerson;

}
