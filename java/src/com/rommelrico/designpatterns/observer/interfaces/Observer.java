package com.rommelrico.designpatterns.observer.interfaces;

public interface Observer {
    void update();
    void setSubject(Subject subject);
}
