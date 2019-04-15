package com.rommelrico.designpatterns.template.models;

import com.rommelrico.designpatterns.template.interfaces.*;

public class FootballGame extends Game {

    @Override
    public void initialize() {
        System.out.println("Football Game Initializing...");
    }

    @Override
    public void startPlay() {
        System.out.println("Football Game Starting...");
    }

    @Override
    public void endPlay() {
        System.out.println("Football Game Ending...");
    }
    
}
