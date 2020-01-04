package io.ashimjk.liquibase.model.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Address implements Serializable {

    private static final long serialVersionUID = 6880204196697948431L;

    private String addressName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String mapUrl;
    private String country;
    private String poBox;
    private boolean defaultAddress = true;

}
