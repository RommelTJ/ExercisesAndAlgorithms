package com.rommelrico.designpatterns.adapter;

import com.rommelrico.designpatterns.adapter.interfaces.*;
import com.rommelrico.designpatterns.adapter.models.*;

public class Main {

    public static void main(String[] args) {

    }

    private static Volt getVolt(SocketAdapter socketAdapter, int i) {
        switch (i) {
            case 3:
                return socketAdapter.get3Volts();
            case 12:
                return socketAdapter.get12Volts();
            default:
                return socketAdapter.get120Volts();
        }
    }

}
