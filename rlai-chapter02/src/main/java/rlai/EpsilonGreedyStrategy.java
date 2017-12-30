package rlai;

import java.util.Random;
import java.util.stream.IntStream;

public class EpsilonGreedyStrategy implements BanditStrategy {
    private double ε;
    private double[] rewards;
    private int[] counter;
    private Random random;

    public EpsilonGreedyStrategy(double ε, final int k) {
        this.ε = ε;
        this.rewards = new double[k];
        this.counter = new int[k];
        random = new Random();
    }

    @Override
    public int nextBandit() {

        if (random.nextFloat() < ε) {
            return random.nextInt(rewards.length);
        }

        return IntStream.range(0, rewards.length)
            .reduce((l, r) -> rewards[l] > rewards[r] ? l : r).getAsInt();
    }

    @Override
    public void updateReward(int order, double reward) {
        double c = counter[order];
        double c1 = c + 1;
        double lastReward = rewards[order];
        rewards[order] = c / c1 * lastReward + reward / c1;

        counter[order]++;
    }
}