package com.rommelrico.designpatterns.template;

import com.rommelrico.designpatterns.template.interfaces.*;
import com.rommelrico.designpatterns.template.models.*;

public class Main {

    public static void main(String[] args) {
        Game game = new EndlessRunnerGame();
        game.play();
        Game game2 = new FootballGame();
        game2.play();
    }

}
