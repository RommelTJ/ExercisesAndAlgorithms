package com.rommelrico.exercises.s1arraysandstringmanipulation.mergesortedarrays;

import java.util.Arrays;

/**
 * Solution
 *
 * First, we allocate our answer array, getting its size by adding the size of myArray and alicesArray.
 *
 * We keep track of a current index in myArray, a current index in alicesArray, and a current index in mergedArray. So
 * at each step, there's a "current item" in alicesArray and in myArray. The smaller of those is the next one we add to
 * the mergedArray!
 *
 * But careful: we also need to account for the case where we exhaust one of our arrays and there are still elements in
 * the other. To handle this, we say that the current item in myArray is the next item to add to mergedArray only if
 * myArray is not exhausted AND, either:
 * 1. alicesArray is exhausted, or
 * 2. the current item in myArray is less than the current item in alicesArray
 *
 * Complexity
 *
 * O(n) time and O(n) additional space, where n is the number of items in the merged array.
 *
 * The added space comes from allocating the mergedArray. There's no way to do this " in-place" because neither of our
 * input arrays are necessarily big enough to hold the merged array.
 *
 * But if our inputs were linked lists, we could avoid allocating a new structure and do the merge by simply adjusting
 * the next pointers in the list nodes!
 *
 * In our implementation above, we could avoid tracking currentIndexMerged and just compute it on the fly by adding
 * currentIndexMine and currentIndexAlices. This would only save us one integer of space though, which is hardly
 * anything. It's probably not worth the added code complexity.
 *
 * What We Learned
 *
 * We spent a lot of time figuring out how to cleanly handle edge cases.
 *
 * Sometimes it's easy to lose steam at the end of a coding interview when you're debugging. But keep sprinting through
 * to the finish! Think about edge cases. Look for off-by-one errors.
 *
 */
public class MergeSortedArrays {

    private static int[] mergeArrays(int[] myArray, int[] alicesArray) {

        // set up our mergedArray
        int[] mergedArray = new int[myArray.length + alicesArray.length];

        int currentIndexAlices = 0;
        int currentIndexMine   = 0;
        int currentIndexMerged = 0;

        while (currentIndexMerged < mergedArray.length) {

            boolean isMyArrayExhausted = currentIndexMine >= myArray.length;
            boolean isAlicesArrayExhausted = currentIndexAlices >= alicesArray.length;

            // case: next comes from my array
            // my array must not be exhausted, and EITHER:
            // 1) Alice's array IS exhausted, or
            // 2) the current element in my array is less
            //    than the current element in Alice's array
            if (!isMyArrayExhausted && (isAlicesArrayExhausted
                    || (myArray[currentIndexMine] < alicesArray[currentIndexAlices]))) {

                mergedArray[currentIndexMerged] = myArray[currentIndexMine];
                currentIndexMine++;

                // case: next comes from Alice's array
            } else {
                mergedArray[currentIndexMerged] = alicesArray[currentIndexAlices];
                currentIndexAlices++;
            }

            currentIndexMerged++;
        }

        return mergedArray;
    }

    public static void main(String[] args) {
        int[] myArray = new int[]{3, 4, 6, 10, 11, 15};
        int[] alicesArray = new int[]{1, 5, 8, 12, 14, 19};
        System.out.println(Arrays.toString(mergeArrays(myArray, alicesArray)));
    }
}
