package com.rommelrico.designpatterns.template.models;

import com.rommelrico.designpatterns.template.interfaces.*;

import java.io.*;

public class EndlessRunnerGame extends Game {

    @Override
    public void initialize() {
        System.out.println("Endless Runner Initializing...");
    }

    @Override
    public void startPlay() {
        System.out.println("Endless Runner Starting...");
        if (playerWantsNewCharacter()) {
            addNewCharacterToGame();
        }
    }

    @Override
    public void endPlay() {
        System.out.println("Endless Runner Ending...");
    }

    @Override
    public void addNewCharacterToGame() {
        System.out.println("Adding a new character to the game...");
    }

    private boolean playerWantsNewCharacter() {
        String answer = getUserInput();
        return answer.toLowerCase().equals("y");
    }

    private String getUserInput() {
        String answer = null;
        System.out.println("Would you like to add a new character to the game? (y/n)");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            answer = in.readLine();
        } catch (IOException ioe) {
            System.out.println("IO Error: " + ioe.getMessage());
        }

        if (answer == null) {
            return "n";
        }
        return answer;
    }

}
