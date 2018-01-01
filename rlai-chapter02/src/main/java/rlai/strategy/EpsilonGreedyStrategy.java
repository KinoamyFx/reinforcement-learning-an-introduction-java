package rlai;

import java.util.Random;
import java.util.stream.IntStream;

import rlai.reward.RewardUpdater;

public class EpsilonGreedyStrategy implements BanditStrategy {
    private double ε;
    private double[] averageReward;
    private int[] counter;
    private Random random;

    private RewardUpdater rewardUpdater;

    public EpsilonGreedyStrategy(double ε, final int k, RewardUpdater updater) {
        this.ε = ε;
        this.averageReward = new double[k];
        this.counter = new int[k];
        this.rewardUpdater = updater;
        random = new Random();
    }

    @Override
    public int nextBandit() {

        if (random.nextFloat() < ε) {
            return random.nextInt(averageReward.length);
        }

        return IntStream.range(0, averageReward.length)
            .reduce((l, r) -> averageReward[l] > averageReward[r] ? l : r).getAsInt();
    }

    @Override
    public void updateReward(int order, double reward) {
        averageReward[order] = rewardUpdater.nextAverageReward(counter[order], averageReward[order], reward);
        counter[order]++;
    }
}