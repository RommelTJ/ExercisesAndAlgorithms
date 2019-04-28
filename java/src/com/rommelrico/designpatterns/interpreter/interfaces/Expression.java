package com.rommelrico.designpatterns.interpreter.interfaces;

import com.rommelrico.designpatterns.interpreter.models.*;

public interface Expression {
    String interpreter(InterpreterContext ctx);
}
