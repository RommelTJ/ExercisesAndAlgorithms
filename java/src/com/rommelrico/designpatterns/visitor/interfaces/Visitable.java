package com.rommelrico.designpatterns.visitor.interfaces;

public interface Visitable {
    double accept(Visitor visitor);
}
