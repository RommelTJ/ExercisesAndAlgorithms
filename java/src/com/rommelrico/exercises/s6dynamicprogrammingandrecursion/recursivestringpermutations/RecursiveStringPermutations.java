package com.rommelrico.exercises.s6dynamicprogrammingandrecursion.recursivestringpermutations;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Solution
 *
 * If we're making all permutations for "cat," we take all permutations for "ca" and then put "t" in each possible
 * position in each of those permutations. We use this approach recursively.
 *
 * Complexity
 *
 * O(n^2) time.
 *
 * What We Learned
 *
 * This is one where playing with a sample input is huge. Sometimes it helps to think of algorithm design as a
 * two-part process: first figure out how you would solve the problem "by hand," as though the input was a stack of
 * paper on a desk in front of you. Then translate that process into code.
 *
 */
public class RecursiveStringPermutations {

    private static Set<String> getPermutations(String inputString) {

        // base case
        if (inputString.length() <= 1) {
            return new HashSet<>(Collections.singletonList(inputString));
        }

        String allCharsExceptLast = inputString.substring(0, inputString.length() - 1);
        char lastChar = inputString.charAt(inputString.length() - 1);

        // recursive call: get all possible permutations for all chars except last
        Set<String> permutationsOfAllCharsExceptLast = getPermutations(allCharsExceptLast);

        // put the last char in all possible positions for each of the above permutations
        Set<String> permutations = new HashSet<>();
        for (String permutationOfAllCharsExceptLast : permutationsOfAllCharsExceptLast) {
            for (int position = 0; position <= allCharsExceptLast.length(); position++) {
                String permutation = permutationOfAllCharsExceptLast.substring(0, position) + lastChar
                        + permutationOfAllCharsExceptLast.substring(position);
                permutations.add(permutation);
            }
        }

        return permutations;
    }

    public static void main(String[] args) {
        String cats = "cats";
        Set<String> permutations = getPermutations(cats);
        for (String item: permutations) {
            System.out.println(item);
        }
    }

}
