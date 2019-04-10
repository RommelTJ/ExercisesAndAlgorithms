package com.rommelrico.designpatterns.singleton;

public class Main {

    public static void main(String[] args) {
        MyClass myClass = MyClass.getInstance();
        MyClass secondClass = MyClass.getInstance();
        myClass.setName("My Class");
        System.out.println(myClass.getName());
        System.out.println(myClass);
        System.out.println(secondClass);

        Person person = new Person();
        Person secondPerson = new Person();
        System.out.println("Object person: " + person);
        System.out.println("Object person: " + secondPerson);
    }
}
