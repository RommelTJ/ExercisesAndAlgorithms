package com.rommelrico.designpatterns.mediator.models;

import com.rommelrico.designpatterns.mediator.interfaces.*;

import java.util.*;

public class ATCMediatorImpl implements ATCMediator {

    private List<Aircraft> aircraftList;

    public ATCMediatorImpl() {
        this.aircraftList = new ArrayList<>();
    }

    @Override
    public void sendMessage(String msg, Aircraft aircraft) {
        for (Aircraft a : aircraftList) {
            if (a != aircraft) {
                a.receive(msg);
            }
        }
    }

    @Override
    public void addAircraft(Aircraft aircraft) {
        aircraftList.add(aircraft);
    }

}
