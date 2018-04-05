package com.rommelrico.exercises.q4highestproductofthree;

/**
 * Solution
 *
 * We use a greedy approach to solve the problem in one pass. At each iteration
 * we keep track of:
 *
 *     highestProductOf3
 *     highestProductOf2
 *     highest
 *     lowestProductOf2
 *     lowest
 *
 * When we reach the end, the highestProductOf3 is our answer. We maintain the
 * others because they're necessary for keeping the highestProductOf3 up to
 * date as we walk through the array. At each iteration, the highestProductOf3
 * is the highest of:
 *
 *     the current highestProductOf3,
 *     current * highestProductOf2,
 *     current * lowestProductOf2 (if current and lowestProductOf2 are both
 *     low negative numbers, this product is a high positive number).
 *
 * Complexity
 *
 * O(n) time and O(1) additional space.
 *
 * What We Learned
 *
 * Greedy algorithms in action again!
 * That's not a coincidence—to illustrate how one pattern can be used to break
 * down several different questions, we're showing this one pattern in action
 * on several different questions.
 *
 * Usually it takes seeing an algorithmic idea from a few different angles for
 * it to really make intuitive sense.
 *
 * Our goal here is to teach you the right way of thinking to be able to break
 * down problems you haven't seen before. Greedy algorithm design is a big part
 * of that way of thinking.
 *
 * For this one, we built up our greedy algorithm exactly the same way we did
 * for the Apple stocks question. By asking ourselves:
 * "Suppose we could come up with the answer in one pass through the input, by
 * simply updating the 'best answer so far' as we went. What additional values
 * would we need to keep updated as we looked at each item in our set, in order
 * to be able to update the 'best answer so far' in constant time?"
 *
 * For the Apple stocks question, the only "additional value" we needed was the
 * min price so far.
 * For this one, we needed four things in order to calculate the new
 * highestProductOf3 at each step:
 *      highestProductOf2
 *      highest
 *      lowestProductOf2
 *      lowest
 * 
 */
public class HighestProductOfThree {
    private static int[] testArray = new int[] {1, 10, -5, 1, -100};

    private static int highestProductOf3(int[] arrayOfInts) {

        if (arrayOfInts.length < 3) {
            throw new IllegalArgumentException("Less than 3 items!");
        }

        // we're going to start at the 3rd item (at index 2)
        // so pre-populate highests and lowests based on the first 2 items.
        // we could also start these as null and check below if they're set
        // but this is arguably cleaner
        int highest = Math.max(arrayOfInts[0], arrayOfInts[1]);
        int lowest  = Math.min(arrayOfInts[0], arrayOfInts[1]);

        int highestProductOf2 = arrayOfInts[0] * arrayOfInts[1];
        int lowestProductOf2  = arrayOfInts[0] * arrayOfInts[1];

        // except this one--we pre-populate it for the first *3* items.
        // this means in our first pass it'll check against itself, which is fine.
        int highestProductOf3 = arrayOfInts[0] * arrayOfInts[1] * arrayOfInts[2];

        // walk through items, starting at index 2
        for (int i = 2; i < arrayOfInts.length; i++) {
            int current = arrayOfInts[i];

            // do we have a new highest product of 3?
            // it's either the current highest,
            // or the current times the highest product of two
            // or the current times the lowest product of two
            highestProductOf3 = Math.max(Math.max(
                    highestProductOf3,
                    current * highestProductOf2),
                    current * lowestProductOf2);

            // do we have a new highest product of two?
            highestProductOf2 = Math.max(Math.max(
                    highestProductOf2,
                    current * highest),
                    current * lowest);

            // do we have a new lowest product of two?
            lowestProductOf2 = Math.min(Math.min(
                    lowestProductOf2,
                    current * highest),
                    current * lowest);

            // do we have a new highest?
            highest = Math.max(highest, current);

            // do we have a new lowest?
            lowest = Math.min(lowest, current);
        }

        return highestProductOf3;
    }

    public static void main(String[] args) {
        System.out.println(highestProductOf3(testArray));
    }
}
