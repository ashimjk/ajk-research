package io.ashimjk.entitymerge.request.correspondentbank;

import io.ashimjk.entitymerge.request.AddressRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class CorrespondentBankRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String bicCode;
    private AddressRequest address = null;
    private List<String> services = new ArrayList<>();
    private List<AccountRequest> accounts = new ArrayList<>();

}

