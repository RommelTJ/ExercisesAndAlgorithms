package com.rommelrico.exercises.q11secondlargestiteminbst;

public class Find2ndLargestInBST {

    public static int findLargest(BinaryTreeNode rootNode) {
        BinaryTreeNode current = rootNode;
        BinaryTreeNode largest = null;

        while (current != null) {
            if (current.right == null) {
                largest = current;
            }
            current = current.right;
        }
        return largest.value;
    }

    
}
