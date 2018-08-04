package com.rommelrico.exercises.s7queuesandstacks.bracketvalidator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
