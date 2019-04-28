package com.rommelrico.designpatterns.interpreter.models;

public class InterpreterContext {

    public String getBinaryFormat(int i) {
        return Integer.toBinaryString(i);
    }

}
