package io.ashimjk.objectmerge.model.shared;

import lombok.Data;

import java.io.Serializable;

@Data
public class Address implements Serializable {

    private static final long serialVersionUID = 6880204196697948431L;

    private String reference;
    private String addressName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String mapUrl;
    private String country;
    private String poBox;
    private boolean defaultAddress = true;

}
