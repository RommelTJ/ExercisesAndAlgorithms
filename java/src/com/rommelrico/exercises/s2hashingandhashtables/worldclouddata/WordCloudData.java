package com.rommelrico.exercises.s2hashingandhashtables.worldclouddata;

import java.util.HashMap;
import java.util.Map;

/**
 * Solution
 *
 * In our solution, we make three decisions:
 * 1. We use a class. This allows us to tie our methods together, calling them on instances of our class instead of
 *    passing references.
 * 2. To handle duplicate words with different cases, we choose to make a word uppercase in our hash map only if it
 *    is always uppercase in the original string. While this is a reasonable approach, it is imperfect (consider
 *    proper nouns that are also lowercase words, like "Bill" and "bill").
 * 3. We build our own splitWords() method instead of using a built-in one. This allows us to pass each word to
 *    our addWordToHashMap() method as it was split, and to split words and eliminate punctuation in one iteration.
 *
 * To split the words in the input string and populate a hash map of the unique words to the number of times they
 * occurred, we:
 * 1. Split words by spaces, em dashes, and ellipses — making sure to include hyphens surrounded by characters. We
 *    also include all apostrophes (which will handle contractions nicely but will break possessives into separate
 *    words).
 * 2. Populate the words in our hash map as they are identified, checking if the word is already in our hash map in
 *    its current case or another case.
 *
 * If the input word is uppercase and there's a lowercase version in the hash map, we increment the lowercase
 * version's count. If the input word is lowercase and there's an uppercase version in the hash map, we "demote" the
 * uppercase version by adding the lowercase version and giving it the uppercase version's count.
 *
 * Complexity
 *
 * O(n) time, and O(n) space.
 *
 * What We Learned
 *
 * To handle capitalized words, there were lots of heuristics and approaches we could have used, each with their own
 * strengths and weaknesses. Open-ended questions like this can really separate good engineers from great engineers.
 *
 * Good engineers will come up with a solution, but great engineers will come up with several solutions, weigh them
 * carefully, and choose the best solution for the given context. So as you're running practice questions, challenge
 * yourself to keep thinking even after you have a first solution. See how many solutions you can come up with. This
 * will grow your ability to quickly see multiple ways to solve a problem, so you can figure out the best solution.
 *
 */
public class WordCloudData {

    private Map<String, Integer> wordsToCounts = new HashMap<>();

    public WordCloudData(String inputString) {
        populateWordsToCounts(inputString);
    }

    public Map<String, Integer> getWordsToCounts() {
        return wordsToCounts;
    }

    private void populateWordsToCounts(String inputString) {
        // iterates over each character in the input string, splitting
        // words and passing them to addWordToHashMap()

        int currentWordStartIndex = 0;
        int currentWordLength = 0;

        for (int i = 0; i < inputString.length(); i++) {
            char character = inputString.charAt(i);

            // if we reached the end of the string we check if the last
            // character is a letter and add the last word to our hash map
            if (i == inputString.length() - 1) {
                if (Character.isLetter(character)) {
                    currentWordLength++;
                }
                if (currentWordLength > 0) {
                    String currentWord = inputString.substring(currentWordStartIndex,
                            currentWordStartIndex + currentWordLength);
                    addWordToHashMap(currentWord);
                }

                // if we reach a space or emdash we know we're at the end of a word
                // so we add it to our hash map and reset our current word
            } else if (character == ' ' || character == '\u2014') {
                if (currentWordLength > 0) {
                    String currentWord = inputString.substring(currentWordStartIndex,
                            currentWordStartIndex + currentWordLength);
                    addWordToHashMap(currentWord);
                    currentWordLength = 0;
                }

                // we want to make sure we split on ellipses so if we get two periods in
                // a row we add the current word to our hash map and reset our current word
            } else if (character == '.') {
                if (i < inputString.length() - 1 && inputString.charAt(i + 1) == '.') {
                    if (currentWordLength > 0) {
                        String currentWord = inputString.substring(currentWordStartIndex,
                                currentWordStartIndex + currentWordLength);
                        addWordToHashMap(currentWord);
                        currentWordLength = 0;
                    }
                }

                // if the character is a letter or an apostrophe, we add it to our current word
            } else if (Character.isLetter(character) || character == '\'') {
                if (currentWordLength == 0) {
                    currentWordStartIndex = i;
                }
                currentWordLength++;

                // if the character is a hyphen, we want to check if it's surrounded by letters
                // if it is, we add it to our current word
            } else if (character == '-') {
                if (i > 0 && Character.isLetter(inputString.charAt(i - 1))
                        && Character.isLetter(inputString.charAt(i + 1))) {
                    if (currentWordLength == 0) {
                        currentWordStartIndex = i;
                    }
                    currentWordLength++;
                } else {
                    if (currentWordLength > 0) {
                        String currentWord = inputString.substring(currentWordStartIndex,
                                currentWordStartIndex + currentWordLength);
                        addWordToHashMap(currentWord);
                        currentWordLength = 0;
                    }
                }
            }
        }
    }

    private void addWordToHashMap(String word) {

        // if the word is already in the hash map we increment its count
        if (wordsToCounts.containsKey(word)) {
            wordsToCounts.put(word, wordsToCounts.get(word) + 1);

            // if a lowercase version is in the hash map, we know our input word must be uppercase
            // but we only include uppercase words if they're always uppercase
            // so we just increment the lowercase version's count
        } else if (wordsToCounts.containsKey(word.toLowerCase())) {
            int newCount = wordsToCounts.get(word.toLowerCase()) + 1;
            wordsToCounts.put(word.toLowerCase(), newCount);

            // if an uppercase version is in the hash map, we know our input word must be lowercase.
            // since we only include uppercase words if they're always uppercase, we add the
            // lowercase version and give it the uppercase version's count
        } else if (wordsToCounts.containsKey(capitalize(word))) {
            int newCount = wordsToCounts.get(capitalize(word)) + 1;
            wordsToCounts.put(word, newCount);
            wordsToCounts.remove(capitalize(word));

            // otherwise, the word is not in the hash map at all, lowercase or uppercase
            // so we add it to the hash map
        } else {
            wordsToCounts.put(word, 1);
        }
    }

    private String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    public static void main(String[] args) {
        String test = "After beating the eggs, Dana read the next step: "
                + "Add milk and eggs, then add flour and sugar.";

        WordCloudData wordCloudData = new WordCloudData(test);
        System.out.println("wordsToCounts map: " + wordCloudData.getWordsToCounts().toString());
    }
}
