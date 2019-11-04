package io.ashimjk.entitymerge.domain.authorizedsignature;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
class DelegatedPerson implements Serializable {

    private static final long serialVersionUID = 7923179030032461293L;

    private String fullName;
    private String nationalNumber;
    private String title;
    private String signature;
    private String document;
    private List<Service> services = new ArrayList<>();

}
