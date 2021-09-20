package com.quan.pattern.headfirst.duck;

/**
 * @author Force-oneself
 * @Description DuckDemo
 * @date 2021-09-09
 */
public class DuckDemo {

    public static void main(String[] args) {
        Duck duck = new MiniDuck();
        duck.performFly();
        duck.performQuack();

        duck.setFlyBehavior(new FlyNoWay());
        duck.performFly();
    }
}
