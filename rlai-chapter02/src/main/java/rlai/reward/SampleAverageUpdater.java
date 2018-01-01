package rlai.reward;

public class SampleAverageUpdater implements RewardUpdater {
    @Override
    public double nextAverageReward(int n, double lastReward, double currentReward) {
        double n1 = n + 1;
        return n / n1 * lastReward + currentReward / n1;
    }
}
