package io.ashimjk.jsondiff.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public
class ChangeLog implements Serializable {

    private String op;
    private String path;
    private Object value;

}