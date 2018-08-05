package com.rommelrico.exercises.s8linkedlists.reverselinkedlist;

/**
 * Solution
 *
 * In one pass from head to tail of our input list, we point each node's next pointer to the previous item.
 *
 * The order of operations is important here! We're careful to copy currentNode.next into next before setting
 * currentNode.next to previousNode. Otherwise "stepping forward" at the end could actually mean stepping back to
 * previousNode!
 *
 * Complexity
 *
 * O(n) time and O(1) space.
 *
 * What We Learned
 *
 * It's one of those problems where, even once you know the procedure, it's hard to write a bug-free solution. Drawing
 * it out helps a lot. Write out a sample linked list and walk through your code by hand, step by step, running each
 * operation on your sample input to see if the final output is what you expect. This is a great strategy for any
 * coding interview question.
 *
 */
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
