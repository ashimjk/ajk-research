package io.ashimjk.document.upload.util;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class IDGenerator {
    public static String newId() {
        return UUID.randomUUID().toString();
    }

}
