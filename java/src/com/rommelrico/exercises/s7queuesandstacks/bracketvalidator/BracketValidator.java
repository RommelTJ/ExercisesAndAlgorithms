package com.rommelrico.exercises.s7queuesandstacks.bracketvalidator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Solution
 *
 * We iterate through our string, making sure that:
 * 1. each closer corresponds to the most recently seen, unclosed opener
 * 2. every opener and closer is in a pair
 *
 * We use a stack to keep track of the most recently seen, unclosed opener. And if the stack is ever empty when we come
 * to a closer, we know that closer doesn't have an opener.
 *
 * So as we iterate:
 * 1. If we see an opener, we push it onto the stack.
 * 2. If we see a closer, we check to see if it is the closer for the opener at the top of the stack. If it is, we pop
 *    from the stack. If it isn't, or if the stack is empty, we return false.
 *
 * If we finish iterating and our stack is empty, we know every opener was properly closed.
 *
 * Complexity
 *
 * O(n) time (one iteration through the string), and O(n) space.
 *
 * What We Learned
 *
 * The trick was to use a stack.
 * It might have been difficult to have that insight, because you might not use stacks that much.
 *
 * Two common uses for stacks are:
 * 1. parsing (like in this problem)
 * 2. tree or graph traversal (like depth-first traversal)
 *
 * So remember, if you're doing either of those things, try using a stack!
 *
 */
public class BracketValidator {

    public static boolean isValid(String code) {

        Map<Character, Character> openersToClosers = new HashMap<>();
        openersToClosers.put('(', ')');
        openersToClosers.put('[', ']');
        openersToClosers.put('{', '}');

        Set<Character> openers = openersToClosers.keySet();
        Set<Character> closers = new HashSet<>(openersToClosers.values());

        Deque<Character> openersStack = new ArrayDeque<>();

        for (char c : code.toCharArray()) {
            if (openers.contains(c)) {
                openersStack.push(c);
            } else if (closers.contains(c)) {
                if (openersStack.isEmpty()) {
                    return false;
                } else {
                    char lastUnclosedOpener = openersStack.pop();

                    // if this closer doesn't correspond to the most recently
                    // seen unclosed opener, short-circuit, returning false
                    if (openersToClosers.get(lastUnclosedOpener) != c) {
                        return false;
                    }
                }
            }
        }
        return openersStack.isEmpty();
    }

    public static void main(String[] args) {
        String input1 = "{ [ ] ( ) }";
        String input2 = "{ [ ( ] ) }";
        String input3 = "{ [ }";
        String input4 = "sdg";
        String input5 = "";

        System.out.println("input1 is valid? " + isValid(input1));
        System.out.println("input2 is valid? " + isValid(input2));
        System.out.println("input3 is valid? " + isValid(input3));
        System.out.println("input4 is valid? " + isValid(input4));
        System.out.println("input5 is valid? " + isValid(input5));
    }

}
