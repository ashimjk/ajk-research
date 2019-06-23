package io.ashimjk.helper;

public class ConversionException extends RuntimeException {
    public ConversionException(Throwable throwable) {
        super(throwable);
    }

    public ConversionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
