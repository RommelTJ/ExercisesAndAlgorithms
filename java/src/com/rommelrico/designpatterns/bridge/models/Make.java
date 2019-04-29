package com.rommelrico.designpatterns.bridge.models;

import com.rommelrico.designpatterns.bridge.interfaces.*;

public class Make implements Workshop {

    @Override
    public void make() {
        System.out.println("Making...");
    }

}
