package com.rommelrico.designpatterns.singleton;

public class Main {

    public static void main(String[] args) {
        MyClass myClass = MyClass.getInstance();
        myClass.setName("My Class");
        System.out.println(myClass.getName());
    }
}
