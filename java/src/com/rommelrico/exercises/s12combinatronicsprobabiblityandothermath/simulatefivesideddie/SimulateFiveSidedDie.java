package com.rommelrico.exercises.s12combinatronicsprobabiblityandothermath.simulatefivesideddie;

import java.util.SplittableRandom;

public class SimulateFiveSidedDie {

    public static int rand7() {
        return new SplittableRandom().nextInt(1, 8);
    }

    public static int rand5() {
        int result = 7;  // arbitrarily large
        while (result > 5) {
            result = rand7();
        }
        return result;
    }
    
}
