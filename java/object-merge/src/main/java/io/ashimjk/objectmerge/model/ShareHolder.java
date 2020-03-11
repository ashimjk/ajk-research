package io.ashimjk.objectmerge.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShareHolder implements Serializable {

    private static final long serialVersionUID = -1998228369437966159L;

    private String reference;
    private String fullName;
    private String nationalNumber;
    private String profile;

}
