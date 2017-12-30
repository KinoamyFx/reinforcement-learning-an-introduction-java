package rlai;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

public class GamblerTest {
    @Test
    public void testGambler() {
        List<Bandit> bandits = new ArrayList<>(10);
        bandits.add(new GaussianBandit(20));
        bandits.add(new GaussianBandit(15));
        bandits.add(new GaussianBandit(10));
        bandits.add(new GaussianBandit(5));
        bandits.add(new GaussianBandit(0));
        bandits.add(new GaussianBandit(-5));
        bandits.add(new GaussianBandit(-10));
        bandits.add(new GaussianBandit(-15));
        bandits.add(new GaussianBandit(-20));
        bandits.add(new GaussianBandit(-25));

        BanditStrategy strategy = new EpsilonGreedyStrategy(0.01, bandits.size());
        Gambler gambler = new Gambler(bandits, strategy);

        double[] xRange = IntStream.range(0, 10000).asDoubleStream().toArray();

        IntStream.range(0, 10000).forEach(i -> {
            System.out.println(gambler.play());
        });
    }

}