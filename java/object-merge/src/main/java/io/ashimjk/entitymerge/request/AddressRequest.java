package io.ashimjk.entitymerge.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String addressName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String poBox;

}

