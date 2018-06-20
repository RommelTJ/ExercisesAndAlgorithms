package com.rommelrico.exercises.s2hashingandhashtables.permutationpalindrome;

import java.util.Set;
import java.util.HashSet;

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
