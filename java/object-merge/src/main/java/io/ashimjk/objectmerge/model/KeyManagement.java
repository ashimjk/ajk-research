package io.ashimjk.objectmerge.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class KeyManagement implements Serializable {

    private static final long serialVersionUID = 7313221370843717500L;

    private String reference;
    private String fullName;
    private String jobTitle;
    private String nationalNumber;
    private String profile;

}
