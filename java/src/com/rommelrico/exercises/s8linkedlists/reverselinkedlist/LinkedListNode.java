package com.rommelrico.exercises.s8linkedlists.reverselinkedlist;

public class LinkedListNode {

    public int value;
    public LinkedListNode next;

    public LinkedListNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LinkedListNode{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}
