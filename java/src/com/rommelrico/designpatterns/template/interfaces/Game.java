package com.rommelrico.designpatterns.template.interfaces;

public abstract class Game {
    public abstract void initialize();
    public abstract void startPlay();
    public abstract void endPlay();

    // Hooked-on Template method
    public abstract void addNewCharacterToGame();

    // Template method
    public final void play() {
        loadAssets();
        initialize();
        startPlay();
        if (addNewGameCharacter()) {
            addNewCharacterToGame();
        }
        endPlay();
    }

    private void loadAssets() {
        System.out.println("Loading Game Assets...");
    }

    private boolean addNewGameCharacter() {
        return true;
    }

}
