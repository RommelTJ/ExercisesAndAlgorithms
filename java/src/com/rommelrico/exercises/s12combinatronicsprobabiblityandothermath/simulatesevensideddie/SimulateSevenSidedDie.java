package com.rommelrico.exercises.s12combinatronicsprobabiblityandothermath.simulatesevensideddie;

import java.util.SplittableRandom;

public class SimulateSevenSidedDie {

    public static int rand5() {
        return new SplittableRandom().nextInt(1, 6);
    }

    public static int rand7() {

        while (true) {

            // do our die rolls
            int roll1 = rand5();
            int roll2 = rand5();

            int outcomeNumber = (roll1-1) * 5 + (roll2-1) + 1;

            // if we hit an extraneous
            // outcome we just re-roll
            if (outcomeNumber > 21) continue;

            // our outcome was fine. return it!
            return outcomeNumber % 7 + 1;
        }
    }

    public static void main(String[] args) {

    }

}
