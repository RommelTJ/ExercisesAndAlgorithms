package com.rommelrico.designpatterns.interpreter.models;

import com.rommelrico.designpatterns.interpreter.interfaces.*;

public class IntToBinaryExpression implements Expression {

    private int i;

    public IntToBinaryExpression(int i) {
        this.i = i;
    }

    @Override
    public String interpreter(InterpreterContext ctx) {
        return ctx.getBinaryFormat(i);
    }

}
