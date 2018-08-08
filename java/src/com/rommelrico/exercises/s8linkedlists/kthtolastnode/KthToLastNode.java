package com.rommelrico.exercises.s8linkedlists.kthtolastnode;

/**
 * Solution
 *
 * Our stick approach is as follows:
 * 1. Walk one pointer k nodes from the head. Call it rightNode.
 * 2. Put another pointer at the head. Call it leftNode.
 * 3. Walk both pointers, at the same speed, towards the tail. This keeps a distance of k between them.
 * 4. When rightNode hits the tail, leftNode is on the target (since it's k nodes from the end of the list).
 *
 * Complexity
 *
 * O(n) time and O(1) space.
 *
 * However, the stick approach might still be slightly faster, due to some caching and other optimizations that modern
 * processors and memory have.
 *
 * Let's focus on caching. Usually when we grab some data from memory (for example, info about a linked list node), we
 * also store that data in a small cache right on the processor. If we need to use that same data again soon after, we
 * can quickly grab it from the cache. But if we don't use that data for a while, we're likely to replace it with other
 * stuff we've used more recently (this is called a "least recently used" replacement policy).
 *
 * Both of our algorithms access a lot of nodes in our list twice, so they could exploit this caching. But notice that
 * in our stick algorithm there's a much shorter time between the first and second times that we access a given node
 * (this is sometimes called "temporal locality of reference"). Thus it seems more likely that our second algorithm
 * will save time by using the processor's cache! But this assumes our processor's cache uses something like a "least
 * recently used" replacement policy—it might use something else. Ultimately the best way to really know which
 * algorithm is faster is to implement both and time them on a few different inputs!
 *
 * What We Learned
 *
 * We listed two good solutions. One seemed to solve the problem in one pass, while the other took two passes. But the
 * single-pass approach didn't take half as many steps, it just took the same steps in a different order.
 *
 * So don't be fooled: "one pass" isn't always fewer steps than "two passes." Always ask yourself, "Have I actually
 * changed the number of steps?"
 *
 */
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

    public static void main(String[] args) {
        LinkedListNode a = new LinkedListNode("Angel Food");
        LinkedListNode b = new LinkedListNode("Bundt");
        LinkedListNode c = new LinkedListNode("Cheese");
        LinkedListNode d = new LinkedListNode("Devil's Food");
        LinkedListNode e = new LinkedListNode("Eccles");

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        System.out.println(kthToLastNode(3, a));
    }

}
