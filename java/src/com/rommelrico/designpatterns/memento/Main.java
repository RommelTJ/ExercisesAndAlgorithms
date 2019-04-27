package com.rommelrico.designpatterns.memento;

import com.rommelrico.designpatterns.memento.models.*;

public class Main {

    public static void main(String[] args) {
        Originator originator = new Originator("Monster");
        Memento memento = originator.createMemento();

        CareTaker careTaker = new CareTaker();
        careTaker.addMemento(memento);

        originator.setState("Monstrosity");
        originator.setState("Beauty");

        memento = originator.createMemento();
        careTaker.addMemento(memento);

        originator.setState("Beast");

        System.out.println("Originator current state: " + originator.getState());
        System.out.println("Originator restoring to previous state...");
        memento = careTaker.getMemento(1);
        originator.setMemento(memento);
        System.out.println("Originator current state: " + originator.getState());
        System.out.println("Originator restoring to previous state...");
        memento = careTaker.getMemento(0);
        originator.setMemento(memento);
        System.out.println("Originator current state: " + originator.getState());
    }

}
