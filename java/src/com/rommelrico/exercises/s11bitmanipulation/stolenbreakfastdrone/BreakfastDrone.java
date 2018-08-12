package com.rommelrico.exercises.s11bitmanipulation.stolenbreakfastdrone;

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
