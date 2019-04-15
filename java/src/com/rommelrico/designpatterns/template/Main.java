package com.rommelrico.designpatterns.template;

import com.rommelrico.designpatterns.template.interfaces.*;
import com.rommelrico.designpatterns.template.models.*;

public class Main {

    public static void main(String[] args) {
        Game game = new FootballGame();
        game.play();
        System.out.println("----");
        Game game2 = new EndlessRunnerGame();
        game2.play();
    }

}
