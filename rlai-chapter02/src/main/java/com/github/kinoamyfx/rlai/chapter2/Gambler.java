package com.github.kinoamyfx.rlai.chapter2;

import java.util.List;

import com.github.kinoamyfx.rlai.chapter2.strategy.BanditStrategy;
import lombok.NonNull;

/**
 * 赌徒一个
 */
public class Gambler {
    private List<Bandit> bandits;
    private BanditStrategy strategy;
    private double averageReward;
    private int selectTimes = 0;

    public Gambler(@NonNull List<Bandit> bandits, @NonNull BanditStrategy strategy) {
        this.bandits = bandits;
        this.strategy = strategy;
    }

    double play() {
        int nextBandit = strategy.nextBandit();
        double reward = bandits.get(nextBandit).select();
        strategy.updateReward(nextBandit, reward);

        averageReward = (double)selectTimes / (selectTimes + 1) * averageReward + reward / (selectTimes + 1);
        selectTimes++;
        return averageReward;
    }
}
