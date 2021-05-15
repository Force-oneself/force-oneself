package com.quan.common.design.pattern.thread.singleExecution.noodle;

public class EatNoodleThread extends Thread {

    private final String name;

    private final Tableware leftTool;

    private final Tableware rightTool;

    private final TablewarePair tablewarePair;

    public EatNoodleThread(String name, Tableware leftTool, Tableware rightTool) {
        this.name = name;
        this.leftTool = leftTool;
        this.rightTool = rightTool;
        this.tablewarePair = null;
    }

    public EatNoodleThread(String name, TablewarePair tablewarePair) {
        this.name = name;
        this.tablewarePair = tablewarePair;
        this.leftTool = null;
        this.rightTool = null;
    }

    @Override
    public void run() {
        while (true) {
            this.eat();
        }
    }

    // 吃面过程
    private void eat() {
        synchronized (leftTool) {
            System.out.println(name + " take up " + leftTool + "(left)");
            synchronized (rightTool) {
                System.out.println(name + " take up " + rightTool + "(right)");
                System.out.println(name + " is eating now.");
                System.out.println(name + " put down " + rightTool + "(right)");
            }
            System.out.println(name + " put down " + rightTool + "(right)");
        }
    }

    private void useTablewarePair() {
        synchronized (tablewarePair) {
            System.out.println(name + " take up " + tablewarePair.getLeftTool() + "(left)");
            System.out.println(name + " take up " + tablewarePair.getRightTool() + "(right)");
            System.out.println(name + " is eating now.");
            System.out.println(name + " put down " + tablewarePair.getRightTool() + "(right)");
            System.out.println(name + " take up " + tablewarePair.getLeftTool() + "(left)");
        }
    }

}
