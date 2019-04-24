package com.rommelrico.designpatterns.mediator.models;

import com.rommelrico.designpatterns.mediator.interfaces.*;

public class AircraftImpl extends Aircraft {

    public AircraftImpl(ATCMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String msg) {
        System.out.println(this.name + ": Sending message = " + msg);
        mediator.sendMessage(msg, this);
    }

    @Override
    public void receive(String msg) {
        System.out.println(this.name + ": Received message = " + msg);
    }
}
