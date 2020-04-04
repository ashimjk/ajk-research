package io.ashimjk.reactor;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static io.ashimjk.reactor.Utils.isFastTime;
import static io.ashimjk.reactor.Utils.isSlowTime;
import static java.lang.Thread.sleep;

public class ReactorTests {

    private static long start = System.currentTimeMillis();

    //public static Boolean isSlowTime() {
    //    return (System.currentTimeMillis() - start) % 12_000 >= 3_000;
    //}
    //
    //public static Boolean isFastTime() {
    //    return !isSlowTime();
    //}

    @Test
    void test1() {
        Flux.just("Hello", "World")
                .subscribe(System.out::println);
    }

    @Test
    void test2() {
        List<String> words = Arrays.asList(
                "the",
                "quick",
                "brown",
                "fox",
                "jumps",
                "over",
                "the",
                "lazy",
                "dog"
        );

        Flux.fromIterable(words)
                .map(word -> word.split(""))
                .flatMap(mappedWords -> Flux.fromArray(mappedWords))
                .distinct()
                .sort()
                .zipWith(Flux.range(1, 100), (word, line) -> line + ". " + word)
                .subscribe(System.out::println);
    }

    @Test
    void test3() {
        Flux<String> fastClock = Flux.interval(Duration.ofSeconds(1)).map(tick -> "Fast : " + tick);
        Flux<String> slowClock = Flux.interval(Duration.ofSeconds(2)).map(tick -> "Slow : " + tick);

        Flux<String> clock = Flux.merge(
                fastClock.filter(tick -> isFastTime()),
                slowClock.filter(tick -> isSlowTime())
        );

        Flux<LocalTime> feed = Flux.interval(Duration.ofSeconds(1)).map(tick -> LocalTime.now());

        clock.withLatestFrom(feed, (tick, time) -> tick + " " + time)
                .subscribe(System.out::println);
    }

    @Test
    void test4() {
        SomeFeed<PriceTick> feed = new SomeFeed<>();
        feed.register(new SomeListener() {
            @Override
            public void priceTick(PriceTick event) {
                System.out.println(event);
            }

            @Override
            public void error(Throwable throwable) {

            }
        });
    }

    @SneakyThrows
    @Test
    void test5() {
        SomeFeed<PriceTick> feed = new SomeFeed<>();
        Flux<PriceTick> feedFlux = Flux.create(emitter ->
                feed.register(new SomeListener() {
                    @Override
                    public void priceTick(PriceTick event) {
                        emitter.next(event);
                    }

                    @Override
                    public void error(Throwable throwable) {
                        emitter.error(throwable);
                    }
                }), FluxSink.OverflowStrategy.LATEST);

        ConnectableFlux<PriceTick> connectableFlux = feedFlux.publish();

        connectableFlux.subscribe(System.out::println);
        sleep(2000);
        connectableFlux.subscribe(System.out::println);
        connectableFlux.connect();
        connectableFlux.subscribe(System.out::println);
    }

    @AfterEach
    @SneakyThrows
    void after() {
        sleep(30_000L);
    }

}

