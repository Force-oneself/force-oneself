package com.quan.demo.concurrency;

/**
 * PairBuffer
 *
 * @author Force-oneself
 * @date 2022-09-14
 */
public class DoubleBuffer<T> implements SegmentBuffer<T, SingleBuffer<T>> {

    private final SingleBuffer<T> from;
    private final SingleBuffer<T> to;
    private SingleBuffer<T> current;

    public DoubleBuffer(int threshold) {
        this.from = new SingleBuffer<>(threshold);
        this.to = new SingleBuffer<>(threshold);
        this.current = this.from;
    }

    @Override
    public void exchange() {
        this.current = this.current == this.to ? this.from : this.to;
    }

    @Override
    public SingleBuffer<T> choose() {
        return this.current;
    }

    @Override
    public boolean isFull() {
        return this.current.isFull();
    }

    @Override
    public void flush() {
        this.current.flush();
    }

}
