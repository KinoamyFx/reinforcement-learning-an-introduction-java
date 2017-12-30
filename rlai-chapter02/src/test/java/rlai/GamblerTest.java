package rlai;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class GamblerTest extends Application {
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
        bandits.add(new GaussianBandit(-25));

        int iterateTimes = 3000;
        Rlai2DChart chart = new Rlai2DChart("Epsilon-Greedy", "Iteration", "Average Reward");

        DoubleStream.of(0.01, 0.05, 0.1, 0.4, 0.6, 0.8).forEach(d -> {
            BanditStrategy strategy = new EpsilonGreedyStrategy(d, bandits.size());
            Gambler gambler = new Gambler(bandits, strategy);
            double[][] data = new double[2][iterateTimes];
            data[0] = IntStream.range(0, iterateTimes).asDoubleStream().toArray();
            data[1] = IntStream.range(0, iterateTimes).mapToDouble(i -> gambler.play()).toArray();
            chart.addLine("ε=" + String.valueOf(d), data);
        });

        primaryStage.setScene(chart.toScene());
        primaryStage.setTitle("Epsilon-Greedy");
        primaryStage.setWidth(1366);
        primaryStage.setHeight(768);
        primaryStage.show();
    }
}