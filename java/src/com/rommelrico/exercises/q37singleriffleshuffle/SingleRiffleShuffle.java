package com.rommelrico.exercises.q37singleriffleshuffle;

/**
 * Solution
 *
 *  We walk through shuffledDeck, seeing if each card so far could have come
 *  from a riffle of the other two halves. To check this, we:
 *
 *  1. Keep pointers to the current index in half1, half2, and shuffledDeck.
 *  2. Walk through shuffledDeck from beginning to end.
 *  3. If the current card in shuffledDeck is the same as the top card from
 *      half1, increment half1Index and keep going. This can be thought of as
 *      "throwing out" the top cards of half1 and shuffledDeck, reducing the
 *      problem to the remaining cards in the stacks.
 *  4. Same as above with half2.
 *  5. If the current card isn't the same as the card at the top of half1 or
 *      half2, we know we don't have a single riffle.
 *  6. If we make it all the way to the end of shuffledDeck and each card
 *      checks out, we know we do have a single riffle.
 *
 * Complexity
 *
 * O(n) time and O(1) additional space.
 *
 * What We Learned
 *
 * If you read the whole breakdown section, you might have noticed that our
 * recursive function cost us extra space. If you missed that part, go back
 * and take a look.
 *
 * Be careful of the hidden space costs from a recursive function's call stack!
 * If you have a solution that's recursive, see if you can save space by using
 * an iterative algorithm instead.
 *
 */
public class SingleRiffleShuffle {

    public static boolean isSingleRiffle(int[] half1, int[] half2, int[] shuffledDeck) {
        int half1Index = 0;
        int half2Index = 0;
        int half1MaxIndex = half1.length - 1;
        int half2MaxIndex = half2.length - 1;

        for (int card : shuffledDeck) {

            // if we still have cards in half1
            // and the "top" card in half1 is the same
            // as the top card in shuffledDeck
            if (half1Index <= half1MaxIndex
                    && card == half1[half1Index]) {
                half1Index++;

                // if we still have cards in half2
                // and the "top" card in half2 is the same
                // as the top card in shuffledDeck
            } else if (half2Index <= half2MaxIndex
                    && card == half2[half2Index]) {
                half2Index++;

                // if the top card in shuffledDeck doesn't match the top
                // card in half1 or half2, this isn't a single riffle.
            } else {
                return false;
            }
        }

        // all cards in shuffledDeck have been "accounted for"
        // so this is a single riffle!
        return true;
    }

    public static void main(String[] args) {
        int[] half1 = {};
        int[] half2 = {};
        int[] shuffledDeck = {};

        System.out.println("Is Single-Riffle deck? "
                + isSingleRiffle(half1, half2, shuffledDeck));
    }

}
