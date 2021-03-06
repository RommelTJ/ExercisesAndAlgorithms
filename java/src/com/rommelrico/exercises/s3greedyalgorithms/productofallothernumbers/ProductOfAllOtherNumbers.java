package com.rommelrico.exercises.s3greedyalgorithms.productofallothernumbers;

/**
 * Solution
 *
 * To find the products of all the integers except the integer at each index, we'll go through our array greedily
 * twice. First we get the products of all the integers before each index, and then we go backwards to get the products
 * of all the integers after each index.
 *
 * When we multiply all the products before and after each index, we get our answer—the products of all the integers
 * except the integer at each index!
 *
 * Complexity
 *
 * O(n) time and O(n) space. We make two passes through our input an array, and the array we build always has the same
 * length as the input array.
 *
 * What We Learned
 *
 * Another question using a greedy approach. The tricky thing about this one: we couldn't actually solve it in one
 * pass. But we could solve it in two passes!
 *
 * This approach probably wouldn't have been obvious if we had started off trying to use a greedy approach.
 *
 * Instead, we started off by coming up with a slow (but correct) brute force solution and trying to improve from
 * there. We looked at what our solution actually calculated, step by step, and found some repeat work. Our final
 * answer came from brainstorming ways to avoid doing that repeat work.
 *
 * So that's a pattern that can be applied to other problems:
 * Start with a brute force solution, look for repeat work in that solution, and modify it to only do that work once.
 */
public class ProductOfAllOtherNumbers {

    private static int[] testArray = new int[] {1, 7, 3, 4};

    private static int[] getProductsOfAllIntsExceptAtIndex(int[] intArray) {

        if (intArray.length < 2) {
            throw new IllegalArgumentException("Getting the product of numbers at other indices requires at least 2 numbers");
        }

        // we make an array with the length of the input array to
        // hold our products
        int[] productsOfAllIntsExceptAtIndex = new int[intArray.length];

        // for each integer, we find the product of all the integers
        // before it, storing the total product so far each time
        int productSoFar = 1;
        for (int i = 0; i < intArray.length; i++) {
            productsOfAllIntsExceptAtIndex[i] = productSoFar;
            productSoFar *= intArray[i];
        }

        // for each integer, we find the product of all the integers
        // after it. since each index in products already has the
        // product of all the integers before it, now we're storing
        // the total product of all other integers
        productSoFar = 1;
        for (int i = intArray.length - 1; i >= 0; i--) {
            productsOfAllIntsExceptAtIndex[i] *= productSoFar;
            productSoFar *= intArray[i];
        }

        return productsOfAllIntsExceptAtIndex;
    }

    public static void main(String[] args) {
        for (int product : getProductsOfAllIntsExceptAtIndex(testArray)) {
            System.out.println(product);
        }
    }
}
