package com.rommelrico.exercises.s12combinatronicsprobabiblityandothermath.simulatesevensideddie;

import java.util.SplittableRandom;

/**
 * Solution
 *
 * Because rand5() has only 5 possible outcomes, and we need 7 possible results for rand7(), we need to call rand5()
 * at least twice.
 *
 * When we call rand5() twice, we have 5 * 5 = 25 possible outcomes. If each of our 7 possible results has an equal
 * chance of occurring, we'll need each outcome to occur in our set of possible outcomes the same number of times. So
 * our total number of possible outcomes must be divisible by 7.
 *
 * 25 isn't evenly divisible by 7, but 21 is. So when we get one of the last 4 possible outcomes, we throw it out and
 * roll again.
 *
 * So we roll twice and translate the result into a unique outcomeNumber in the range 1..25. If the outcomeNumber is
 * greater than 21, we throw it out and re-roll. If not, we mod by 7 (and add 1).
 *
 * Complexity
 *
 * Worst-case O(∞) time (we might keep re-rolling forever) and O(1) space.
 *
 * What We Learned
 *
 * The requirement to "return each integer with equal probability" is a real sticking point. Lots of candidates come up
 * with clever O(1)-time solutions that they are so sure about. But their solutions aren't actually uniform (in other
 * words, they're not truly random). In fact, it's impossible to have true randomness and non-infinite worst-case
 * runtime.
 *
 */
public class SimulateSevenSidedDie {

    public static int rand5() {
        return new SplittableRandom().nextInt(1, 6);
    }

    public static int rand7() {

        while (true) {

            // do our die rolls
            int roll1 = rand5();
            int roll2 = rand5();

            int outcomeNumber = (roll1-1) * 5 + (roll2-1) + 1;

            // if we hit an extraneous
            // outcome we just re-roll
            if (outcomeNumber > 21) continue;

            // our outcome was fine. return it!
            return outcomeNumber % 7 + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(rand7());
        System.out.println(rand7());
        System.out.println(rand7());
        System.out.println(rand7());
    }

}
