package com.rommelrico.exercises.s1arraysandstringmanipulation.cardshuffle;

import java.util.Arrays;

public class CardShuffle {
    public static void main(String[] args) {
        int half1index=0, half2index=0;

        int[] deckOfCards = new int[52];
        for (int i=0; i<52; i++) {
            deckOfCards[i] = i+1;
        }
        System.out.println("Deck of Cards: " + Arrays.toString(deckOfCards));
        System.out.println("Shuffled Deck of Cards: " + Arrays.toString(shuffledCards(deckOfCards)));
    }

    private static int[] shuffledCards(int[] deckOfCards) {
        int[] shuffledDeck = new int[deckOfCards.length];

        int[] half1 = new int[deckOfCards.length/2];
        int[] half2 = new int[deckOfCards.length/2];
        for (int i = 0, j=0, k=0; i<deckOfCards.length; i++) {
            if (i<deckOfCards.length/2) {
                half1[j] = deckOfCards[i];
                j++;
            } else {
                half2[k] = deckOfCards[i];
                k++;
            }
        }

        for (int i=0, j=0, k=0; i<deckOfCards.length; i++) {
            if (i%2 == 0) {
                shuffledDeck[i] = half1[j];
                j++;
            } else {
                shuffledDeck[i] = half2[k];
                k++;
            }
        }

        return shuffledDeck;
    }
}
