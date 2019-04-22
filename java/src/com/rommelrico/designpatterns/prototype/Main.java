package com.rommelrico.designpatterns.prototype;

import com.rommelrico.designpatterns.prototype.models.*;

public class Main {

    public static void main(String[] args) {
        Person rommel = new Person("Rommel", 30);
        System.out.println(rommel);

        Person liza = (Person) rommel.clone();
        liza.setName("Liza");
        liza.setAge(21);
        System.out.println(liza);

        Dolphin jerry = new Dolphin("Jerry", 33);
        System.out.println(jerry);
        Dolphin sam = (Dolphin) jerry.clone();
        sam.setName("Sam");
        System.out.println(sam);

        System.out.println("----");

        Person2 person2 = new Person2("James", 45);
        System.out.println(person2);
        Person2 person21 = (Person2) person2.clone();
        person21.setName("Person 21");
        System.out.println(person21);
    }
}
