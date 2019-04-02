package com.rommelrico.designpatterns.strategy.strategyone.model;

import com.rommelrico.designpatterns.strategy.strategyone.controllers.*;

public class Clown extends ScoreAlgorithmBase {

    @Override
    public int calculateScore(int taps, int multiplier) {
        return (taps * multiplier) - 10;
    }

}
