package io.ashimjk.jacoco;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CalculatorTest {

    static Stream<Arguments> data() {
        return Stream.of(
                arguments("1 + 1", 2.0, null),
                arguments("1 + 1 + 1", 3.0, null),
                arguments("1–1", 0.0, new CalculatorException("Invalid expression: 1-1")),
                arguments("1 * 1", 1.0, null),
                arguments("1 / 1", 1.0, null),
                arguments("( 1 + 1 )", 2.0, null),
                arguments("+", 0.0, new CalculatorException("Invalid expression: +")),
                arguments("1 1", 0.0, new CalculatorException("Invalid expression: 1 1")),
                arguments("1 / 1 * 1 ", 1.0, null),
                arguments("( 1–1 )", 0.0, new CalculatorException("Invalid expression: ( 1–1 )")),
                arguments("( 1 + 1–1 * 1 / 1 )", 1.0, new CalculatorException("Invalid expression: ( 1 + 1–1 * 1 / 1 )")),
                arguments("( 1 + 1 * 1 )", 2.0, null),
                arguments("1 ( 1", 0.0, new CalculatorException("Invalid expression: 1 ( 1")),
                arguments("1 + + 1", 0.0, new CalculatorException("Invalid expression: 1 + + 1")),
                arguments("6 / 3", 2.0, null),
                arguments("1 - 1 * 2", -1.0, null)
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void testProcess(String input, Double expected, Exception exception) {

        Calculator c = new CalculatorImpl();

        if (Objects.isNull(exception)) {
            assertThat(c.process(input)).isEqualTo(expected);
        } else {
            assertThrows(exception.getClass(), () -> c.process(input), exception.getMessage());
        }

    }
}