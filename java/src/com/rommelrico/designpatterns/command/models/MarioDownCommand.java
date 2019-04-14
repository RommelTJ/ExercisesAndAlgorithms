package com.rommelrico.designpatterns.command.models;

import com.rommelrico.designpatterns.command.interfaces.*;

public class MarioDownCommand implements Command {

    private MarioCharacterReceiver marioCharacter;

    public MarioDownCommand(MarioCharacterReceiver marioCharacter) {
        this.marioCharacter = marioCharacter;
    }

    @Override
    public void execute() {
        marioCharacter.moveDown();
    }

}
