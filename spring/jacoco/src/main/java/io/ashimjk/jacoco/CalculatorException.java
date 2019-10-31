package io.ashimjk.jacoco;

class CalculatorException extends RuntimeException {

    CalculatorException(String message) {
        super(message);
    }

    CalculatorException(String message, Exception ex) {
        super(message, ex);
    }

}
