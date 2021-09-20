package com.quan.pattern.headfirst.duck;

/**
 * @author Force-oneself
 * @Description FlyRocketPowered
 * @date 2021-09-09
 */
public class FlyRocketPowered implements FlyBehavior{

    @Override
    public void fly() {
        System.out.println("I'm flying with a rocket");
    }
}
