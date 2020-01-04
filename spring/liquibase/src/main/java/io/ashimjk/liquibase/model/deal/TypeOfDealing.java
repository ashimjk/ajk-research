package io.ashimjk.liquibase.model.deal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class TypeOfDealing implements Serializable {

    private static final long serialVersionUID = -2457907793618698935L;

    private Long id;
    private DealType dealType;
    private List<String> services;

}
