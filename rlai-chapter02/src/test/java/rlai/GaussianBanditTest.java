package rlai;

import org.junit.Test;

/**
 * @author KinoamyFx
 * @date 2018/03/03
 */
public class GaussianBanditTest {

    @Test
    public void select() {
        new GaussianBandit(0.2).select();
    }
}