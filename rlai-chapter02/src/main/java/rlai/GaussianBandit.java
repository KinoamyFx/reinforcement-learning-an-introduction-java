package rlai;

import java.util.Random;

public class GaussianBandit implements Bandit {

    private Random random;
    private int a;
    private int b;

    public GaussianBandit(int a, int b) {
        this.random = new Random();
        this.a = a;
        this.b = b;
    }

    @Override
    public float select() {
        return (float)(Math.sqrt(b) * random.nextGaussian() + a);
    }
}
