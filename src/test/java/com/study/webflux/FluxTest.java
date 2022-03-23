package com.study.webflux;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FluxTest {

    @Test
    void test_flux_just_consumer() {
        List<String> names = new ArrayList<>();

        Flux<String> flux = Flux.just("에디킴", "아이폰").log();

        System.out.println("여기가 제읾 먼저 시작되겠군..");

        flux.subscribe((item) -> {
            System.out.println("item 수신 : " + item);
            names.add(item);
        });

        assertThat(names.size()).isEqualTo(2);
        assertThat(names).isEqualTo(Arrays.asList("에디킴", "아이폰"));
    }


    @Test
    void test_flux_multiple_time() {
        List<Integer> test = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Flux<List<Integer>> test2 = (Flux<List<Integer>>) Flux.just(test)
                .log();



        System.out.println("hello");
        System.out.println("dfdf");


        test2.subscribe(list -> {
            list.forEach(item -> {
                System.out.println("item = " + item);
            });
        });

    }
}
