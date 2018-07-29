package com.rommelrico.exercises.s6dynamicprogrammingandrecursion.binarysearchtreechecker;

import com.rommelrico.exercises.s6dynamicprogrammingandrecursion.binarysearchtreechecker.BinaryTreeNode;
import com.rommelrico.exercises.s6dynamicprogrammingandrecursion.binarysearchtreechecker.NodeBounds;

import java.util.Stack;

/**
 * Solution
 *
 * We do a depth-first walk through the tree, testing each node for validity as we go. A given node is valid if it's
 * greater than all the ancestral nodes it's in the right sub-tree of and less than all the ancestral nodes it's in the
 * left-subtree of. Instead of keeping track of each ancestor to check these inequalities, we just check the largest
 * number it must be greater than (its lowerBound) and the smallest number it must be less than (its upperBound).
 *
 * Complexity
 *
 * O(n) time and O(n) space.
 *
 * What We Learned
 *
 * We could think of this as a greedy approach. We start off by trying to solve the problem in just one walk through
 * the tree. So we ask ourselves what values we need to track in order to do that. Which leads us to our stack that
 * tracks upper and lower bounds.
 *
 * We could also think of this as a sort of "divide and conquer" approach. The idea in general behind divide and
 * conquer is to break the problem down into two or more subproblems, solve them, and then use that solution to solve
 * the original problem.
 *
 * In this case, we're dividing the problem into subproblems by saying, "This tree is a valid binary search tree if
 * the left subtree is valid and the right subtree is valid." This is more apparent in the recursive formulation of the
 * answer above.
 *
 * Of course, it's just fine that our approach could be thought of as greedy or could be thought of as divide and
 * conquer. It can be both. The point here isn't to create strict categorizations so we can debate whether or not
 * something "counts" as divide and conquer.
 *
 * Instead, the point is to recognize the underlying patterns behind algorithms, so we can get better at thinking
 * through problems.
 *
 * Sometimes we'll have to kinda smoosh together two or more different patterns to get our answer.
 *
 */
public class BinarySearchTreeChecker {

    public static boolean isBinarySearchTree(BinaryTreeNode root) {

        // start at the root, with an arbitrarily low lower bound
        // and an arbitrarily high upper bound
        Stack<NodeBounds> nodeAndBoundsStack = new Stack<>();
        nodeAndBoundsStack.push(new NodeBounds(root, Integer.MIN_VALUE, Integer.MAX_VALUE));

        // depth-first traversal
        while (!nodeAndBoundsStack.empty()) {
            NodeBounds nb = nodeAndBoundsStack.pop();
            BinaryTreeNode node = nb.node;
            int lowerBound = nb.lowerBound;
            int upperBound = nb.upperBound;

            // if this node is invalid, we return false right away
            if (node.value <= lowerBound || node.value >= upperBound) {
                return false;
            }

            if (node.left != null) {
                // this node must be less than the current node
                nodeAndBoundsStack.push(new NodeBounds(node.left, lowerBound, node.value));
            }
            if (node.right != null) {
                // this node must be greater than the current node
                nodeAndBoundsStack.push(new NodeBounds(node.right, node.value, upperBound));
            }
        }

        // if none of the nodes were invalid, return true
        // (at this point we have checked all nodes)
        return true;
    }

    public static void main(String[] args) {
        BinaryTreeNode rootNode = new BinaryTreeNode(50);
        BinaryTreeNode left1 = rootNode.insertLeft(30);
        BinaryTreeNode right1 = rootNode.insertRight(80);
        System.out.println("Is Binary Search Tree: " + isBinarySearchTree(rootNode)); // true

        left1.insertLeft(20);
        left1.insertRight(60);
        right1.insertLeft(70);
        right1.insertRight(90);
        System.out.println("Is Binary Search Tree: " + isBinarySearchTree(rootNode)); // false
    }

}
