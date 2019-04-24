package com.rommelrico.designpatterns.mediator.interfaces;

public interface ATCMediator {

    void sendMessage(String msg, Aircraft aircraft);
    void addAircraft(Aircraft aircraft);

}
