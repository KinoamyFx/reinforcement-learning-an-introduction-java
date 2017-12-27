package rlai;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GamblerTest {
    @Test
    public void testGambler() {
        List<Bandit> bandits = new ArrayList<>(10);
        bandits.add(new GaussianBandit(1, 3));

        Gambler gambler = new Gambler(null, null);
    }
}