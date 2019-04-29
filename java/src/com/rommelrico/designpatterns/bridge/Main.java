package com.rommelrico.designpatterns.bridge;

import com.rommelrico.designpatterns.bridge.interfaces.*;
import com.rommelrico.designpatterns.bridge.models.*;

public class Main {

    public static void main(String[] args) {
        Vehicle bmw = new Car(new Make(), new Assemble());
        bmw.manufacture();

        Vehicle cinelli = new Bike(new Make(), new Assemble());
        cinelli.manufacture();
    }

}
