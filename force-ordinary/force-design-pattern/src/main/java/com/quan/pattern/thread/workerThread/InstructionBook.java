package com.quan.pattern.thread.workerThread;

/**
 * 在流水线上需要被加工的产品，create作为一个模板方法，提供了加工产品的说明书
 */
public abstract class InstructionBook {

    public final void create() {
        this.firstProcess();
        this.secondProcess();
    }

    protected abstract void firstProcess();

    protected abstract void secondProcess();
}
