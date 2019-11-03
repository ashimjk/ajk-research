package io.ashimjk.genericimpl.exception;

public class DomainViolationException extends RuntimeException {

    public DomainViolationException(String code) {
        super(code);
    }

}
