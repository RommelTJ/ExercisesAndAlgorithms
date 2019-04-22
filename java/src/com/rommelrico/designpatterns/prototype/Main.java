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
    }
}
