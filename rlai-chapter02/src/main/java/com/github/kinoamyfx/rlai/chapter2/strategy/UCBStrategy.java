package com.github.kinoamyfx.rlai.chapter2.strategy;

import java.util.stream.IntStream;

import com.github.kinoamyfx.rlai.chapter2.updater.RewardUpdater;

import static java.lang.Math.log;
import static java.lang.Math.sqrt;

public class UCBStrategy implements BanditStrategy {
    private double[] averageReward;
    private int[] counter;
    private int t;
    private RewardUpdater updater;

    public UCBStrategy(int bandits, RewardUpdater updater) {
        this.averageReward = new double[bandits];
        this.counter = new int[bandits];
        this.updater = updater;
    }

    @Override
    public int nextBandit() {
        if (t < counter.length) {
            return t;
        }
        double[] at = new double[averageReward.length];

        /*
        计算reward
         */
        IntStream.range(0, averageReward.length).parallel().forEach(i ->
            at[i] = averageReward[i] + sqrt(log(t) / counter[i])
        );

        /*
        找到最大的reward
         */
        return IntStream.range(0, averageReward.length).reduce((l, r) -> at[l] > at[r] ? l : r).getAsInt();
    }

    @Override
    public void updateReward(int order, double reward) {
        t++;
        averageReward[order] = updater.nextAverageReward(counter[order]++, averageReward[order], reward);
    }
}
