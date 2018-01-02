package rlai.updater;

public interface RewardUpdater {
    double nextAverageReward(int n, double lastReward, double currentReward);
}
