package io.ashimjk.liquibase.model;

import io.ashimjk.liquibase.model.shared.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class ContactPerson implements Serializable {

    private static final long serialVersionUID = 8107883710962236389L;

    private Long id;
    private String jobTitle;
    private String fullName;
    private String email;
    private String phoneNo;
    private String nationalNumber;
    private Address address;
    private List<String> services;
    private String profile;
    private Boolean primaryContact;

    public void updateFullName(String fullName) {
        this.fullName = fullName;
    }

}
