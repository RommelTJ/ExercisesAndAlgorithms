package com.rommelrico.exercises.s5treesandgraphs.balancedbinarytree;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Solution
 *
 * We do a depth-first walk through our tree, keeping track of the depth as we go. When we find a leaf, we add its
 * depth to a list of depths if we haven't seen that depth already.
 *
 * Each time we hit a leaf with a new depth, there are two ways that our tree might now be unbalanced:
 * 1. There are more than 2 different leaf depths
 * 2. There are exactly 2 leaf depths and they are more than 1 apart.
 *
 * Why are we doing a depth-first walk and not a breadth-first one? You could make a case for either. We chose
 * depth-first because it reaches leaves faster, which allows us to short-circuit earlier in some cases.
 *
 * Complexity
 *
 * O(n) time and O(n) space.
 *
 * What We Learned
 *
 * This is an intro to some tree basics. If this is new to you, don't worry—it can take a few questions for this
 * stuff to come together. We have a few more coming up.
 *
 * Particular things to note:
 *
 * Focus on depth-first vs breadth-first traversal. You should be very comfortable with the differences between the
 * two and the strengths and weaknesses of each.
 *
 * You should also be very comfortable coding each of them up.
 *
 * One tip: Remember that breadth-first uses a queue and depth-first uses a stack (could be the call stack or an
 * actual stack object). That's not just a clue about implementation, it also helps with figuring out the differences
 * in behavior. Those differences come from whether we visit nodes in the order we see them (first in, first out) or we
 * visit the last-seen node first (last in, first out).
 *
 */
public class BalancedBinaryTree {

    public static boolean isBalanced(BinaryTreeNode treeRoot) {

        // a tree with no nodes is superbalanced, since there are no leaves!
        if (treeRoot == null) {
            return true;
        }

        // we short-circuit as soon as we find more than 2
        List<Integer> depths = new ArrayList<>(3);

        // nodes will store pairs of a node and the node's depth
        Stack<NodeDepthPair> nodes = new Stack<>();
        nodes.push(new NodeDepthPair(treeRoot, 0));

        while (!nodes.empty()) {

            // pop a node and its depth from the top of our stack
            NodeDepthPair nodeDepthPair = nodes.pop();
            BinaryTreeNode node = nodeDepthPair.node;
            int depth = nodeDepthPair.depth;

            // case: we found a leaf
            if (node.left == null && node.right == null) {

                // we only care if it's a new depth
                if (!depths.contains(depth)) {
                    depths.add(depth);

                    // two ways we might now have an unbalanced tree:
                    //   1) more than 2 different leaf depths
                    //   2) 2 leaf depths that are more than 1 apart
                    if (depths.size() > 2 || (depths.size() == 2
                            && Math.abs(depths.get(0) - depths.get(1)) > 1)) {
                        return false;
                    }
                }

            // case: this isn't a leaf - keep stepping down
            } else {
                if (node.left != null) {
                    nodes.push(new NodeDepthPair(node.left, depth + 1));
                }
                if (node.right != null) {
                    nodes.push(new NodeDepthPair(node.right, depth + 1));
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        BinaryTreeNode rootNode = new BinaryTreeNode(0);
        assert isBalanced(rootNode); // no leaves = true

        rootNode.insertLeft(4); // leaf at depth == 0
        assert isBalanced(rootNode); // difference in depth is 0 == superbalanced.

        rootNode.insertRight(5); // Leaf at depth == 0
        rootNode.insertLeft(1).insertLeft(2).insertRight(10); // Leaf at depth == 2
        assert !isBalanced(rootNode); // difference in depth is 2 == not balanced

        rootNode.insertRight(5).insertRight(4); // Leaf at depth == 1
        assert isBalanced(rootNode); // difference in depth is 1 == balanced
    }

}
