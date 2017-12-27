package rlai;

import java.util.List;

import lombok.NonNull;

public class Gambler {
    private List<Bandit> bandits;
    private BanditStrategy strategy;

    public Gambler(@NonNull List<Bandit> bandits, @NonNull BanditStrategy strategy) {
        this.bandits = bandits;
        this.strategy = strategy;
    }
}
