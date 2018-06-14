package com.github.kinoamyfx.rlai.chapter2.strategy;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * @author KinoamyFx
 * @date 2018/03/03
 */
public class GradientStrategy implements BanditStrategy {

    /**
     * Pr{At=a} = e^(Hta)/(e^(Ht1)+......+e^(Htn))
     */
    private double[] preference;

    private double averageReward;

    private double alpha;

    private double preferenceSum;

    /**
     * 迭代次数
     */
    private double n;

    public GradientStrategy(int bandits, double alpha) {
        this.preference = new double[bandits];

        if (alpha <= 0 && alpha > 1) {
            throw new RuntimeException("alpha is not correct");
        }

        this.alpha = alpha;
    }

    @Override
    public int nextBandit() {
        preferenceSum = DoubleStream.of(preference)
            .map(Math::exp)
            .reduce(0D, (l, r) -> l + r);

        return IntStream.range(0, preference.length)
            .reduce((l, r) -> Math.exp(preference[l]) / preferenceSum > Math.exp(preference[r]) / preferenceSum ? l : r)
            .getAsInt();
    }

    @Override
    public void updateReward(int order, double reward) {
        preference[order] = preference[order] +
            alpha * (reward - averageReward) * (1 - Math.exp(preference[order]) / preferenceSum);

        IntStream.range(0, preference.length)
            .filter(i -> i != order)
            .forEach(i -> preference[i] = preference[i] - alpha * (reward - averageReward) * Math.exp(preference[i])
                / preferenceSum);

        averageReward = averageReward * n / (n + 1) + reward / (n + 1);
        n++;
    }
}
