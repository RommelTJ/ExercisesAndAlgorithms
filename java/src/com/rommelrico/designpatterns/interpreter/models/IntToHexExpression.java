package com.rommelrico.designpatterns.interpreter.models;

import com.rommelrico.designpatterns.interpreter.interfaces.*;

public class IntToHexExpression implements Expression {

    private int i;

    public IntToHexExpression(int i) {
        this.i = i;
    }

    @Override
    public String interpreter(InterpreterContext ctx) {
        return ctx.getHexFormat(i);
    }

}
