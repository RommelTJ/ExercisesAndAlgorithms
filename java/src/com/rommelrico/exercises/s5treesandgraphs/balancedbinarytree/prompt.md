### Balanced Binary Tree

Write a method to see if a binary tree is "superbalanced" (a new tree property we just made up).

A tree is "superbalanced" if the difference between the depths of any two leaf nodes is no greater than one.

Here's a sample binary tree node class:

```
public class BinaryTreeNode {

    public int value;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode(int value) {
        this.value = value;
    }

    public BinaryTreeNode insertLeft(int leftValue) {
        this.left = new BinaryTreeNode(leftValue);
        return this.left;
    }

    public BinaryTreeNode insertRight(int rightValue) {
        this.right = new BinaryTreeNode(rightValue);
        return this.right;
    }
}
```
