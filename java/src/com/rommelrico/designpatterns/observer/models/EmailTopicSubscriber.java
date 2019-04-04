package com.rommelrico.designpatterns.observer.models;

import com.rommelrico.designpatterns.observer.interfaces.*;

public class EmailTopicSubscriber implements Observer {

    private String name;
    private Subject topic; // Reference to the Subject interface

    public EmailTopicSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update() {
        String message = (String) topic.getUpdate(this);
        if (message == null) System.out.println(name + " No new message on this topic!");
        else System.out.println(name + " Retrieving message: " + message);
    }

    @Override
    public void setSubject(Subject subject) {
        this.topic = subject;
    }
    
}
