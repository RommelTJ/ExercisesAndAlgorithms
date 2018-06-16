package com.rommelrico.exercises.s4sortingsearchingandlogarithms.findrotationpoint;

/**
 * Solution
 *
 * This is a modified version of binary search. At each iteration, we go right if the item we're looking at is greater
 * than the first item and we go left if the item we're looking at is less than the first item.
 *
 * We keep track of the lower and upper bounds on the rotation point, calling them floorIndex and ceilingIndex
 * (initially we called them "floor" and "ceiling," but because we didn't imply the type in the name we got confused
 * and created bugs). When floorIndex and ceilingIndex are directly next to each other, we know the floor is the last
 * item we added before starting from the beginning of the dictionary, and the ceiling is the first item we added after.
 *
 * Complexity
 *
 * O(lg n) time and O(1) space.
 *
 * What We Learned
 *
 * The answer was a modified version of binary search.
 *
 * This is a great example of the difference between "knowing" something and knowing something. You might have seen
 * binary search before, but that doesn't help you much unless you've learned the lessons of binary search.
 *
 * Binary search teaches us that when an array is sorted or mostly sorted:
 * 1. The value at a given index tells us a lot about what's to the left and what's to the right.
 * 2. We don't have to look at every item in the array. By inspecting the middle item, we can "rule out" half of the
 *    array.
 * 3. We can use this approach over and over, cutting the problem in half until we have the answer. This is sometimes
 *    called "divide and conquer."
 *
 * So whenever you know an array is sorted or almost sorted, think about these lessons from binary search and see if
 * they apply.
 *
 */
public class FindRotationPoint {

    private static int findRotationPoint(String[] words) {
        final String firstWord = words[0];

        int floorIndex = 0;
        int ceilingIndex = words.length - 1;

        while (floorIndex < ceilingIndex) {

            // guess a point halfway between floor and ceiling
            int guessIndex = floorIndex + ((ceilingIndex - floorIndex) / 2);

            // if guess comes after first word or is the first word
            if (words[guessIndex].compareTo(firstWord) >= 0) {
                // go right
                floorIndex = guessIndex;
            } else {
                // go left
                ceilingIndex = guessIndex;
            }

            // if floor and ceiling have converged
            if (floorIndex + 1 == ceilingIndex) {

                // between floor and ceiling is where we flipped to the beginning
                // so ceiling is alphabetically first
                break;
            }
        }

        return ceilingIndex;
    }

    public static void main(String[] args) {
        String[] words = new String[]{
                "ptolemaic",
                "retrograde",
                "supplant",
                "undulate",
                "xenoepist",
                "asymptote",  // <-- rotates here!
                "babka",
                "banoffee",
                "engender",
                "karpatka",
                "othellolagkage",
        };
        System.out.println("Rotation Point Index: " + findRotationPoint(words));
    }

}
