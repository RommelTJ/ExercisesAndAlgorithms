package com.rommelrico.exercises.s2hashingandhashtables.topscores;

public class TopScores {

    public static int[] sortScores(int[] unorderedScores, int highestPossibleScore) {

        // array of 0s at indices 0..highestPossibleScore
        int[] scoreCounts = new int[highestPossibleScore + 1];

        // populate scoreCounts
        for (int score : unorderedScores) {
            scoreCounts[score]++;
        }

        // populate the final sorted array
        int[] sortedScores = new int[unorderedScores.length];
        int currentSortedIndex = 0;

        // for each item in scoreCounts
        for (int score = highestPossibleScore; score >= 0; score--) {
            int count = scoreCounts[score];

            // for the number of times the item occurs
            for (int occurrence = 0; occurrence < count; occurrence++) {

                // add it to the sorted array
                sortedScores[currentSortedIndex] = score;
                currentSortedIndex++;
            }
        }

        return sortedScores;
    }

}

