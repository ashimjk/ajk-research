package io.ashimjk.part2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MoneyTest {

    static Stream<Arguments> negativeMoneyProvider() {
        return Stream.of(
                arguments(-1, 0, 0, 0, 0, 0),
                arguments(0, -2, 0, 0, 0, 0),
                arguments(0, 0, -3, 0, 0, 0),
                arguments(0, 0, 0, -4, 0, 0),
                arguments(0, 0, 0, 0, -5, 0),
                arguments(0, 0, 0, 0, 0, -6)
        );
    }

    static Stream<Arguments> totalAmountProvider() {
        return Stream.of(
                arguments(0, 0, 0, 0, 0, 0, 0),
                arguments(1, 0, 0, 0, 0, 0, 0.01),
                arguments(1, 2, 0, 0, 0, 0, 0.21),
                arguments(1, 2, 3, 0, 0, 0, 0.96),
                arguments(1, 2, 3, 4, 0, 0, 4.96),
                arguments(1, 2, 3, 4, 5, 0, 29.96),
                arguments(1, 2, 3, 4, 5, 6, 149.96),
                arguments(11, 0, 0, 0, 0, 0, 0.11),
                arguments(110, 0, 0, 0, 100, 0, 501.1)
        );
    }

    @Test
    void sum_of_two_moneys_produces_correct_result() {
        Money money1 = new Money(1, 2, 3, 4, 5, 6);
        Money money2 = new Money(1, 2, 3, 4, 5, 6);

        Money sum = Money.add(money1, money2);

        assertEquals(2, sum.getOneCentCount());
        assertEquals(4, sum.getTenCentCount());
        assertEquals(6, sum.getQuarterCount());
        assertEquals(8, sum.getOneDollarCount());
        assertEquals(10, sum.getFiveDollarCount());
        assertEquals(12, sum.getTwentyDollarCount());
    }

    @Test
    void two_money_instances_equal_if_contain_the_same_money_amount() {
        Money money1 = new Money(1, 2, 3, 4, 5, 6);
        Money money2 = new Money(1, 2, 3, 4, 5, 6);

        assertEquals(money1, money2);
        assertEquals(money1.hashCode(), money2.hashCode());
    }

    @Test
    void two_money_instances_do_not_equal_if_contain_different_money_amounts() {
        Money dollar = new Money(0, 0, 0, 1, 0, 0);
        Money hundredCents = new Money(100, 0, 0, 0, 0, 0);

        assertNotEquals(dollar, hundredCents);
        assertNotEquals(dollar.hashCode(), hundredCents.hashCode());
    }

    @ParameterizedTest
    @MethodSource("negativeMoneyProvider")
    void cannot_create_money_with_negative_value(
            int oneCentCount,
            int tenCentCount,
            int quarterCount,
            int oneDollarCount,
            int fiveDollarCount,
            int twentyDollarCount) {

        assertThrows(InvalidArgumentException.class,
                () -> new Money(
                        oneCentCount,
                        tenCentCount,
                        quarterCount,
                        oneDollarCount,
                        fiveDollarCount,
                        twentyDollarCount)
        );
    }

    @ParameterizedTest
    @MethodSource("totalAmountProvider")
    void amount_is_calculated_correctly(
            int oneCentCount,
            int tenCentCount,
            int quarterCount,
            int oneDollarCount,
            int fiveDollarCount,
            int twentyDollarCount,
            double expectedAmount) {

        Money money = new Money(
                oneCentCount,
                tenCentCount,
                quarterCount,
                oneDollarCount,
                fiveDollarCount,
                twentyDollarCount);

        assertEquals(expectedAmount, money.getAmount());
    }

    @Test
    void subtraction_of_two_moneys_produces_correct_result() {
        Money money1 = new Money(10, 10, 10, 10, 10, 10);
        Money money2 = new Money(1, 2, 3, 4, 5, 6);

        Money result = Money.minus(money1, money2);

        assertEquals(9, result.getOneCentCount());
        assertEquals(8, result.getTenCentCount());
        assertEquals(7, result.getQuarterCount());
        assertEquals(6, result.getOneDollarCount());
        assertEquals(5, result.getFiveDollarCount());
        assertEquals(4, result.getTwentyDollarCount());
    }

    @Test
    void cannot_subtract_more_than_exists() {
        Money money1 = new Money(0, 1, 0, 0, 0, 0);
        Money money2 = new Money(1, 0, 0, 0, 0, 0);

        assertThrows(InvalidArgumentException.class, () -> Money.minus(money1, money2));
    }

}