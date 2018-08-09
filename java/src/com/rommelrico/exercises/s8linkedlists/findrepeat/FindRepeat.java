package com.rommelrico.exercises.s8linkedlists.findrepeat;

/**
 * Solution
 *
 * We treat the input array as a linked list like we described in the prompt.
 *
 * To find a duplicate integer:
 * 1. We know the position of a node with multiple incoming pointers is a duplicate in our array because the nodes that
 *    pointed to it must have the same value.
 * 2. We find a node with multiple incoming pointers by finding the first node in a cycle.
 * 3. We find the first node in a cycle by finding the length of the cycle and advancing two pointers: one starting at
 *    the head of the linked list, and the other starting ahead as many steps as there are nodes in the cycle. The
 *    pointers will meet at the first node in the cycle.
 * 4. We find the length of a cycle by remembering a position inside the cycle and counting the number of steps it
 *    takes to get back to that position.
 * 5. We get inside a cycle by starting at the head and walking nnn steps. We know the head of the list is at position
 *    n+1.
 *
 * We want to think of our array as a linked list but we don't want to actually use up all that space, so we traverse
 * our array as if it were a linked list by converting positions to indices.
 *
 * Complexity
 *
 * O(n) time and O(1) space.
 *
 * What We Learned
 *
 * This one's pretty crazy. It's hard to imagine an interviewer expecting you to get all the way through this question
 * without help.
 *
 * But just because it takes a few hints to get to the answer doesn't mean a question is "too hard." Some interviewers
 * expect they'll have to offer a few hints.
 *
 * So if you get a hint in an interview, just relax and listen. The most impressive thing you can do is drop what
 * you're doing, fully understand the hint, and then run with it.
 *
 */
public class FindRepeat {

    public static int findRepeat(int[] intArray) {

        final int n = intArray.length - 1;

        // STEP 1: GET INSIDE A CYCLE
        // start at position n+1 and walk n steps to
        // find a position guaranteed to be in a cycle
        int positionInCycle = n + 1;
        for (int i = 0; i < n; i++) {

            // we subtract 1 from the current position to step ahead:
            // the 2nd *position* in an array is *index* 1
            positionInCycle = intArray[positionInCycle - 1];
        }

        // STEP 2: FIND THE LENGTH OF THE CYCLE
        // find the length of the cycle by remembering a position in the cycle
        // and counting the steps it takes to get back to that position
        int rememberedPositionInCycle = positionInCycle;
        int currentPositionInCycle = intArray[positionInCycle - 1];  // 1 step ahead
        int cycleStepCount = 1;

        while (currentPositionInCycle != rememberedPositionInCycle) {
            currentPositionInCycle = intArray[currentPositionInCycle - 1];
            cycleStepCount += 1;
        }

        // STEP 3: FIND THE FIRST NODE OF THE CYCLE
        // start two pointers
        //   (1) at position n+1
        //   (2) ahead of position n+1 as many steps as the cycle's length
        int pointerStart = n + 1;
        int pointerAhead = n + 1;
        for (int i = 0; i < cycleStepCount; i++) {
            pointerAhead = intArray[pointerAhead - 1];
        }

        // advance until the pointers are in the same position
        // which is the first node in the cycle
        while (pointerStart != pointerAhead) {
            pointerStart = intArray[pointerStart - 1];
            pointerAhead = intArray[pointerAhead - 1];
        }

        // since there are multiple values pointing to the first node
        // in the cycle, its position is a duplicate in our array
        return pointerStart;
    }

    public static void main(String[] args) {
        int[] testArray1 = {1, 2, 3, 3, 4, 5};
        System.out.println("The Duplicate is: " + findRepeat(testArray1));

        int[] testArray2 = {1, 1, 5, 5, 5, 5};
        System.out.println("The Duplicate is: " + findRepeat(testArray2));
    }
}
