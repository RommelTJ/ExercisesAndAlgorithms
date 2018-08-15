package com.rommelrico.exercises.s12combinatronicsprobabiblityandothermath.inplaceshuffle;

import java.util.Arrays;
import java.util.Random;

/**
 * Solution
 *
 * We choose a random item to move to the first index, then we choose a random
 * other item to move to the second index, etc. We "place" an item in an index
 * by swapping it with the item currently at that index.
 *
 * Crucially, once an item is placed at an index it can't be moved. So for the
 * first index we choose from nnn items, for the second index we choose from
 * n − 1 items, etc.
 *
 * Complexity
 *
 * O(n) time and O(1) space.
 *
 * What We Learned
 *
 * Don't worry, most interviewers won't expect a candidate to know the
 * Fisher-Yates shuffle algorithm. Instead, they'll be looking for the
 * problem-solving skills to derive the algorithm, perhaps with a couple hints
 * along the way.
 *
 * They may also be looking for an understanding of why the naive solution is
 * non-uniform (some outcomes are more likely than others). If you had trouble
 * with that part, try walking through it again.
 *
 */
public class InPlaceShuffle {

    private static Random rand = new Random();

    private static int getRandom(int floor, int ceiling) {
        return rand.nextInt((ceiling - floor) + 1) + floor;
    }

    public static void shuffle(int[] theArray) {

        // if it's 1 or 0 items, just return
        if (theArray.length <= 1) {
            return;
        }

        // walk through from beginning to end
        for (int indexWeAreChoosingFor = 0;
             indexWeAreChoosingFor < theArray.length - 1; indexWeAreChoosingFor++) {

            // choose a random not-yet-placed item to place there
            // (could also be the item currently in that spot)
            // must be an item AFTER the current item, because the stuff
            // before has all already been placed
            int randomChoiceIndex = getRandom(indexWeAreChoosingFor, theArray.length - 1);

            // place our random choice in the spot by swapping
            if (randomChoiceIndex != indexWeAreChoosingFor) {
                int valueAtIndexWeChoseFor = theArray[indexWeAreChoosingFor];
                theArray[indexWeAreChoosingFor] = theArray[randomChoiceIndex];
                theArray[randomChoiceIndex] = valueAtIndexWeChoseFor;
            }
        }
    }

    public static void main(String[] args) {
        int[] someArray = {1, 4, 1, 8, 9, 10, 42, 2};
        System.out.println("Pre-Shuffle array: " + Arrays.toString(someArray));
        shuffle(someArray);
        System.out.println("Shuffled array: " + Arrays.toString(someArray));
    }
}
