package com.quan.pattern.headfirst.duck;

/**
 * @author Force-oneself
 * @Description MiniDuck
 * @date 2021-09-09
 */
public class MiniDuck extends Duck{

    protected MiniDuck() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new Squeak();
    }

    @Override
    public void display() {
        System.out.println("I am a mini duck");
    }
}
