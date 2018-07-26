package com.rommelrico.exercises.s6dynamicprogrammingandrecursion.cakethief;

/**
 * Solution
 *
 * This is a classic computer science puzzle called "the unbounded knapsack problem."
 *
 * We use a bottom-up approach to find the max value at our duffel bag's weightCapacity by finding the max value at
 * every capacity from 0 to weightCapacity.
 *
 * We allocate an array maxValuesAtCapacities where the indices are capacities and each value is the max value at that
 * capacity.
 *
 * For each capacity, we want to know the max monetary value we can carry. To figure that out, we go through each cake,
 * checking to see if we should take that cake.
 *
 * The best monetary value we can get if we take a given cake is simply:
 * 1. that cake's value, plus
 * 2. the best monetary value we can carry in our remaining duffel bag capacity after taking the cake - which we'll
 *    already have stored in maxValuesAtCapacities.
 *
 * To handle weights and values of zero, we throw an infinity error only if a cake weighs nothing and has a positive
 * value.
 *
 * Complexity
 *
 * O(n*k) time, and O(k) space, where n is number of types of cake and k is the capacity of the duffel bag.
 *
 * What We Learned
 *
 * This question is our spin on the famous "unbounded knapsack problem" — a classic dynamic programming question.
 *
 */
public class CakeThief {

    public static long maxDuffelBagValue(CakeType[] cakeTypes, int weightCapacity) {

        // we make an array to hold the maximum possible value at every
        // duffel bag weight capacity from 0 to weightCapacity
        // starting each index with value 0
        long[] maxValuesAtCapacities = new long[weightCapacity + 1];

        for (int currentCapacity = 0; currentCapacity <= weightCapacity; currentCapacity++) {

            // set a variable to hold the max monetary value so far for currentCapacity
            long currentMaxValue = 0;

            for (CakeType cakeType : cakeTypes) {

                // if a cake weighs 0 and has a positive value the value of our duffel bag is infinite!
                if (cakeType.weight == 0 && cakeType.value != 0) {
                    throw new InfinityException();
                }

                // if the current cake weighs as much or less than the current weight capacity
                // it's possible taking the cake would get a better value
                if (cakeType.weight <= currentCapacity) {

                    // so we check: should we use the cake or not?
                    // if we use the cake, the most kilograms we can include in addition to the cake
                    // we're adding is the current capacity minus the cake's weight. we find the max
                    // value at that integer capacity in our array maxValuesAtCapacities
                    long maxValueUsingCake = cakeType.value
                            + maxValuesAtCapacities[currentCapacity - cakeType.weight];

                    // now we see if it's worth taking the cake. how does the
                    // value with the cake compare to the currentMaxValue?
                    currentMaxValue = Math.max(maxValueUsingCake, currentMaxValue);
                }
            }

            // add each capacity's max value to our array so we can use them
            // when calculating all the remaining capacities
            maxValuesAtCapacities[currentCapacity] = currentMaxValue;
        }

        return maxValuesAtCapacities[weightCapacity];
    }

    public static void main(String[] args) {
        CakeType[] cakeTypes = new CakeType[] {
                new CakeType(7, 160),
                new CakeType(3, 90),
                new CakeType(2, 15),
        };

        int capacity = 20;
        long result = maxDuffelBagValue(cakeTypes, capacity);
        System.out.println("maxDuffelBagValue(cakeTypes, 20): " + result);
    }

}
