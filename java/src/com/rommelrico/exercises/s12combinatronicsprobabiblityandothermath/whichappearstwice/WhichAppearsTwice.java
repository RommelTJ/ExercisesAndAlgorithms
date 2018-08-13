package com.rommelrico.exercises.s12combinatronicsprobabiblityandothermath.whichappearstwice;

public class WhichAppearsTwice {

    public static int findRepeat(int[] numbers) {

        if (numbers.length < 2) {
            throw new IllegalArgumentException("Finding duplicate requires at least two numbers");
        }

        int n = numbers.length - 1;

        int sumWithoutDuplicate = (n * n + n) / 2;

        int actualSum = 0;
        for (int number : numbers) {
            actualSum += number;
        }

        return actualSum - sumWithoutDuplicate;
    }
    
}
