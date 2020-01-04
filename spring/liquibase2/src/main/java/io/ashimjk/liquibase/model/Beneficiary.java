package io.ashimjk.liquibase.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Beneficiary implements Serializable {

    private static final long serialVersionUID = 8899862948712874865L;

    private Long id;
    private String fullName;
    private List<CorrespondentBank> correspondentBanks;

    public void updateFullName(String fullName) {
        this.fullName = fullName;
    }

}
