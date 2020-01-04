package io.ashimjk.liquibase.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class KeyManagement implements Serializable {

    private static final long serialVersionUID = 7313221370843717500L;

    private String jobTitle;
    private String fullName;
    private String nationalNumber;
    private String profile;

}
