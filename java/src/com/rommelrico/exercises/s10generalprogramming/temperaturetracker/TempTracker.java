package com.rommelrico.exercises.s10generalprogramming.temperaturetracker;

/**
 * Solution
 *
 * We maintain the maxTemp, minTemp, mean, and mode as temperatures are inserted, so that each getter method simply
 * returns an instance variable.
 *
 * To maintain the mean at each insert, we track the numberOfReadings and the totalSum of numbers inserted so far.
 *
 * To maintain the mode at each insert, we track the total occurrences of each number, as well as the maxOccurrences
 * we've seen so far.
 *
 * Complexity
 *
 * O(1) time for each method, and O(1) space related to input! (Our occurrences array's size is bounded by our range
 * of possible temps, in this case 0-110)
 *
 * What We Learned
 *
 * This question brings up a common design decision: whether to do work just-in-time or ahead-of-time.
 *
 * Our first thought for this question might have been to use a just-in-time approach: have our insert() method simply
 * put the temperature in an array, and then have our getters compute e.g. the mode just-in-time, at the moment the
 * getter is called.
 *
 * Instead, we used an ahead-of-time approach: have our insert method compute and store our mean, mode, max, and min
 * ahead of time (that is, before they're asked for). So our getter just returns the precomputed value in O(1) time.
 *
 * In this case, the ahead-of-time approach doesn't just speed up our getters...it also reduces our space cost. If we
 * tried to compute each metric just-in-time, we'd need to store all of the temperatures as they come in, taking O(n)
 * space for n insert()s.
 *
 * As an added bonus, the ahead-of-time approach didn't increase our asymptotic time cost for inserts, even though we
 * added more work. With some cleverness (channeling some greedy thinking to figure out what we needed to store in
 * order to update the answer in O(1) time), we were able to keep it at O(1) time.
 *
 * It doesn't always happen this way. Sometimes there are trade-offs between just-in-time and ahead-of-time. Sometimes
 * to save time in our getters, we have to spend more time in our insert.
 *
 * In those cases, whether we should prefer a just-in-time approach or an ahead-of-time approach is a nuanced question.
 * Ultimately it comes down to your usage patterns. Do you expect to get more inserts than gets? Do slow inserts have
 * a stronger negative effect on users than slow gets?
 *
 * Whenever you're designing a data structure with inserts and getters, think about the advantages and disadvantages
 * of a just-in-time approach vs an ahead-of-time approach.
 *
 */
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
