package io.ashimjk.liquibase.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class ShareHolder implements Serializable {

    private static final long serialVersionUID = -1998228369437966159L;

    private String fullName;
    private String nationalNumber;
    private String profile;

}
