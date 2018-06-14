package com.github.kinoamyfx.rlai.chapter2.updater;

public interface RewardUpdater {
    double nextAverageReward(int n, double lastReward, double currentReward);
}
