package com.rommelrico.exercises.s8linkedlists.deletenode;

/**
 * Solution
 *
 * We take value and next from the input node's next node and copy them into the input node. Now the input node's
 * previous node effectively skips the input node's old value!
 *
 * Note that this approach doesn't work for deleting the last node in the list. It also has the following side-effects:
 * 1. Any references to the input node have now effectively been reassigned to its next node.
 * 2. If there are pointers to the input node's original next node, those pointers now point to a "dangling" node.
 *
 * Complexity
 *
 * O(1) time and O(1) space.
 *
 * What We Learned
 *
 * My favorite part of this problem is how imperfect the solution is. Because it modifies the list "in place" it can
 * cause other parts of the surrounding system to break. This is called a "side effect."
 *
 * In-place operations like this can save time and/or space, but they're risky. If you ever make in-place modifications
 * in an interview, make sure you tell your interviewer that in a real system you'd carefully check for side effects in
 * the rest of the code base.
 *
 */
public class DeleteNode {

    private static void deleteNode(LinkedListNode nodeToDelete) {

        // get the input node's next node, the one we want to skip to
        LinkedListNode nextNode = nodeToDelete.next;

        if (nextNode != null) {

            // replace the input node's value and pointer with the next
            // node's value and pointer. the previous node now effectively
            // skips over the input node
            nodeToDelete.value = nextNode.value;
            nodeToDelete.next  = nextNode.next;

        } else {

            // eep, we're trying to delete the last node!
            throw new IllegalArgumentException("Can't delete the last node with this technique!");
        }
    }

    public static void main(String[] args) {
        LinkedListNode a = new LinkedListNode(1);
        LinkedListNode b = new LinkedListNode(2);
        LinkedListNode c = new LinkedListNode(3);

        a.next = b;
        b.next = c;

        System.out.println("a.next.value: " + a.next.value);
        deleteNode(b);
        System.out.println("a.next.value: " + a.next.value);
    }

}
