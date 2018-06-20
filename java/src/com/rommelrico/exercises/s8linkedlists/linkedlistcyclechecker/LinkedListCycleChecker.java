package com.rommelrico.exercises.s8linkedlists.linkedlistcyclechecker;

/**
 * Solution
 *
 * We keep two pointers to nodes (we'll call these “runners”), both starting at the first node. Every time slowRunner
 * moves one node ahead, fastRunner moves two nodes ahead.
 *
 * If the linked list has a cycle, fastRunner will "lap" (catch up with) slowRunner, and they will momentarily equal
 * each other.
 *
 * If the list does not have a cycle, fastRunner will reach the end.
 *
 * Complexity
 *
 * O(n) time and O(1) space.
 *
 * What We Learned
 *
 * Some people have trouble coming up with the "two runners" approach. That's expected — it's somewhat of a blind
 * insight. Even great candidates might need a few hints to get all the way there. And that's fine.
 *
 * Remember that the coding interview is a dialogue, and sometimes your interviewer expects she'll have to offer some
 * hints along the way.
 *
 * One of the most impressive things you can do as a candidate is listen to a hint, fully understand it, and take it
 * to its next logical step.
 *
 */
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
