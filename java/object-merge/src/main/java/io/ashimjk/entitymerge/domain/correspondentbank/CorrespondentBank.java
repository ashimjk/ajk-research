package io.ashimjk.entitymerge.domain.correspondentbank;

import io.ashimjk.entitymerge.domain.Address;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class CorrespondentBank implements Serializable {

    private static final long serialVersionUID = -3227813107196832875L;

    private String name;
    private String bicCode;
    private Address address;
    private List<String> services = new ArrayList<>();
    private Set<Account> accounts = new HashSet<>();

}
