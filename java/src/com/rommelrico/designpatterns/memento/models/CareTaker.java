package com.rommelrico.designpatterns.memento.models;

import java.util.*;

public class CareTaker {

    private List stateList;

    public CareTaker() {
        this.stateList = new ArrayList();
    }

    public void addMemento(Memento memento) {
        stateList.add(memento);
    }

    public Memento getMemento(int index) {
        return (Memento) stateList.get(index);
    }

}
