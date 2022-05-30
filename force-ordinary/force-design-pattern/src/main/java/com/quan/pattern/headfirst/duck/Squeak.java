package com.quan.pattern.headfirst.duck;

/**
 * @author Force-oneself
 * @Description Squeak
 * @date 2021-09-09
 */
public class Squeak implements QuackBehavior{

    @Override
    public void quack() {
        System.out.println("Squeak");
    }
}
