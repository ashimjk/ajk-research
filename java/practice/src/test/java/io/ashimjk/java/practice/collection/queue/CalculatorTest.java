package io.ashimjk.java.practice.collection.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    void shouldEvaluateConstants() {
        int result = this.calculator.evaluate("1");
        assertEquals(1, result);
    }

    @Test
    void shouldSupportAdding() {
        int result = this.calculator.evaluate("1 + 2");
        assertEquals(3, result);
    }

    @Test
    void shouldSupportSubtraction() {
        int result = this.calculator.evaluate("1 - 2");
        assertEquals(-1, result);
    }

    @Test
    void shouldSupportComplexOperation() {
        int result = this.calculator.evaluate("1 - 3 + 2 + 4");
        assertEquals(4, result);
    }

}
