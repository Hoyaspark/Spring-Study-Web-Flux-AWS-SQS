package com.study.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

import java.util.ArrayList;
import java.util.List;

public class MonoTest {

    @Test
    void test_mono_just() {
        List<Signal<Integer>> signals = new ArrayList<>(4);

        final Integer[] result = new Integer[1];

        Mono<Integer> mono = Mono.just(1).log()
                .doOnEach(integerSignal -> {
                    signals.add(integerSignal);
                    System.out.println("Signal... : " + integerSignal);
                });

        mono.subscribe(integer -> result[0] = integer);

    }

    @Test
    void test_mono_just_multiple() {

    }
}
