package com.rommelrico.exercises.q8temperaturetracker;

public class TempTracker {

    // for mode
    private int[] occurrences = new int[111];  // array of 0s at indices 0..110
    private int maxOccurrences;
    private int mode;

    // for mean
    private int numberOfReadings;
    private long totalSum;
    private double mean;

    // for min and max
    private int minTemp = Integer.MAX_VALUE;
    private int maxTemp = Integer.MIN_VALUE;

    public void insert(int temperature) {

        // for mode
        occurrences[temperature]++;
        if (occurrences[temperature] > maxOccurrences) {
            mode = temperature;
            maxOccurrences = occurrences[temperature];
        }

        // for mean
        numberOfReadings++;
        totalSum += temperature;
        mean = (double) totalSum / numberOfReadings;

        // for min and max
        minTemp = Math.min(minTemp, temperature);
        maxTemp = Math.max(maxTemp, temperature);
    }

    public int getMax() {
        return maxTemp;
    }

    public int getMin() {
        return minTemp;
    }

    public double getMean() {
        return mean;
    }

    public int getMode() {
        return mode;
    }

    public static void main(String[] args) {
        TempTracker tracker = new TempTracker();
        System.out.println("Max: " + tracker.getMax());
        System.out.println("Min: " + tracker.getMin());
        System.out.println("Mean: " + tracker.getMean());
        System.out.println("Mode: " + tracker.getMode());
        System.out.println("--------");


        tracker.insert(32);
        tracker.insert(41);
        tracker.insert(43);
        tracker.insert(56);
        tracker.insert(73);
        tracker.insert(78);
        tracker.insert(73);
        tracker.insert(32);
        tracker.insert(73);

        System.out.println("Max: " + tracker.getMax());
        System.out.println("Min: " + tracker.getMin());
        System.out.println("Mean: " + tracker.getMean());
        System.out.println("Mode: " + tracker.getMode());
        System.out.println("--------");
    }

}
