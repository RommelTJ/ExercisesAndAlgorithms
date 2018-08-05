package com.rommelrico.exercises.s8linkedlists.reverselinkedlist;

import java.util.LinkedList;

public class ReverseLinkedList {

    public static LinkedListNode reverse(LinkedListNode headOfList) {
        LinkedListNode currentNode = headOfList;
        LinkedListNode previousNode = null;
        LinkedListNode nextNode = null;

        // until we have 'fallen off' the end of the list
        while (currentNode != null) {

            // copy a pointer to the next element
            // before we overwrite currentNode.next
            nextNode = currentNode.next;

            // reverse the 'next' pointer
            currentNode.next = previousNode;

            // step forward in the list
            previousNode = currentNode;
            currentNode = nextNode;
        }

        return previousNode;
    }

    public static void main(String[] args) {
        LinkedListNode node1 = new LinkedListNode(1);
        LinkedListNode node2 = new LinkedListNode(2);
        LinkedListNode node3 = new LinkedListNode(3);
        node1.next = node2;
        node2.next = node3;

        System.out.println("node1 = " + node1.toString());
        System.out.println("node2 = " + node2.toString());
        System.out.println("node3 = " + node3.toString());

        reverse(node1);

        System.out.println("node1 = " + node1.toString());
        System.out.println("node2 = " + node2.toString());
        System.out.println("node3 = " + node3.toString());
    }

}
