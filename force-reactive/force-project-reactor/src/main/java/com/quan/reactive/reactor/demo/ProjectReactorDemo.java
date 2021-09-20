package com.quan.reactive.reactor.demo;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

/**
 * @author Force-oneself
 * @Description ProjectReactor
 * @date 2021-08-29
 */
public class ProjectReactorDemo {

    public static void main(String[] args) {

        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");

        List<String> iterable = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(iterable);

        Flux<Object> flux = new Flux<Object>() {
            @Override
            public void subscribe(CoreSubscriber<? super Object> actual) {

            }
        };
    }
}
