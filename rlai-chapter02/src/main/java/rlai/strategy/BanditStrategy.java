package rlai.strategy;

public interface BanditStrategy {

    int nextBandit();

    void updateReward(int order, double reward);
}
