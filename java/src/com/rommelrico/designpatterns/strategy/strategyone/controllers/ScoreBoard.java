package com.rommelrico.designpatterns.strategy.strategyone.controllers;

public class ScoreBoard {

    private ScoreAlgorithmBase algorithmBase;

    public void showScore(int taps, int multiplier) {
        System.out.println(algorithmBase.calculateScore(taps, multiplier));
    }

}
