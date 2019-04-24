package com.rommelrico.designpatterns.mediator;

import com.rommelrico.designpatterns.mediator.interfaces.*;
import com.rommelrico.designpatterns.mediator.models.*;

public class Main {

    public static void main(String[] args) {
        ATCMediator mediator = new ATCMediatorImpl();

        Aircraft a1 = new AircraftImpl(mediator, "A1");
        Aircraft a2 = new AircraftImpl(mediator, "A2");
        Aircraft a3 = new AircraftImpl(mediator, "A3");
        Aircraft a4 = new AircraftImpl(mediator, "A4");
        Aircraft a5 = new AircraftImpl(mediator, "A5");

        mediator.addAircraft(a1);
        mediator.addAircraft(a2);
        mediator.addAircraft(a3);
        mediator.addAircraft(a4);
        mediator.addAircraft(a5);

        a1.send("Hello World");
        a3.send("Hi there!");
    }
    
}
