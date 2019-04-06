package com.rommelrico.designpatterns.factory.model;

class JamaicanCheeseburger extends Hamburger {

    JamaicanCheeseburger() {
        this.name = "Jamaican Cheeseburger";
        this.buns = "Jamaican Buns";
        this.sauce = "Jamaican Sauce";
    }

    @Override
    public void cook() {
        System.out.println("Cooking Jamaican style!");
    }
}
