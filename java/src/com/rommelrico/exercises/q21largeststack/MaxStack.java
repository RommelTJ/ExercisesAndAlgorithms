package com.rommelrico.exercises.q21largeststack;

import java.util.Stack;

/**
 * Solution
 *
 * We define two new stacks within our MaxStack class—stack holds all of our integers, and maxesStack holds our
 * "maxima." We use maxesStack to keep our max up to date in constant time as we push() and pop():
 * 1. Whenever we push() a new item, we check to see if it's greater than or equal to the current max, which is at the
 *    top of maxesStack. If it is, we also push() it onto maxesStack.
 * 2. Whenever we pop(), we also pop() from the top of maxesStack if the item equals the top item in maxesStack.
 *
 * Complexity
 *
 * O(1) time for push(), pop(), and getMax(). O(m) additional space, where m is the number of operations performed on
 * the stack.
 *
 * What We Learned
 *
 * Notice how in the solution we're spending time on push() and pop() so we can save time on getMax(). That's because
 * we chose to optimize for the time cost of calls to getMax().
 *
 * But we could've chosen to optimize for something else. For example, if we expected we'd be running push() and pop()
 * frequently and running getMax() rarely, we could have optimized for faster push() and pop() methods.
 *
 * Sometimes the first step in algorithm design is deciding what we're optimizing for. Start by considering the
 * expected characteristics of the input.
 *
 */
public class MaxStack {

    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> maxesStack = new Stack<>();

    // Add a new item to the top of our stack. If the item is greater
    // than or equal to the last item in maxesStack, it's
    // the new max! So we'll add it to maxesStack.
    public void push(int item) {
        stack.push(item);
        if (maxesStack.empty() || item >= maxesStack.peek()) {
            maxesStack.push(item);
        }
    }

    // Remove and return the top item from our stack. If it equals
    // the top item in maxesStack, they must have been pushed in together.
    // So we'll pop it out of maxesStack too.
    public int pop() {
        int item = stack.pop();
        if (item == maxesStack.peek()) {
            maxesStack.pop();
        }
        return item;
    }

    // The last item in maxesStack is the max item in our stack.
    public int getMax() {
        return maxesStack.peek();
    }

    public static void main(String[] args) {
        MaxStack myStack = new MaxStack();

        myStack.push(1);
        myStack.push(32);
        myStack.push(15);
        myStack.push(2);
        myStack.push(1);
        myStack.push(99);
        myStack.push(20);

        System.out.println("Max Item in Stack: " + myStack.getMax());
    }

}
