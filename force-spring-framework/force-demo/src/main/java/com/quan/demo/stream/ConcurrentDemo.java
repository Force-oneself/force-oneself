package com.quan.demo.stream;

import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

/**
 * @author Force-oneself
 * @description ConcurrentDemo
 * @date 2021-12-14
 */
public class ConcurrentDemo {

    private final static Logger log = LoggerFactory.getLogger(ConcurrentDemo.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        allAndAnyOf();
    }

    private static void allAndAnyOf() throws InterruptedException, ExecutionException {
        Random rand = new Random();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("future1 done...");
            }
            return "abc";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("future2 done...");
            }
            return "efg";
        });

        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(future1, future2);
        completableFuture.join();
        assertTrue(completableFuture.isDone());
        System.out.println("all futures done...");

        CompletableFuture<Object> f = CompletableFuture.anyOf(future1, future2);
        System.out.println(f.get());
        System.out.println("any futures done...");
    }

    private static void thenCombine() throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture = CompletableFuture
                .supplyAsync(() -> "hello!")
                .thenCombine(CompletableFuture.supplyAsync(() -> "world!"), (s1, s2) -> s1 + s2)
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "nice!"));
        assertEquals("hello!world!nice!", completableFuture.get());
    }

    private static void thenCompose() throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> "hello!")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "world!"));
        assertEquals("hello!world!", future.get());
    }

    private static void exceptionally() throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> {
                    if (true) {
                        throw new RuntimeException("Computation error!");
                    }
                    return "hello!";
                })
                .exceptionally(ex -> {
                    // CompletionException
                    System.out.println(ex.toString());
                    return "exception";
                });
        assertEquals("exception", future.get());
    }

    private static void handle() throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                    throw new RuntimeException("Computation error!");
                })
                .handle((res, ex) -> {
                    // res 代表返回的结果
                    // ex 的类型为 Throwable ，代表抛出的异常
                    return ex != null ? "exception" : "world!";
                });
        assertEquals("exception", future.get());
    }

    private static void whenComplete() throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello!")
                .whenComplete((res, ex) -> {
                    // res 代表返回的结果
                    // ex 的类型为 Throwable ，代表抛出的异常
                    System.out.println(res);
                    // 这里没有抛出异常所有为 null
                    assertNull(ex);
                });
        assertEquals("hello!", future.get());
    }

    private static void thenAcceptAndRun() {
        //hello!world!nice!
        CompletableFuture.completedFuture("hello!")
                .thenApply(s -> s + "world!")
                .thenApply(s -> s + "nice!")
                .thenAccept(System.out::println);

        //hello!
        CompletableFuture.completedFuture("hello!")
                .thenApply(s -> s + "world!")
                .thenApply(s -> s + "nice!")
                .thenRun(() -> System.out.println("hello!"));
    }

    private static void thenApply() throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.completedFuture("hello!")
                .thenApply(s -> s + "world!");
        assertEquals("hello!world!", future.get());
        //这次调用将被忽略。
        future.thenApply(s -> s + "nice!");
        assertEquals("hello!world!", future.get());

        // 流式
        CompletableFuture<String> future1 = CompletableFuture.completedFuture("hello!")
                .thenApply(s -> s + "world!")
                .thenApply(s -> s + "nice!");
        assertEquals("hello!world!nice!", future1.get());
    }

    private static void runAsync() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> System.out.println("hello!"));
        // 输出 "hello!"
        future.get();
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "hello!");
        assertEquals("hello!", future2.get());
    }

    private static void newInstance() throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.completedFuture("hello!");
        assertEquals("hello!", future.get());
    }

    private static void assertEquals(String s, String s1) {
        System.out.println(Objects.equals(s, s1));
    }

    private static void assertTrue(boolean done) {
        System.out.println(done);
    }

    private static void assertNull(Throwable ex) {
        System.out.println(ex == null);
    }
}
