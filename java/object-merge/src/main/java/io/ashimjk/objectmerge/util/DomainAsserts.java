package io.ashimjk.objectmerge.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class DomainAsserts {

    public static <T> boolean isNullList(List<T> list) {
        return isNull(list) || list.isEmpty();
    }

    public static <T> boolean nonNullList(List<T> list) {
        return !isNullList(list);
    }

    public static boolean isBlank(String value) {
        return isNull(value) || value.trim().isEmpty();
    }

    public static boolean notBlank(String value) {
        return !isNull(value) && !value.trim().isEmpty();
    }

    private static boolean isNull(Object object) {
        return Objects.isNull(object);
    }


}
