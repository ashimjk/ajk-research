package io.ashimjk.liquibase.model.authorizedsignature;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorizedSignature implements Serializable {

    private static final long serialVersionUID = 4895934224691399039L;

    private Long id;
    private String jobTitle;
    private String fullName;
    private String nationalNumber;
    private String signature;
    private Integer limitAmount;
    private String profile;
    private String document;
    private DelegatedPerson delegatedPerson;
    private List<String> services;

}
