package io.ashimjk.entitymerge.request.authorizedsignature;

import lombok.Data;

import java.io.Serializable;

@Data
class ServiceRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private TierRequest tier = null;

}

