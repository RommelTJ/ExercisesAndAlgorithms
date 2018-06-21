package com.rommelrico.exercises.s1arraysandstringmanipulation.reversewordsinplace;

/**
 * Solution
 *
 * We'll write a helper method reverseCharacters() that reverses all the characters between a leftIndex and rightIndex.
 * We use it to:
 * 1. Reverse all the characters in the entire message, giving us the correct word order but with each word backwards.
 * 2. Reverse the characters in each individual word.
 *
 * Complexity
 *
 * O(n) time and O(1) space!
 *
 * What We Learned
 *
 * The naive solution of reversing the words one at a time had a worst-case O(n^2) runtime. That's because swapping
 * words with different lengths required "scooting over" all the other characters to make room.
 *
 * To get around this "scooting over" issue, we reversed all the characters in the message first. This put all the
 * words in the correct spots, but with the characters in each word backwards. So to get the final answer, we reversed
 * the characters in each word. This all takes two passes through the message, so O(n) time total.
 *
 * This might seem like a blind insight, but we derived it by using a clear strategy:
 * Solve a simpler version of the problem (in this case, reversing the characters instead of the words), and see if
 * that gets us closer to a solution for the original problem.
 * We talk about this strategy in the "get unstuck" section of our coding interview tips.
 *
 */
public class ReverseWordsInPlace {

    public static void reverseWords(char[] message) {

        // first we reverse all the characters in the entire message array
        // this gives us the right word order
        // but with each word backwards
        reverseCharacters(message, 0, message.length - 1);

        // now we'll make the words forward again
        // by reversing each word's characters

        // we hold the index of the *start* of the current word
        // as we look for the *end* of the current word
        int currentWordStartIndex = 0;
        for (int i = 0; i <= message.length; i++) {

            // found the end of the current word!
            if (i == message.length || message[i] == ' ') {

                // if we haven't exhausted the array, our
                // next word's start is one character ahead
                reverseCharacters(message, currentWordStartIndex, i - 1);
                currentWordStartIndex = i + 1;
            }
        }
    }

    private static void reverseCharacters(char[] message, int leftIndex, int rightIndex) {

        // walk towards the middle, from both sides
        while (leftIndex < rightIndex) {

            // swap the left char and right char
            char temp = message[leftIndex];
            message[leftIndex] = message[rightIndex];
            message[rightIndex] = temp;
            leftIndex++;
            rightIndex--;
        }
    }


    public static void main(String[] args) {
        char[] message = { 't', 'h', 'e', ' ',
                'e', 'a', 'g', 'l', 'e', ' ',
                'h', 'a', 's', ' ',
                'l', 'a', 'n', 'd', 'e', 'd' };

        System.out.println(String.valueOf(message));
        reverseWords(message);
        System.out.println(String.valueOf(message));
    }
}
