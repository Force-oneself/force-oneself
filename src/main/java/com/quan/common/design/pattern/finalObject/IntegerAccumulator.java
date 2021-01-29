package com.quan.common.design.pattern.finalObject;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 不可变对象不予许被继承
 */
public final class IntegerAccumulator {

    private final int init;

    public IntegerAccumulator(int init) {
        this.init = init;
    }

    public IntegerAccumulator(IntegerAccumulator accumulator, int init) {
        this.init = accumulator.getInit() + init;
    }

    public IntegerAccumulator add(int i) {
        return new IntegerAccumulator(this, i);
    }

    public int getInit() {
        return init;
    }

    private static void slowly() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IntegerAccumulator accumulator = new IntegerAccumulator(0);
        IntStream.range(0, 3).forEach(i -> new Thread(() -> {
            int inc = 0;
            while (true) {
                int oldValue = accumulator.getInit();
                int result = accumulator.add(inc).getInit();
                System.out.println(oldValue + " + " + inc + " = " + result);
                if (inc + oldValue != result) {
                    System.err.println("Error:" + oldValue + " + " + inc + " = " + result);
                }
                inc++;
                slowly();
            }
        }).start());
    }

}
