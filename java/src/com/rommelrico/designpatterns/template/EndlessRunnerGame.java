package com.rommelrico.designpatterns.template;

public class EndlessRunnerGame extends Game {

    @Override
    void initialize() {
        System.out.println("Endless Runner Initializing...");
    }

    @Override
    void startPlay() {
        System.out.println("Endless Runner Starting...");
    }

    @Override
    void endPlay() {
        System.out.println("Endless Runner Ending...");
    }

}
