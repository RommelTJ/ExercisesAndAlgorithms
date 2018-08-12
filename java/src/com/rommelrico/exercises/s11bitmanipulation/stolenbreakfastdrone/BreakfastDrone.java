package com.rommelrico.exercises.s11bitmanipulation.stolenbreakfastdrone;

public class BreakfastDrone {

    public static int findUniqueDeliveryId(int[] deliveryIds) {

        int uniqueDeliveryId = 0;

        for (int deliveryId: deliveryIds) {
            uniqueDeliveryId ^= deliveryId;
        }

        return uniqueDeliveryId;
    }

}
