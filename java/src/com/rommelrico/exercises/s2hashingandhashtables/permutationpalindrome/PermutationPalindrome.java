package com.rommelrico.exercises.s2hashingandhashtables.permutationpalindrome;

import java.util.Set;
import java.util.HashSet;

/**
 * Solution
 *
 * Our approach is to check that each character appears an even number of times, allowing for only one character to
 * appear an odd number of times (a middle character). This is enough to determine if a permutation of the input string
 * is a palindrome.
 *
 * We iterate through each character in the input string, keeping track of the characters we've seen an odd number of
 * times using a hash set unpairedCharacters.
 *
 * As we iterate through the characters in the input string:
 * - If the character is not in unpairedCharacters, we add it.
 * - If the character is already in unpairedCharacters, we remove it.
 *
 * Finally, we just need to check if less than two characters don’t have a pair.
 *
 * Complexity
 *
 * O(n) time, and O(n) space.
 *
 * What We Learned
 *
 * One of the tricks was to use a hash map or hash set.
 *
 * This is the most common way to get from a brute force approach to something more efficient. Especially for easier
 * problems.
 *
 * I even know interviewers who just want to hear you say "hash map", and once they hear that they'll say, "Great,
 * let's move on."
 *
 * So always ask yourself, right from the start: "Can I save time by using a hash map?"
 *
 */
public class PermutationPalindrome {

    private static boolean hasPalindromePermutation(String theString) {

        // track characters we've seen an odd number of times
        Set<Character> unpairedCharacters = new HashSet<>();

        for (char c : theString.toCharArray()) {
            if (unpairedCharacters.contains(c)) {
                unpairedCharacters.remove(c);
            } else {
                unpairedCharacters.add(c);
            }
        }

        // the string has a palindrome permutation if it
        // has one or zero characters without a pair
        return unpairedCharacters.size() <= 1;
    }

    public static void main(String[] args) {
        String test1 = "civic";
        String test2 = "ivicc";
        String test3 = "civil";
        String test4 = "livci";

        System.out.println(test1 + " should return: " + hasPalindromePermutation(test1));
        System.out.println(test2 + " should return: " + hasPalindromePermutation(test2));
        System.out.println(test3 + " should return: " + hasPalindromePermutation(test3));
        System.out.println(test4 + " should return: " + hasPalindromePermutation(test4));
    }

}
