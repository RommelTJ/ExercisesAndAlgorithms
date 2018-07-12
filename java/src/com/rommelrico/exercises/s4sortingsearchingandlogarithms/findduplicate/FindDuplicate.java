package com.rommelrico.exercises.s4sortingsearchingandlogarithms.findduplicate;

/**
 * Solution
 *
 * Our approach is similar to a binary search, except we divide the range of possible answers in half at each step,
 * rather than dividing the array in half.
 * 1. Find the number of integers in our input array which lie within the range 1..n/2.
 * 2. Compare that to the number of possible unique integers in the same range.
 * 3. If the number of actual integers is greater than the number of possible integers, we know there’s a duplicate
 *    in the range 1..n/2​​, so we iteratively use the same approach on that range.
 * 4. If the number of actual integers is not greater than the number of possible integers, we know there must be
 *    duplicate in the range n/2 + 1..n, so we iteratively use the same approach on that range.
 * 5. At some point, our range will contain just 1 integer, which will be our answer.
 *
 * Complexity
 *
 * O(1) space and O(n * lg n) time.
 *
 * What We Learned
 *
 * Our answer was a modified binary search. We got there by reasoning about the expected runtime:
 * 1. We started with an O(n^2)  "brute force" solution and wondered if we could do better.
 * 2. We knew to beat O(n^2) we'd probably do O(n) or O(n * lg n), so we started thinking of ways we might get an
 *    O(n * lg n) runtime.
 * 3. lg(n) usually comes from iteratively cutting stuff in half, so we arrived at the final algorithm by exploring
 *    that idea.
 *
 * Starting with a target runtime and working backward from there can be a powerful strategy for all kinds of coding
 * interview questions.
 *
 */
public class FindDuplicate {

    private static int findRepeat(int[] theArray) {

        int floor = 1;
        int ceiling = theArray.length - 1;

        while (floor < ceiling) {

            // divide our range 1..n into an upper range and lower range
            // (such that they don't overlap)
            // lower range is floor..midpoint
            // upper range is midpoint+1..ceiling
            int midpoint = floor + ((ceiling - floor) / 2);
            int lowerRangeFloor   = floor;
            int lowerRangeCeiling = midpoint;
            int upperRangeFloor   = midpoint + 1;
            int upperRangeCeiling = ceiling;

            // count number of items in lower range
            int itemsInLowerRange = 0;
            for (int item : theArray) {

                // is it in the lower range?
                if (item >= lowerRangeFloor && item <= lowerRangeCeiling) {
                    itemsInLowerRange += 1;
                }
            }

            int distinctPossibleIntegersInLowerRange = lowerRangeCeiling - lowerRangeFloor + 1;

            if (itemsInLowerRange > distinctPossibleIntegersInLowerRange) {

                // there must be a duplicate in the lower range
                // so use the same approach iteratively on that range
                floor   = lowerRangeFloor;
                ceiling = lowerRangeCeiling;
            } else {

                // there must be a duplicate in the upper range
                // so use the same approach iteratively on that range
                floor   = upperRangeFloor;
                ceiling = upperRangeCeiling;
            }
        }

        // floor and ceiling have converged
        // we found a number that repeats!
        return floor;
    }

    public static void main(String[] args) {
        int[] testArray1 = {1, 2, 3, 3, 4, 5};
        System.out.println("The Duplicate is: " + findRepeat(testArray1));

        int[] testArray2 = {1, 1, 5, 5, 5, 5};
        System.out.println("The Duplicate is: " + findRepeat(testArray2));
    }

}
