package com.rommelrico.designpatterns.command.models;

import com.rommelrico.designpatterns.command.interfaces.*;

public class MarioRightCommand implements Command {

    private MarioCharacterReceiver marioCharacter;

    public MarioRightCommand(MarioCharacterReceiver marioCharacter) {
        this.marioCharacter = marioCharacter;
    }

    @Override
    public void execute() {
        marioCharacter.moveRight();
    }

}
