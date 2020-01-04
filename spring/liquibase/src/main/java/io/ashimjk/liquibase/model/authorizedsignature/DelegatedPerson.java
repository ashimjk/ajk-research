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
public class DelegatedPerson implements Serializable {

    private static final long serialVersionUID = 1748717632914066708L;

    private Long id;
    private String jobTitle;
    private String fullName;
    private String nationalNumber;
    private String signature;
    private String profile;
    private String document;
    private Integer limitAmount;
    private List<String> services;

}
