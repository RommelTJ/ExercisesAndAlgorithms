package com.rommelrico.exercises.s7queuesandstacks.parenthesismatching;

/**
 * Solution
 *
 * We simply walk through the string, starting at our input opening parenthesis position. As we iterate, we keep a
 * count of how many additional "(" we find as openNestedParens. When we find a ")" we decrement openNestedParens. If
 * we find a ")" and openNestedParens is 0, we know that ")" closes our initial "(", so we return its position.
 *
 * Complexity
 *
 * O(n) time, where n is the number of chars in the string. O(1) space.
 *
 * What We Learned
 *
 * The trick to many "parsing" questions like this is using a stack to track which brackets/phrases/etc are "open" as
 * you go.
 *
 * So next time you get a parsing question, one of your first thoughts should be "use a stack!"
 *
 * In this problem, we can realize our stack would only hold '(' characters. So instead of storing each of those
 * characters in a stack, we can store the number of items our stack would be holding.
 *
 * That gets us from O(n) space to O(1) space.
 *
 * It's pretty cool when you can replace a whole data structure with a single integer :)
 *
 */
public class ParenthesisMatching {

    public static int getClosingParen(String sentence, int openingParenIndex) {
        int openNestedParens = 0;

        for (int position = openingParenIndex + 1; position < sentence.length(); position++) {
            char c = sentence.charAt(position);

            if (c == '(') {
                openNestedParens++;
            } else if (c == ')') {
                if (openNestedParens == 0) {
                    return position;
                } else {
                    openNestedParens--;
                }
            }
        }

        throw new IllegalArgumentException("No closing parenthesis :(");
    }

    public static void main(String[] args) {
        String input = "Sometimes (when I nest them (my parentheticals) too much (like this (and this))) they get confusing.";
        System.out.println("Corresponding closing parenthesis is at position: " + getClosingParen(input, 10));
    }

}
