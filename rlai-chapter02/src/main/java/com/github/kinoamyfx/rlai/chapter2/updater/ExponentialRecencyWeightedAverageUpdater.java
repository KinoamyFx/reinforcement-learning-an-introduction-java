package com.github.kinoamyfx.rlai.chapter2.updater;

public class ExponentialRecencyWeightedAverageUpdater implements RewardUpdater {
    private double α;

    public ExponentialRecencyWeightedAverageUpdater(double α) {
        this.α = α;
    }

    @Override
    public double nextAverageReward(int n, double lastReward, double currentReward) {
        return lastReward + α * currentReward;
    }
}
