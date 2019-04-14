package com.rommelrico.designpatterns.command.models;

import com.rommelrico.designpatterns.command.interfaces.*;

public class KirbyLeftCommand implements Command {

    private KirbyCharacterReceiver kirbyCharacter;

    public KirbyLeftCommand(KirbyCharacterReceiver kirbyCharacter) {
        this.kirbyCharacter = kirbyCharacter;
    }

    @Override
    public void execute() {
        kirbyCharacter.moveLeft();
    }

}
