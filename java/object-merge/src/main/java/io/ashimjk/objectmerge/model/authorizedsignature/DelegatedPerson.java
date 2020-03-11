package io.ashimjk.objectmerge.model.authorizedsignature;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(of = "reference")
public class DelegatedPerson implements Serializable {

    private static final long serialVersionUID = 1748717632914066708L;

    private Long id;
    private String reference;
    private String fullName;
    private String jobTitle;
    private String nationalNumber;
    private String signature;
    private String profile;
    private String document;
    private Integer limitAmount;
    private List<String> services = new ArrayList<>();

}
