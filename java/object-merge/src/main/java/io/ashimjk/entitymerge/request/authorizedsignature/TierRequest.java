package io.ashimjk.entitymerge.request.authorizedsignature;

import lombok.Data;

import java.io.Serializable;

@Data
class TierRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String from;
    private String to;

}

