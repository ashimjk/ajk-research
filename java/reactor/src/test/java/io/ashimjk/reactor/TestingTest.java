package io.ashimjk.reactor;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestingTest {

    private Testing test = new Testing();

    @Test
    public void tenToZero() {
        StepVerifier.create(test.tenToZero())
                .expectNext(10)
                .expectNext(9, 8, 7, 6, 5)
                .expectNextCount(4)
                .expectNext(0)
                .expectComplete()
                .verify();
    }

    @Test
    void multiplyByTenToZero() {
        StepVerifier.create(test.operateOnTenToZero(i -> 10 * i))
                .expectSubscription()
                .expectNextCount(5)
                .assertNext(v -> assertEquals(50, v))
                .expectNext(40, 30, 20, 10, 0)
                .expectComplete()
                .verify();
    }

    @Test
    void divideByTenToZeroErrors() {
        StepVerifier.create(test.operateOnTenToZero(i -> 10 / i))
                .expectNext(1)
                .expectNextCount(4)
                .expectNext(2)
                .expectNextCount(2)
                .expectNext(5, 10)
                .verifyErrorSatisfies(e -> assertThat(e).isInstanceOf(ArithmeticException.class)
                        .hasMessage("divide by zero"));
    }

    @Test
    void namesPerSecond() {
        StepVerifier.create(test.namesPerSecond().doOnNext(System.out::println))
                .recordWith(ArrayList::new)
                .thenConsumeWhile(Objects::nonNull)
                .consumeRecordedWith(l -> assertThat(Testing.NAMES).containsAll(l))
                .verifyComplete();
    }

    @Test
    void namesPerSecondWithoutTime() {
        StepVerifier.withVirtualTime(() -> test.namesPerSecond().doOnNext(System.out::println))
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(1))
                .expectNext("Summer")
                .thenAwait(Duration.ofSeconds(2))
                .expectNextCount(2)
                .thenAwait(Duration.ofMinutes(1))
                .expectNext("Morty", "Rick", "Simon", "Victor")
                .verifyComplete();
    }

}
