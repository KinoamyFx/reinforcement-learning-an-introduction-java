package rlai;

import java.util.Random;

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
