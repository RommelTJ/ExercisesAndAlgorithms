### Merge Sorted Arrays

In order to win the prize for most cookies sold, my friend Alice and I are going to merge our 
Girl Scout Cookies orders and enter as one unit.

Each order is represented by an "order id" (an integer).

We have our lists of orders sorted numerically already, in arrays. Write a method to merge our 
arrays of orders into one sorted array.

For example:
```
int[] myArray = new int[]{3, 4, 6, 10, 11, 15};
int[] alicesArray = new int[]{1, 5, 8, 12, 14, 19};

System.out.println(Arrays.toString(mergeArrays(myArray, alicesArray)));
// prints [1, 3, 4, 5, 6, 8, 10, 11, 12, 14, 15, 19]
```
