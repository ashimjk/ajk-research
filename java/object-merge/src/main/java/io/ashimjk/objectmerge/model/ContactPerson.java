package io.ashimjk.objectmerge.model;

import io.ashimjk.objectmerge.model.shared.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(of = "reference")
public class ContactPerson implements Serializable {

    private static final long serialVersionUID = 8107883710962236389L;

    private Long id;
    private String reference;
    private String fullName;
    private String jobTitle;
    private String email;
    private String phoneNo;
    private String idType;
    private String nationalNumber;
    private Address address;
    private String profile;
    private Boolean primaryContact;
    private List<String> services = new ArrayList<>();

}
