package com.rommelrico.exercises.s12combinatronicsprobabiblityandothermath.simulatefivesideddie;

import java.util.SplittableRandom;

/**
 * Solution
 *
 * We simply "re-roll" whenever we get a number greater than 5.
 *
 * Complexity
 *
 * Worst-case O(∞) time (we might keep re-rolling forever) and O(1) space.
 *
 * What We Learned
 *
 * Making sure each possible result has the same probability is a big part of what makes this one tricky.
 *
 * If you're ever struggling with the math to figure something like that out, don't be afraid to just count. As in,
 * write out all the possible results from rand7(), and label each one with its final outcome for rand5(). Then count
 * up how many ways there are to make each final outcome. If the counts aren't the same, the method isn't uniformly
 * random.
 *
 */
public class SimulateFiveSidedDie {

    public static int rand7() {
        return new SplittableRandom().nextInt(1, 8);
    }

    public static int rand5() {
        int result = 7;  // arbitrarily large
        while (result > 5) {
            result = rand7();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(rand5());
        System.out.println(rand5());
        System.out.println(rand5());
        System.out.println(rand5());
        System.out.println(rand5());
    }
}
