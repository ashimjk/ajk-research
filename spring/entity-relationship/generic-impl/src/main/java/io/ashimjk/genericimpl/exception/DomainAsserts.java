package io.ashimjk.genericimpl.exception;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

@UtilityClass
@Slf4j
public class DomainAsserts {

    public static <T> boolean nonNullList(List<T> list) {
        return nonNull(list) && !list.isEmpty();
    }

    public static <T> void nonNullList(List<T> list, String errorCode) {
        if (Objects.isNull(list) || list.isEmpty()) {
            raiseDomainViolation(errorCode);
        }
    }

    public static void isNull(Object value, String errorCode) {

        if (Objects.isNull(value)) {
            raiseDomainViolation(errorCode);
        }
    }

    public static void isNotBlank(String value, String errorCode) {
        DomainAsserts.isNull(value, errorCode);

        if (value.trim().isEmpty()) {
            raiseDomainViolation(errorCode);
        }
    }

    public static void isBigDecimal(String value, String errorCode) {
        try {
            new BigDecimal(value);
        } catch (NumberFormatException exception) {
            raiseDomainViolation(errorCode);
        }
    }

    public static <T> void isNullOrEmpty(Collection<T> collections, String errorCode) {
        DomainAsserts.isNull(collections, errorCode);

        if (collections.isEmpty()) {
            raiseDomainViolation(errorCode);
        }
    }

    private static void raiseDomainViolation(String errorCode) {
        if (log.isDebugEnabled()) {
            log.debug("Error Code : {}", errorCode);
        }
        throw new DomainViolationException(errorCode);
    }

}
