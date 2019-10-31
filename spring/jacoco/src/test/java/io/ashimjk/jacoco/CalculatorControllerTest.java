package io.ashimjk.jacoco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CalculatorControllerTest {
    @Test
    void result() {
        CalculatorController c = new CalculatorController(expression -> {
            if (expression.equals("1 + 1")) {
                return 2;
            }
            if (expression.equals("+")) {
                throw new CalculatorException("Invalid expression: +");
            }
            throw new CalculatorException("Unexpected input: " + expression);
        });

        assertEquals("2.0", c.result("1 + 1"));
        assertThrows(CalculatorException.class, () -> c.result("+"), "Invalid expression: +");
    }
}
