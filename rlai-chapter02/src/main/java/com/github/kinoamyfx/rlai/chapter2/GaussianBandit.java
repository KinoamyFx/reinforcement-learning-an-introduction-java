package com.github.kinoamyfx.rlai.chapter2;

import java.util.Random;

/**
 * 内部生成规则为高斯分布的摇臂机器人
 */
public class GaussianBandit implements Bandit {

    private Random random;
    private double a;
    private double b;
    private double μ;

    public GaussianBandit(double μ) {
        this.random = new Random();
        this.a = μ - 1;
        this.b = μ + 1;
        this.μ = μ;
    }

    @Override
    public double select() {
        return random.nextDouble() + μ;
    }
}
