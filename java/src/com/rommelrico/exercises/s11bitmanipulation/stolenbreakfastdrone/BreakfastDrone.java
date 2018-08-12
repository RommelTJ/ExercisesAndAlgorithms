package com.rommelrico.exercises.s11bitmanipulation.stolenbreakfastdrone;

/**
 * Solution
 *
 * We XOR all the integers in the array. We start with a variable uniqueDeliveryId set to 0. Every time we XOR with a
 * new ID, it will change the bits. When we XOR with the same ID again, it will cancel out the earlier change.
 *
 * In the end, we'll be left with the ID that appeared once!
 *
 * Complexity
 *
 * O(n) time, and O(1) space.
 *
 * What We Learned
 *
 * This problem is a useful reminder of the power we can unlock by knowing what's happening at the "bit level."
 *
 * How do you know when bit manipulation might be the key to solving a problem? Here are some signs to watch out for:
 * 1. You want to multiply or divide by 2 (use a left shift to multiply by 2, right shift to divide by 2).
 * 2. You want to "cancel out" matching numbers (use XOR).
 *
 */
public class BreakfastDrone {

    public static int findUniqueDeliveryId(int[] deliveryIds) {

        int uniqueDeliveryId = 0;

        for (int deliveryId: deliveryIds) {
            uniqueDeliveryId ^= deliveryId;
        }

        return uniqueDeliveryId;
    }

    public static void main(String[] args) {
        int[] deliveryIds = {1, 2, 3, 4, 5, 3, 2, 4, 1};
        System.out.println("Unique Delivery ID: " + findUniqueDeliveryId(deliveryIds));
    }

}
