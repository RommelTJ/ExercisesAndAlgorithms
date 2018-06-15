package com.rommelrico.exercises.s2hashingandhashtables.inflightentertainment;

import java.util.HashSet;
import java.util.Set;

/**
 * Solution
 *
 *  We make one pass through movieLengths, treating each item as the firstMovieLength. At each iteration, we:
 *  1. See if there's a matchingSecondMovieLength we've seen already (stored in our movieLengthsSeen hash set) that is
 *     equal to flightLength - firstMovieLength. If there is, we short-circuit and return true.
 *  2. Keep our movieLengthsSeen hash set up to date by throwing in the current firstMovieLength.
 *
 * Complexity
 *
 * O(n) time, and O(n) space. Note while optimizing runtime we added a bit of space cost.
 *
 * What We Learned
 *
 * The trick was to use a hash set to access our movies by length, in O(1) time.
 *
 * Using hash-based data structures, like hash maps or hash sets, is so common in coding challenge solutions, it should
 * always be your first thought. Always ask yourself, right from the start: "Can I save time by using a hash map?"
 *
 */
public class InflightEntertainment {

    private static boolean canTwoMoviesFillFlight(int[] movieLengths, int flightLength) {

        // movie lengths we've seen so far
        Set<Integer> movieLengthsSeen = new HashSet<>();

        for (int firstMovieLength : movieLengths) {

            int matchingSecondMovieLength = flightLength - firstMovieLength;
            if (movieLengthsSeen.contains(matchingSecondMovieLength)) {
                return true;
            }

            movieLengthsSeen.add(firstMovieLength);
        }

        // we never found a match, so return false
        return false;
    }

    public static void main(String[] args) {
        int flightLength = 236;
        int[] movieLengths = {120, 116, 124, 118, 90, 98};
        System.out.println("Can Two Movies Fill Flight: " + canTwoMoviesFillFlight(movieLengths, flightLength));
    }
}
