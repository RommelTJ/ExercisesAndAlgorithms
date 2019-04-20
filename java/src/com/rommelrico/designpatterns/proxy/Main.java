package com.rommelrico.designpatterns.proxy;

import com.rommelrico.designpatterns.proxy.interfaces.*;
import com.rommelrico.designpatterns.proxy.models.*;

public class Main {

    public static void main(String[] args) {
        Bank bank = new ProxyBank();
        try {
            bank.withdrawMoney("Rommel");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("----");

        try {
            bank.withdrawMoney("hackerman");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
