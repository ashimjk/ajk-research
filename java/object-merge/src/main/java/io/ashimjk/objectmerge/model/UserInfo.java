package io.ashimjk.objectmerge.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -7717475352377390184L;

    private String userId;
    private String userName;
    private UserAction action;

    public enum UserAction {
        INITIAL, AMMEND, DEACTIVATE, REACTIVATE, DELETE, ONTHEFLY, ONTHEFLY_UPDATE
    }

}
