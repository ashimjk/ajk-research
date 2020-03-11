package io.ashimjk.objectmerge.model;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Status {

    INITIAL, ACTIVE, INACTIVE, CANCELLED, REJECTED, DELETED;

    public static Status of(String status) {
        boolean isStatusAvailable = Arrays.stream(Status.values())
                .map(Status::name)
                .collect(Collectors.toList())
                .contains(status);

        return isStatusAvailable ? valueOf(status) : null;
    }
}
