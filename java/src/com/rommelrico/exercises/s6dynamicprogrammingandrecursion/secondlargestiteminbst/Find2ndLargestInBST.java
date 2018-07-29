package com.rommelrico.exercises.s6dynamicprogrammingandrecursion.secondlargestiteminbst;

import com.rommelrico.exercises.s6dynamicprogrammingandrecursion.secondlargestiteminbst.BinaryTreeNode;

/**
 * Solution
 *
 * We start with a method for getting the largest value. The largest value is simply the "rightmost" one, so we can
 * get it in one walk down the tree by traversing rightward until we don't have a right child anymore.
 *
 * With this in mind, we can also find the second largest in one walk down the tree. At each step, we have a few cases:
 * 1. If we have a left subtree but not a right subtree, then the current node is the largest overall (the
 *    "rightmost") node. The second largest element must be the largest element in the left subtree. We use our
 *    findLargest() method above to find the largest in that left subtree!
 * 2. If we have a right child, but that right child node doesn't have any children, then the right child must be the
 *    largest element and our current node must be the second largest element!
 * 3. Else, we have a right subtree with more than one element, so the largest and second largest are somewhere in
 *    that subtree. So we step right.
 *
 * Complexity
 *
 * We're doing one walk down our BST, which means O(h) time, where h is the height of the tree (again, that's O(lg{n})
 * if the tree is balanced, O(n) otherwise). O(1) space.
 *
 * What We Learned
 *
 * Here we used a "simplify, solve, and adapt" strategy.
 *
 * The question asks for a method to find the second largest element in a BST, so we started off by simplifying the
 * problem: we thought about how to find the first largest element.
 *
 * Once we had a strategy for that, we adapted that strategy to work for finding the second largest element.
 *
 * It may seem counter-intuitive to start off by solving the wrong question. But starting off with a simpler version
 * of the problem is often much faster, because it's easier to wrap our heads around right away.
 *
 * One more note about this one: Breaking things down into cases is another strategy that really helped us here.
 * Notice how simple finding the second largest node got when we divided it into two cases:
 * 1. The largest node has a left subtree.
 * 2. The largest node does not have a left subtree.
 *
 * Whenever a problem is starting to feel complicated, try breaking it down into cases.
 *
 * It can be really helpful to actually draw out sample inputs for those cases. This'll keep your thinking organized
 * and also help get your interviewer on the same page.
 *
 */
public class Find2ndLargestInBST {

    private static int findLargest(BinaryTreeNode rootNode) {
        BinaryTreeNode current = rootNode;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    public static int findSecondLargest(BinaryTreeNode rootNode) {
        if (rootNode == null || (rootNode.left == null
                && rootNode.right == null)) {
            throw new IllegalArgumentException("Tree must have at least 2 nodes");
        }

        BinaryTreeNode current = rootNode;

        while (true) {

            // case: current is largest and has a left subtree
            // 2nd largest is the largest in that subtree
            if (current.left != null && current.right == null) {
                return findLargest(current.left);
            }

            // case: current is parent of largest, and largest has no children,
            // so current is 2nd largest
            if (current.right != null &&
                    current.right.left == null &&
                    current.right.right == null) {
                return current.value;
            }

            current = current.right;
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode rootNode = new BinaryTreeNode(50);
        BinaryTreeNode left1 = rootNode.insertLeft(30);
        BinaryTreeNode right1 = rootNode.insertRight(80);
        left1.insertLeft(20);
        left1.insertRight(60);
        right1.insertLeft(70);
        right1.insertRight(90);
        System.out.println("2nd Largest: " + findSecondLargest(rootNode)); // false
    }

}
