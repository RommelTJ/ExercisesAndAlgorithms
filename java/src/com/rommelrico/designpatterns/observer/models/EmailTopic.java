package com.rommelrico.designpatterns.observer.models;

import com.rommelrico.designpatterns.observer.interfaces.*;
import com.rommelrico.designpatterns.observer.interfaces.Observer;

import java.util.*;

public class EmailTopic implements Subject {

    private List<Observer> observers;
    private String message;

    public EmailTopic(List<Observer> observers) {
        this.observers = observers;
    }

    @Override
    public void register(Observer observer) {
        if (observer == null) throw new NullPointerException("No observer instance");
        if (!observers.contains(observer)) observers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer: observers) {
            observer.update();
        }
    }

    @Override
    public Object getUpdate(Observer observer) {
        return this.message;
    }

    public void postMessage(String message) {
        System.out.println("Message posted to my topic: " + message);
        this.message = message;
        notifyObservers();
    }
}
