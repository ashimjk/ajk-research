package io.ashimjk.reactor;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.*;
import java.util.function.Function;

public class Testing {

    static final Set<String> NAMES = new LinkedHashSet<>(Arrays.asList(
            "Victor", "Simon", "Rick", "Morty", "Beth", "Jerry", "Summer"
    ));

    public Flux<Integer> tenToZero() {
        return Flux.range(0, 11)
                .map(i -> 10 - i);
    }

    public Flux<Integer> operateOnTenToZero(Function<Integer, Integer> operation) {
        return tenToZero().map(operation);
    }

    public Flux<String> namesPerSecond() {
        List<String> randomizedNames = new ArrayList<>(NAMES);
        Collections.reverse(randomizedNames);

        return Flux.fromIterable(randomizedNames)
                .delayElements(Duration.ofSeconds(1));
    }

}
