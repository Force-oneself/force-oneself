package com.quan.reactive.reactor.demo;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * ProjectReactor
 *
 * @author Force-oneself
 * @date 2021-08-29
 */
public class ProjectReactorDemo {

    public static void main(String[] args) throws InterruptedException {

        // 静态创建
        // staticFlux();

        // 动态创建
        // dynamicFlux();

        Mono.just("Mono")
                .concatWith(Mono.error(new IllegalStateException()))
                .onErrorReturn("return error")
                .subscribe(System.out::println, System.err::println);

        Flux.range(1, 25)
                .buffer(10)
                .subscribe(System.out::println);

        Flux.range(1, 25)
                .window(2)
                .toIterable()
                .forEach(w -> {
                    w.subscribe(System.out::println);
                    System.out.println("---------");
                });

        Flux.just(1, 2)
                .map(i -> "map:" + i)
                .subscribe(System.out::println);

        Flux.just(2, 5)
                .flatMap(x -> Mono.just(x * x))
                .subscribe(System.out::println);


        Flux.range(0, 5)
                .filter(x -> x % 2 == 0)
                .subscribe(System.out::println);

    }

    private static void dynamicFlux() {
        Flux.generate(sink -> {
                    sink.next("generate");
                    sink.complete();
                })
                .subscribe(System.out::println);
        Flux.generate(() -> 1, (i, sink) -> {
                    sink.next("generate:" + i);
                    if (i == 5) {
                        sink.complete();
                    }
                    return ++i;
                })
                .subscribe(System.out::println);

        Flux.create(sink -> {
                    for (int i = 0; i < 5; i++) {
                        sink.next("create:" + i);
                    }
                    sink.complete();
                })
                .subscribe(System.out::println);
    }

    private static void staticFlux() throws InterruptedException {
        Flux.just("foo", "bar", "foobar")
                .subscribe(System.out::println);

        Flux.fromIterable(Arrays.asList("foo", "bar", "foobar"))
                .subscribe(System.out::println);

        Flux.range(1, 20)
                .subscribe(System.out::println);

        Flux.interval(Duration.ofSeconds(2), Duration.ofMillis(200))
                .subscribe(System.out::println);
        Thread.currentThread()
                .join();
    }
}
