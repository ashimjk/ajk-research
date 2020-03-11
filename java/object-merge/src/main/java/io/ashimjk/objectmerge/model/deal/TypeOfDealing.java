package io.ashimjk.objectmerge.model.deal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(of = "reference")
public class TypeOfDealing implements Serializable {

    private static final long serialVersionUID = -2457907793618698935L;

    private Long id;
    private String reference;

}
