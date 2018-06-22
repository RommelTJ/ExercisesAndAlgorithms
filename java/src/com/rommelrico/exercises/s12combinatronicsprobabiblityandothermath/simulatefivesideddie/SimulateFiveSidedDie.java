package com.rommelrico.exercises.s12combinatronicsprobabiblityandothermath.simulatefivesideddie;

import java.util.SplittableRandom;

public class SimulateFiveSidedDie {

    public static int rand7() {
        return new SplittableRandom().nextInt(1, 8);
    }

}
