package com.rommelrico.exercises.q9balancedbinarytree;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

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
