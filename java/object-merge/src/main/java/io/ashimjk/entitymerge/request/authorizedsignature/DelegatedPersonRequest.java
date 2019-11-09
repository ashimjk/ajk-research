package io.ashimjk.entitymerge.request.authorizedsignature;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public
class DelegatedPersonRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fullName;
    private String title;
    private String nationalNumber;
    private String signature;
    private String document;
    private List<ServiceRequest> services = new ArrayList<>();

}

