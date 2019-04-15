package com.rommelrico.designpatterns.template.models;

import com.rommelrico.designpatterns.template.interfaces.*;

public class EndlessRunnerGame extends Game {

    @Override
    public void initialize() {
        System.out.println("Endless Runner Initializing...");
    }

    @Override
    public void startPlay() {
        System.out.println("Endless Runner Starting...");
    }

    @Override
    public void endPlay() {
        System.out.println("Endless Runner Ending...");
    }

}
