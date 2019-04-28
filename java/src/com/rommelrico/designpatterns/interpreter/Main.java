package com.rommelrico.designpatterns.interpreter;

import com.rommelrico.designpatterns.interpreter.interfaces.*;
import com.rommelrico.designpatterns.interpreter.models.*;

public class Main {

    private InterpreterContext context;

    public Main(InterpreterContext context) {
        this.context = context;
    }

    public String interpret(String string) {
        Expression expression = null;
        if (string.contains("Hexadecimal")) {
            expression = new IntToHexExpression(Integer.parseInt(string.substring(0, string.indexOf(" "))));
        } else if (string.contains("Binary")) {
            expression = new IntToBinaryExpression(Integer.parseInt(string.substring(0, string.indexOf(" "))));
        } else {
            return string;
        }

        return expression.interpreter(context);
    }

    public static void main(String[] args) {
        String first = "13 Binary";
        String second = "28 Hexadecimal";

        Main interpreter = new Main(new InterpreterContext());
        System.out.println(first + " = " + interpreter.interpret(first));
        System.out.println(second + " = " + interpreter.interpret(second));
    }



}
