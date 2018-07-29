package com.rommelrico.exercises.s6dynamicprogrammingandrecursion.binarysearchtreechecker;

import com.rommelrico.exercises.s6dynamicprogrammingandrecursion.binarysearchtreechecker.BinaryTreeNode;

public class NodeBounds {

    BinaryTreeNode node;
    int lowerBound;
    int upperBound;

    NodeBounds(BinaryTreeNode node, int lowerBound, int upperBound) {
        this.node = node;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }
}
