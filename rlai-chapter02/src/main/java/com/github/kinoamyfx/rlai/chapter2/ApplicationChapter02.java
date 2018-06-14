package com.github.kinoamyfx.rlai.chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import com.github.kinoamyfx.rlai.c3.c2.core.RLAIChart2D;
import com.github.kinoamyfx.rlai.chapter2.strategy.BanditStrategy;
import com.github.kinoamyfx.rlai.chapter2.strategy.EpsilonGreedyStrategy;
import com.github.kinoamyfx.rlai.chapter2.strategy.GradientStrategy;
import com.github.kinoamyfx.rlai.chapter2.strategy.UCBStrategy;
import com.github.kinoamyfx.rlai.chapter2.updater.ExponentialRecencyWeightedAverageUpdater;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author KinoamyFx
 * @date 2018/03/03
 */
public class ApplicationChapter02 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        List<Bandit> bandits = new ArrayList<>();
        bandits.add(new GaussianBandit(20));
        bandits.add(new GaussianBandit(15));
        bandits.add(new GaussianBandit(10));
        bandits.add(new GaussianBandit(5));
        bandits.add(new GaussianBandit(0));
        bandits.add(new GaussianBandit(-5));
        bandits.add(new GaussianBandit(-10));
        bandits.add(new GaussianBandit(-15));
        bandits.add(new GaussianBandit(-20));

        int iterateTimes = 400;
        RLAIChart2D chart = new RLAIChart2D("Bandits", "Iteration", "Average Reward");

        epsilonGreedy(chart, iterateTimes, bandits);

        ucb(chart, iterateTimes, bandits);

        gradient(chart, iterateTimes, bandits);

        primaryStage.setScene(chart.toScene());
        primaryStage.setTitle("Bandits");
        primaryStage.setWidth(1366);
        primaryStage.setHeight(768);
        primaryStage.show();
    }

    private void epsilonGreedy(RLAIChart2D chart, int iterateTimes, List<Bandit> bandits) {
        DoubleStream.of(0.01, 0.03, 0.06, 0.07, 0.08, 0.1).forEach(d -> {
            BanditStrategy strategy = new EpsilonGreedyStrategy(d, bandits.size(),
                new ExponentialRecencyWeightedAverageUpdater(0.6));
            Gambler gambler = new Gambler(bandits, strategy);
            double[][] data = new double[2][iterateTimes];
            data[0] = IntStream.range(0, iterateTimes).asDoubleStream().toArray();
            data[1] = IntStream.range(0, iterateTimes).mapToDouble(i -> gambler.play()).toArray();
            chart.addLine("ε=" + String.valueOf(d), data);
        });
    }

    private void ucb(RLAIChart2D chart, int iterateTimes, List<Bandit> bandits) {
        BanditStrategy strategy = new UCBStrategy(bandits.size(), new ExponentialRecencyWeightedAverageUpdater(0.6));
        Gambler gambler = new Gambler(bandits, strategy);
        double[][] data = new double[2][iterateTimes];
        data[0] = IntStream.range(0, iterateTimes).asDoubleStream().toArray();
        data[1] = IntStream.range(0, iterateTimes).mapToDouble(i -> gambler.play()).toArray();
        chart.addLine("UCB", data);
    }

    private void gradient(RLAIChart2D chart, int iterateTimes, List<Bandit> bandits) {
        BanditStrategy strategy = new GradientStrategy(bandits.size(), 0.2);
        Gambler gambler = new Gambler(bandits, strategy);
        double[][] data = new double[2][iterateTimes];
        data[0] = IntStream.range(0, iterateTimes).asDoubleStream().toArray();
        data[1] = IntStream.range(0, iterateTimes).mapToDouble(i -> gambler.play()).toArray();
        chart.addLine("gradient", data);
    }
}
