package com.rommelrico.designpatterns.builder;

import com.rommelrico.designpatterns.builder.models.*;

public class Main {

    public static void main(String[] args) {
        User rommel = new User.UserBuilder("Rommel", "Rico")
                .address("Beverly Hills")
                .age(30)
                .phoneNumber("007")
                .build();

        System.out.println(rommel);
    }
}
