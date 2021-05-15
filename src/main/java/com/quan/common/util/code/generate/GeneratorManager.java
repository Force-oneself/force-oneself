package com.quan.common.util.code.generate;

/**
 * @Description: Freemarker 演示demo
 * @Author heyq
 * @Date 2021-01-01
 **/
public class GeneratorManager {

    private Generator generator;

    public GeneratorManager(Generator generator) {
        this.generator = generator;
    }

    public void generate() {
        generator.generate();
    }
}
