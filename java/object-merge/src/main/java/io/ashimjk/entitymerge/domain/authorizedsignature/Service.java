package io.ashimjk.entitymerge.domain.authorizedsignature;

import lombok.Data;

import java.io.Serializable;

@Data
class Service implements Serializable {

    private static final long serialVersionUID = -3191617581859324560L;

    private String name;
    private Tier tier;

}
