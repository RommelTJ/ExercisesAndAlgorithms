package com.rommelrico.designpatterns.command.models;

import com.rommelrico.designpatterns.command.interfaces.*;

public class KirbyRightCommand implements Command {

    private KirbyCharacterReceiver kirbyCharacter;

    public KirbyRightCommand(KirbyCharacterReceiver kirbyCharacter) {
        this.kirbyCharacter = kirbyCharacter;
    }

    @Override
    public void execute() {
        kirbyCharacter.moveRight();
    }

}
