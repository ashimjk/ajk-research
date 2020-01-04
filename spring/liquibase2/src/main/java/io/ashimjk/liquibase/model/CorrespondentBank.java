package io.ashimjk.liquibase.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class CorrespondentBank implements Serializable {

    private static final long serialVersionUID = 213942640908917631L;

    private String name;

    public void updateName(String name) {
        this.name = name;
    }

}
