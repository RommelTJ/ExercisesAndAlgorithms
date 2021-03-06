package com.rommelrico.exercises.s6dynamicprogrammingandrecursion.fibonacci;

/**
 * Solution
 *
 * We use a bottom-up approach, starting with the 0th Fibonacci number and iteratively computing subsequent numbers
 * until we get to n.
 *
 * Complexity
 *
 * O(n) time and O(1) space.
 *
 * What We Learned
 *
 * This one's a good illustration of the tradeoff we sometimes have between code cleanliness and efficiency.
 *
 * We could use a cute, recursive method to solve the problem. But that would cost O(2^n) time as opposed to n time in
 * our final bottom-up solution. Massive difference!
 *
 * In general, whenever you have a recursive solution to a problem, think about what's actually happening on the call
 * stack. An iterative solution might be more efficient.
 *
 */
public class ComputeNthFibonacci {

    public static int fib(int n) {

        // edge cases:
        if (n < 0) {
            throw new IllegalArgumentException("Index was negative. No such thing as a negative index in a series.");
        } else if (n == 0 || n == 1) {
            return n;
        }

        // we'll be building the fibonacci series from the bottom up
        // so we'll need to track the previous 2 numbers at each step
        int prevPrev = 0;  // 0th fibonacci
        int prev = 1;      // 1st fibonacci
        int current = 0;   // Declare and initialize current

        for (int i = 1; i < n; i++) {

            // Iteration 1: current = 2nd fibonacci
            // Iteration 2: current = 3rd fibonacci
            // Iteration 3: current = 4th fibonacci
            // To get nth fibonacci ... do n-1 iterations.
            current = prev + prevPrev;
            prevPrev = prev;
            prev = current;
        }

        return current;
    }

    public static void main(String[] args) {
        System.out.println("FIB(5) -> " + fib(0));
        System.out.println("FIB(5) -> " + fib(5));
        System.out.println("FIB(5) -> " + fib(6));
    }

}
