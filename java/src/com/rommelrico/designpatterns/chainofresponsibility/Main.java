package com.rommelrico.designpatterns.chainofresponsibility;

import com.rommelrico.designpatterns.chainofresponsibility.models.*;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        CEOPurchasePower ceo = new CEOPurchasePower();
        Director director = new Director();
        Manager manager = new Manager();

        ceo.setSuccessor(director);
        director.setSuccessor(ceo);
        manager.setSuccessor(director);

        while (true) {
            System.out.println("Enter the amount to check who should approve your budget: ");
            System.out.print(">>");

            try {
                double d = Double.parseDouble(new BufferedReader(new InputStreamReader(System.in)).readLine());
                manager.processRequest(new PurchaseRequest(d, "Buy Stuff"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
