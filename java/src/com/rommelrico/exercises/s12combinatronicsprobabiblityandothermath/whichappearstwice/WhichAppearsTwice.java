package com.rommelrico.exercises.s12combinatronicsprobabiblityandothermath.whichappearstwice;

/**
 * Solution
 *
 * First, we sum all numbers 1..n. We can do this using the equation: (n^2 + n) / 2.
 * because the numbers in 1..n are a triangular series.
 *
 * Second, we sum all numbers in our input array, which should be the same as our other sum but with our repeat number
 * added in twice. So the difference between these two sums is the repeated number!
 *
 * Complexity
 *
 * O(n) time and O(1) space.
 *
 */
public class WhichAppearsTwice {

    public static int findRepeat(int[] numbers) {

        if (numbers.length < 2) {
            throw new IllegalArgumentException("Finding duplicate requires at least two numbers");
        }

        int n = numbers.length - 1;

        int sumWithoutDuplicate = (n * n + n) / 2;

        int actualSum = 0;
        for (int number : numbers) {
            actualSum += number;
        }

        return actualSum - sumWithoutDuplicate;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 3, 2, 5, 3, 4};
        System.out.println("Which appears twice? " + findRepeat(numbers) +"! ");
    }

}
