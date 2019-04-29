package com.rommelrico.designpatterns.chainofresponsibility.interfaces;

import com.rommelrico.designpatterns.chainofresponsibility.models.*;

public abstract class PurchasePower {

    protected static final double BASE = 1000;
    private PurchasePower successor;

    abstract protected double getAllowable();
    abstract protected String getRole();

    public PurchasePower getSuccessor() {
        return successor;
    }

    public void setSuccessor(PurchasePower successor) {
        this.successor = successor;
    }

    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < this.getAllowable()) {
            System.out.println(this.getRole() + " will approve request for $" + request.getAmount());
        } else if (successor != null) {
            successor.processRequest(request);
        }
    }

}
