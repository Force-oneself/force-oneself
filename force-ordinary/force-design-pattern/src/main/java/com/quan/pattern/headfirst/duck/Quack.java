package com.quan.pattern.headfirst.duck;

/**
 * @author Force-oneself
 * @Description Quack
 * @date 2021-09-09
 */
public class Quack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("quack");
    }
}
