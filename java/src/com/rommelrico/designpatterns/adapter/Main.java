package com.rommelrico.designpatterns.adapter;

import com.rommelrico.designpatterns.adapter.interfaces.*;
import com.rommelrico.designpatterns.adapter.models.*;

public class Main {

    public static void main(String[] args) {
        testingObjectAdapter();
    }

    private static void testingObjectAdapter() {
        SocketAdapter socketAdapter = new SocketObjectAdapterImpl();
        Volt v3 = getVolt(socketAdapter, 3);
        Volt v12 = getVolt(socketAdapter, 12);
        Volt v120 = getVolt(socketAdapter, 120);

        System.out.println("V3 volts is using Object Adapter " + v3.getVolts());
        System.out.println("V12 volts is using Object Adapter " + v12.getVolts());
        System.out.println("V120 volts is using Object Adapter " + v120.getVolts());
    }
    private static void testingSocketAdapter() {
        SocketAdapter socketAdapter = new SocketAdapterImpl();
        Volt v3 = getVolt(socketAdapter, 3);
        Volt v12 = getVolt(socketAdapter, 12);
        Volt v120 = getVolt(socketAdapter, 120);

        System.out.println("V3 volts is using Socket Adapter " + v3.getVolts());
        System.out.println("V12 volts is using Socket Adapter " + v12.getVolts());
        System.out.println("V120 volts is using Socket Adapter " + v120.getVolts());
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
