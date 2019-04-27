package com.rommelrico.designpatterns.memento.models;

public class Originator {

    private String state;

    public Originator(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memento createMemento() {
        return new Memento(this.getState());
    }

    public void setMemento(Memento memento) {
        setState(memento.getState());
    }

}
