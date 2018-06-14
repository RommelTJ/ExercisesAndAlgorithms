### Breakdown

We could simply concatenate (join together) the two arrays into one, then sort the result:
```
public static int[] mergeSortedArrays(int[] myArray, int[] alicesArray) {
    int[] mergedArray = Arrays.copyOf(myArray, myArray.length + alicesArray.length);
    System.arraycopy(alicesArray, 0, mergedArray, myArray.length, alicesArray.length);
    Arrays.sort(mergedArray);
    return mergedArray;
}
```

What would the time cost be? 

### Hint 1

O(n lg n), where n is the total length of our output array (the sum of the lengths of our inputs).

We can do better. With this algorithm, we're not really taking advantage of the fact that the 
input arrays are themselves already sorted. How can we save time by using this fact? 

### Hint 2

A good general strategy for thinking about an algorithm is to try writing out a sample input and 
performing the operation by hand. If you're stuck, try that!

### Hint 3

Since our arrays are sorted, we know they each have their smallest item in the 0th index. So the 
smallest item overall is in the 0th index of one of our input arrays!

Which 0th element is it? Whichever is smaller!

To start, let's just write a method that chooses the 0th element for our sorted array.

### Hint 4

```
public static int[] mergeArrays(int[] myArray, int[] alicesArray) {

    // make an array big enough to fit the elements from both arrays
    int[] mergedArray = new int[myArray.length + alicesArray.length];

    int headOfMyArray = myArray[0];
    int headOfAlicesArray = alicesArray[0];

    // case: 0th comes from my array
    if (headOfMyArray < headOfAlicesArray) {
        mergedArray[0] = headOfMyArray;

    // case: 0th comes from Alice's array
    } else {
        mergedArray[0] = headOfAlicesArray;
    }

    // eventually we'll want to return the merged array
    return mergedArray;
}
```

Okay, good start! That works for finding the 0th element. Now how do we choose the next element? 

### Hint 5

Let's look at a sample input:

```
[3,  4,  6, 10, 11, 15]  // myArray
[1,  5,  8, 12, 14, 19]  // alicesArray
```

To start we took the 0th element from alicesArray and put it in the 0th slot in the output array:

```
[3,  4,  6, 10, 11, 15]  // myArray
[1,  5,  8, 12, 14, 19]  // alicesArray
[1,  x,  x,  x,  x,  x]  // mergedArray
```

We need to make sure we don't try to put that 1 in mergedArray again. We should mark it as "already merged" somehow. For now, we can just cross it out:

```
[3,  4,  6, 10, 11, 15]  // myArray
[x,  5,  8, 12, 14, 19]  // alicesArray
[1,  x,  x,  x,  x,  x]  // mergedArray
```

Or we could even imagine it's removed from the array:

```
[3,  4,  6, 10, 11, 15]  // myArray
[5,  8, 12, 14, 19]      // alicesArray
[1,  x,  x,  x,  x,  x]  // mergedArray
```

Now to get our next element we can use the same approach we used to get the 0th element — it's the 
smallest of the earliest unmerged elements in either array! In other words, it's the smaller of 
the leftmost elements in either array, assuming we've removed the elements we've already merged in.

So in general we could say something like:
1. We'll start at the beginnings of our input arrays, since the smallest elements will be there.
2. As we put items in our final mergedArray, we'll keep track of the fact that they're "already merged."
3. At each step, each array has a first "not-yet-merged" item.
4. At each step, the next item to put in the mergedArray is the smaller of those two "not-yet-merged" items!

Can you implement this in code? 

### Hint 6

```
public static int[] mergeArrays(int[] myArray, int[] alicesArray) {

    int[] mergedArray = new int[myArray.length + alicesArray.length];

    int currentIndexAlices = 0;
    int currentIndexMine   = 0;
    int currentIndexMerged = 0;

    while (currentIndexMerged < mergedArray.length) {
        int firstUnmergedAlices = alicesArray[currentIndexAlices];
        int firstUnmergedMine = myArray[currentIndexMine];

        // case: next comes from my array
        if (firstUnmergedMine < firstUnmergedAlices) {
            mergedArray[currentIndexMerged] = firstUnmergedMine;
            currentIndexMine++;

        // case: next comes from Alice's array
        } else {
            mergedArray[currentIndexMerged] = firstUnmergedAlices;
            currentIndexAlices++;
        }

        currentIndexMerged++;
    }

    return mergedArray;
}
```

Okay, this algorithm makes sense. To wrap up, we should think about edge cases and check for bugs. 
What edge cases should we worry about? 

### Hint 7

Here are some edge cases:

1. One or both of our input arrays is 0 elements or 1 element
2. One of our input arrays is longer than the other.
3. One of our arrays runs out of elements before we're done merging.

Actually, (3) will always happen. In the process of merging our arrays, we'll certainly exhaust 
one before we exhaust the other.

Does our method handle these cases correctly?

### Hint 8

We'll get an ArrayIndexOutOfBoundsException in all three cases!

How can we fix this?

### Hint 9

We can probably solve these cases at the same time. They're not so different — they just have to 
do with handling empty arrays.

To start, we could treat each of our arrays being out of elements as a separate case to handle, 
in addition to the 2 cases we already have. So we have 4 cases total. Can you code that up?

Be sure you check the cases in the right order!

### Hint 10

```
public static int[] mergeArrays(int[] myArray, int[] alicesArray) {

    int[] mergedArray = new int[myArray.length + alicesArray.length];

    int currentIndexAlices = 0;
    int currentIndexMine   = 0;
    int currentIndexMerged = 0;

    while (currentIndexMerged < mergedArray.length) {

        // case: my array is exhausted
        if (currentIndexMine >= myArray.length) {
            mergedArray[currentIndexMerged] = alicesArray[currentIndexAlices];
            currentIndexAlices++;

        // case: Alice's array is exhausted
        } else if (currentIndexAlices >= alicesArray.length) {
            mergedArray[currentIndexMerged] = myArray[currentIndexMine];
            currentIndexMine++;

        // case: my item is next
        } else if (myArray[currentIndexMine] < alicesArray[currentIndexAlices]) {
            mergedArray[currentIndexMerged] = myArray[currentIndexMine];
            currentIndexMine++;

        // case: Alice's item is next
        } else {
            mergedArray[currentIndexMerged] = alicesArray[currentIndexAlices];
            currentIndexAlices++;
        }

        currentIndexMerged++;
    }

    return mergedArray;
}
```

Cool. This'll work, but it's a bit repetitive. We have these two lines twice:

```
mergedArray[currentIndexMerged] = myArray[currentIndexMine];
currentIndexMine++;
```

Same for these two lines:

```
mergedArray[currentIndexMerged] = alicesArray[currentIndexAlices];
currentIndexAlices++;
```

That's not DRY. Maybe we can avoid repeating ourselves by bringing our code back down to just 2 
cases.

See if you can do this in just one "if else" by combining the conditionals. 

### Hint 11

You might try to simply squish the middle cases together:

```
if (isAlicesArrayExhausted || (myArray[currentIndexMine] < alicesArray[currentIndexAlices])) {
    mergedArray[currentIndexMerged] = myArray[currentIndexMine];
    currentIndexMine++;
}
```

But what happens when myArray is exhausted? 

### Hint 12

We'll get an ArrayIndexOutOfBoundsException when we try to access myArray[currentIndexMine]!

How can we fix this?

