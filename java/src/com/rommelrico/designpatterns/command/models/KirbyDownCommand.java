package com.rommelrico.designpatterns.command.models;

import com.rommelrico.designpatterns.command.interfaces.*;

public class KirbyDownCommand implements Command {

    private KirbyCharacterReceiver kirbyCharacter;

    public KirbyDownCommand(KirbyCharacterReceiver kirbyCharacter) {
        this.kirbyCharacter = kirbyCharacter;
    }

    @Override
    public void execute() {
        kirbyCharacter.moveDown();
    }

}
