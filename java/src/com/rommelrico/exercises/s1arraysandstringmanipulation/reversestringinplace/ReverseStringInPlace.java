package com.rommelrico.exercises.s1arraysandstringmanipulation.reversestringinplace;

public class ReverseStringInPlace {

    private static void reverse(char[] arrayOfChars) {

        int leftIndex = 0;
        int rightIndex = arrayOfChars.length - 1;

        while (leftIndex < rightIndex) {

            // swap characters
            char temp = arrayOfChars[leftIndex];
            arrayOfChars[leftIndex] = arrayOfChars[rightIndex];
            arrayOfChars[rightIndex] = temp;

            // move towards middle
            leftIndex++;
            rightIndex--;
        }
    }

    public static void main(String[] args) {
        char[] testArray = {'a', 'b', 'c'};
        System.out.println("Original Array: " + testArray);
        reverse(testArray);
        System.out.println("Reversed Array: " + testArray);
    }

}
