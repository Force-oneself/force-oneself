package com.quan.pattern.headfirst.duck;

/**
 * @author Force-oneself
 * @Description FlyNoWay
 * @date 2021-09-09
 */
public class FlyNoWay implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I can't fly");
    }
}
