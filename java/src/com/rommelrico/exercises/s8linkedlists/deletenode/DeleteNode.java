package com.rommelrico.exercises.s8linkedlists.deletenode;

public class DeleteNode {

    public static void deleteNode(LinkedListNode nodeToDelete) {

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
