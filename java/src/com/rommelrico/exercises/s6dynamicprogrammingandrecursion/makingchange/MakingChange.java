package com.rommelrico.exercises.s6dynamicprogrammingandrecursion.makingchange;

/**
 * Solution
 *
 * We use a bottom-up algorithm to build up a table waysOfDoingNCents such that waysOfDoingNCents[k] is how many ways
 * we can get to k cents using our denominations. We start with the base case that there's one way to create the amount
 * zero, and progressively add each of our denominations.
 *
 * The number of new ways we can make a higherAmount when we account for a new coin is simply
 * waysOfDoingNCents[higherAmount - coin], where we know that value already includes combinations involving coin
 * (because we went bottom-up, we know smaller values have already been calculated).
 *
 * Complexity
 *
 * O(n ∗ m) time and O(n) additional space, where n is the amount of money and m is the number of potential
 * denominations.
 *
 * What We Learned
 *
 * This question is in a broad class called "dynamic programming." We have a bunch more dynamic programming questions
 * we'll go over later.
 *
 * Dynamic programming is kind of like the next step up from greedy. You're taking that idea of "keeping track of what
 * we need in order to update the best answer so far," and applying it to situations where the new best answer so far
 * might not just have to do with the previous answer, but some other earlier answer as well.
 *
 * So as you can see in this problem, we kept track of all of our previous answers to smaller versions of the problem
 * (called "subproblems") in a big array called waysOfDoingNCents.
 *
 * Again, same idea of keeping track of what we need in order to update the answer as we go, like we did when storing
 * the max product of 2, min product of 2, etc in the highest product of 3 question. Except now the thing we need to
 * keep track of is all our previous answers, which we're keeping in an array.
 *
 * We built that array bottom-up, but we also talked about how we could do it top-down and memoize. Going bottom-up is
 * cleaner and usually more efficient, but often it's easier to think of the top-down version first and try to adapt
 * from there.
 *
 * Dynamic programming is a weak point for lots of candidates. If this one was tricky for you, don't fret. We have more
 * coming later.
 * 
 */
public class MakingChange {

    private static int amount = 4;
    private static int[] denominations = new int[]{1, 2, 3};

    public static int changePossibilitiesBottomUp(int amount, int[] denominations) {
        int[] waysOfDoingNCents = new int[amount + 1];  // array of zeros from 0..amount
        waysOfDoingNCents[0] = 1;

        for (int coin : denominations) {
            for (int higherAmount = coin; higherAmount <= amount; higherAmount++) {
                int higherAmountRemainder = higherAmount - coin;
                waysOfDoingNCents[higherAmount] += waysOfDoingNCents[higherAmountRemainder];
            }
        }

        return waysOfDoingNCents[amount];
    }

    public static void main(String[] args) {
        int out = changePossibilitiesBottomUp(amount, denominations);
        System.out.println("changePossibilitiesBottomUp: " + out);
    }

}
