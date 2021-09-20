package com.quan.pattern.headfirst.duck;

/**
 * @author Force-oneself
 * @Description FlyWithWings
 * @date 2021-09-09
 */
public class FlyWithWings implements FlyBehavior{

    @Override
    public void fly() {
        System.out.println("I can fly");
    }
}
