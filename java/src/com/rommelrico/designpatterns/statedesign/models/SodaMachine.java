package com.rommelrico.designpatterns.statedesign.models;

public class SodaMachine {

    private final static int SOLD_OUT = 0;
    private final static int NO_MONEY = 1;
    private final static int HAS_MONEY = 2;
    private final static int SOLD = 3;

    private int state = SOLD_OUT;
    private int count = 0;

    public SodaMachine(int count) {
        this.count = count;

        if (count > 0) {
            state = NO_MONEY;

            System.out.println("Welcome to our Soda Vending Machine");
            System.out.println("Inventory is " + count + " Sodas");
            System.out.println("Insert a dollar bill to get started...");
        }
    }

    // Actions

    public void insertMoney() {
        if (state == HAS_MONEY) {
            System.out.println("You can't insert another dollar!");
        } else if (state == NO_MONEY) {
            state = HAS_MONEY;
            System.out.println("You inserted some money.");
        } else if (state == SOLD_OUT) {
            System.out.println("The machine is sold out.");
        } else if (state == SOLD) {
            System.out.println("Please wait! We are giving you a soda.");
        }
    }

    public void ejectMoney() {
        if (state == HAS_MONEY) {
            state = NO_MONEY;
            System.out.println("Returning dollar bill!");
        } else if (state == NO_MONEY) {
            System.out.println("You haven't inserted a dollar bill.");
        } else if (state == SOLD) {
            System.out.println("Already selected soda!");
        } else if (state == SOLD_OUT) {
            System.out.println("Machine sold out!");
        }
    }

    public void selectSoda() {
        if (state == HAS_MONEY) {
            state = SOLD;
            System.out.println("Selected soda...");
            dispense();
        } else if (state == NO_MONEY) {
            System.out.println("You selected soda, but money first, buddy!");
        } else if (state == SOLD) {
            System.out.println("Dispensing your soda.");
        } else if (state == SOLD_OUT) {
            System.out.println("Machine sold out!");
        }
    }

    private void dispense() {
        if (state == HAS_MONEY) {
            System.out.println("No soda dispensed.");
        } else if (state == NO_MONEY) {
            System.out.println("You haven't inserted a dollar bill.");
        } else if (state == SOLD) {
            System.out.println("Dispensing your soda!");
            count = count - 1;
            if (count == 0) {
                System.out.println("Sorry, out of sodas!");
                state = SOLD_OUT;
            } else {
                state = NO_MONEY;
            }
        } else if (state == SOLD_OUT) {
            System.out.println("Machine sold out!");
        }
    }

}
