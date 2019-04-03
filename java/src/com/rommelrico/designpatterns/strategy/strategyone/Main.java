package com.rommelrico.designpatterns.strategy.strategyone;

import com.rommelrico.designpatterns.strategy.strategyone.controllers.*;
import com.rommelrico.designpatterns.strategy.strategyone.model.*;

public class Main {

    public static void main(String[] args) {
        ScoreBoard board = new ScoreBoard();

        System.out.println("Balloon Tap Score:");
        board.algorithmBase = new Balloon();
        board.showScore(10, 5);

        System.out.println("Clown Tap Score:");
        board.algorithmBase = new Clown();
        board.showScore(10, 5);

        System.out.println("SquareBalloon Tap Score:");
        board.algorithmBase = new SquareBalloon();
        board.showScore(10, 5);
    }

}
