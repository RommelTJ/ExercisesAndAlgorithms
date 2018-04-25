package com.rommelrico.exercises.q24linkedlistcyclechecker;

import java.util.LinkedList;

public class LinkedListCycleChecker {

    public static boolean containsCycle(LinkedListNode firstNode) {

        // start both runners at the beginning
        LinkedListNode slowRunner = firstNode;
        LinkedListNode fastRunner = firstNode;

        // until we hit the end of the list
        while (fastRunner != null && fastRunner.next != null) {
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next.next;

            // case: fastRunner is about to "lap" slowRunner
            if (fastRunner == slowRunner) {
                return true;
            }
        }

        // case: fastRunner hit the end of the list
        return false;
    }


    public static void main(String[] args) {
        LinkedListNode node1 = new LinkedListNode(1);
        LinkedListNode node2 = new LinkedListNode(2);
        LinkedListNode node3 = new LinkedListNode(3);
        LinkedListNode node4 = new LinkedListNode(4);

        // false
        System.out.println("containsCycle: " + containsCycle(node1));

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        // false
        System.out.println("containsCycle: " + containsCycle(node1));

        node3.next = node1;

        // true
        System.out.println("containsCycle: " + containsCycle(node1));
    }

}
