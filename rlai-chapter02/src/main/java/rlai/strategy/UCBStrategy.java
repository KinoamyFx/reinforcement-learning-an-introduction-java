package rlai.strategy;

import java.util.stream.IntStream;

import rlai.updater.RewardUpdater;

import static java.lang.Math.log;
import static java.lang.Math.sqrt;

public class UCBStrategy implements BanditStrategy {
    private double[] averageReward;
    private int[] counter;
    private int t;
    private RewardUpdater updater;

    public UCBStrategy(int k, RewardUpdater updater) {
        this.averageReward = new double[k];
        this.counter = new int[k];
        this.updater = updater;
    }

    @Override
    public int nextBandit() {
        if (t < counter.length) {
            return t;
        }
        double[] at = new double[averageReward.length];
        IntStream.range(0, averageReward.length).parallel().forEach(i ->
            at[i] = averageReward[i] + sqrt(log(t) / counter[i])
        );
        return IntStream.range(0, averageReward.length).reduce((l, r) -> at[l] > at[r] ? l : r).getAsInt();
    }

    @Override
    public void updateReward(int order, double reward) {
        t++;
        averageReward[order] = updater.nextAverageReward(counter[order]++, averageReward[order], reward);
    }
}
