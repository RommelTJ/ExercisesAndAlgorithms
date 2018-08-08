package com.rommelrico.exercises.s8linkedlists.kthtolastnode;

public class KthToLastNode {

    public static LinkedListNode kthToLastNode(int k, LinkedListNode head) {

        if (k < 1) {
            throw new IllegalArgumentException(
                    "Impossible to find less than first to last node: " + k);
        }

        LinkedListNode leftNode  = head;
        LinkedListNode rightNode = head;

        // move rightNode to the kth node
        for (int i = 0; i < k - 1; i++) {

            // but along the way, if a rightNode doesn't have a next,
            // then k is greater than the length of the list and there
            // can't be a kth-to-last node! we'll raise an error
            if (rightNode.next == null) {
                throw new IllegalArgumentException(
                        "k is larger than the length of the linked list: " + k);
            }

            rightNode = rightNode.next;
        }

        // starting with leftNode on the head,
        // move leftNode and rightNode down the list,
        // maintaining a distance of k between them,
        // until rightNode hits the end of the list
        while (rightNode.next != null) {
            leftNode  = leftNode.next;
            rightNode = rightNode.next;
        }

        // since leftNode is k nodes behind rightNode,
        // leftNode is now the kth to last node!
        return leftNode;
    }
    
}
