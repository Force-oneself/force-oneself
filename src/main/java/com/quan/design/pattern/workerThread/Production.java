package com.quan.design.pattern.workerThread;

public class Production extends InstructionBook {

    // 产品编号
    private final int proID;

    public Production(int proID) {
        this.proID = proID;
    }

    @Override
    protected void firstProcess() {
        System.out.println("execute the " + proID + " first process");
    }

    @Override
    protected void secondProcess() {
        System.out.println("execute the " + proID + " second process");
    }
}
