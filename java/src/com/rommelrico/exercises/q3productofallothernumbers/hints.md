### Breakdown

A brute force approach would use two loops to multiply the integer at every index by the integer at every nestedIndex, 
unless index == nestedIndex.

This would give us a runtime of O(n^2). Can we do better?

### Hint 1

Well, we’re wasting a lot of time doing the same calculations. As an example, let's take:

```java
// input array
[1, 2, 6, 5, 9]

// array of the products of all integers
// except the integer at each index:
[540, 270, 90, 108, 60]  // [2 * 6 * 5 * 9,  1 * 6 * 5 * 9,  1 * 2 * 5 * 9,  1 * 2 * 6 * 9,  1 * 2 * 6 * 5]
```

We're doing some of the same multiplications two or three times!  
`[2*6*5*9, 1*6*5*9, 1*2*5*9, 1*2*6*9, 1*2*6*5]`, we're calculating 5*9 three times: at indices 0, 1, and 2.

Or look at this pattern:
`[2*6*5*9, 1*6*5*9, 1*2*5*9, 1*2*6*9, 1*2*6*5]`, we have 1 in index 1, and we calculate 1*2 at index 2, 
1*2*6 at index 3, and 1*2*6*5 at index 4.

We’re redoing multiplications when instead we could be storing the results! This would be a great time to use a greedy 
approach. We could store the results of each multiplication highlighted in blue, then just multiply by one new integer 
each time.

So in the last highlighted multiplication, for example, we wouldn't have to multiply 1∗2∗6 again. If we stored that 
value (12) from the previous multiplication, we could just multiply 12∗5.

Can we break our problem down into subproblems so we can use a greedy approach?

### Hint 2

Let's look back at the last example:  
`[2*6*5*9, 1*6*5*9, 1*2*5*9, 1*2*6*9, 1*2*6*5]`, we have 1 in index 1, and we calculate 1*2 at index 2, 
1*2*6 at index 3, and 1*2*6*5 at index 4.

What do all the highlighted multiplications have in common?

### Hint 3

They are all the integers that are before each index in the input array ([1,2,6,5,9]). For example, the highlighted 
multiplication at index 3 (1∗2∗6) is all the integers before index 3 in the input array. 

`[1, 2, 6, 5, 9]`  
`[2*6*5*9, 1*6*5*9, 1*2*5*9, 1*2*6*9, 1*2*6*5]`

Do all the multiplications that aren't highlighted have anything in common?

### Hint 4

Yes, they're all the integers that are after each index in the input array!

Knowing this, can we break down our original problem to use a greedy approach?

### Hint 5

The product of all the integers except the integer at each index can be broken down into two pieces:

1. The product of all the integers before each index, and
2. The product of all the integers after each index.

To start, let's just get the product of all the integers before each index.

### Hint 6

How can we do this? Let's take another example:

```java
// input array
[3, 1, 2, 5, 6, 4]

// multiplication of all integers before each index
// (we give index 0 a value of 1 since it has no integers before it)
[1, 3,  3 * 1,  3 * 1 * 2,  3 * 1 * 2 * 5,  3 * 1 * 2 * 5 * 6]

// final array of the products of all the integers before each index
[1, 3, 3, 6, 30, 180]
```

Notice that we're always adding one new integer to our multiplication for each index! 

### Hint 7

So to get the products of all the integers before each index, we could greedily store each product so far and multiply 
that by the next integer. Then we can store that new product so far and keep going.

So how can we apply this to our input array?

### Hint 8

Let’s make an array productsOfAllIntsBeforeIndex:

```java
int[] productsOfAllIntsBeforeIndex = new int[intArray.length];

// for each integer, find the product of all the integers
// before it, storing the total product so far each time
int productSoFar = 1;
for (int i = 0; i < intArray.length; i++) {
    productsOfAllIntsBeforeIndex[i] = productSoFar;
    productSoFar *= intArray[i];
}
```

### Hint 9

So we solved the subproblem of finding the products of all the integers before each index. Now, how can we find the 
products of all the integers after each index?

### Hint 10

It might be tempting to make a new array of all the values in our input array in reverse, and just use the same method 
we used to find the products before each index.

Is this the best way?

### Hint 11

This method will work, but:

1. We'll need to make a whole new array that's basically the same as our input array. That's another O(n)O(n)O(n) 
memory cost!
2. To keep our indices aligned with the original input array, we'd have to reverse the array of products we return. 
That's two reversals, or two O(n)O(n)O(n) operations!

Is there a cleaner way to get the products of all the integers after each index?

### Hint 12

We can just walk through our array backwards! So instead of reversing the values of the array, we'll just reverse the 
indices we use to iterate!

```java
int[] productsOfAllIntsAfterIndex = new int[intArray.length];

int productSoFar = 1;
for (int i = intArray.length - 1; i >= 0; i--) {
    productsOfAllIntsAfterIndex[i] = productSoFar;
    productSoFar *= intArray[i];
}
```

Now we've got productsOfAllIntsAfterIndex, but we’re starting to build a lot of new arrays. And we still need our final 
array of the total products. How can we save space? 

### Hint 13

Let’s take a step back. Right now we’ll need three arrays:

1. productsOfAllIntsBeforeIndex,
2. productsOfAllIntsAfterIndex, and
3. productsOfAllIntsExceptAtIndex.

To get the first one, we keep track of the total product so far going forwards, and to get the second one, we keep 
track of the total product so far going backwards. How do we get the third one?

### Hint 14

Well, we want the product of all the integers before an index and the product of all the integers after an index. We 
just need to multiply every integer in productsOfAllIntsBeforeIndex with the integer at the same index in 
productsOfAllIntsAfterIndex!

Let's take an example. Say our input array is `[2,4,10]`:

We'll calculate productsOfAllIntsBeforeIndex as:  
input array `[2, 4, 10]`,   
product of all the numbers before each index `[1, 2, 8]`  

And we'll calculate productsOfAllIntsAfterIndex as:  
input array `[2, 4, 10]`,   
product of all the numbers after each index `[40, 10, 1]`

If we take these arrays and multiply the integers at the same indices, we get:  
products before each index `[1, 2, 8]`,  
products after each index `[40, 10, 1]`,  
product of all other integers `[40, 20, 8]`

And this gives us what we're looking for—the products of all the integers except the integer at each index.

Knowing this, can we eliminate any of the arrays to reduce the memory we use?

### Hint 15

Yes, instead of building the second array productsOfAllIntsAfterIndex, we could take the product we would have stored 
and just multiply it by the matching integer in productsOfAllIntsBeforeIndex!

So in our example above, when we calculated our first (well, "0th") "product after index" (which is 40), we’d just 
multiply that by our first "product before index" (1) instead of storing it in a new array.

How many arrays do we need now?

### Hint 16

Just one! We create an array, populate it with the products of all the integers before each index, and then multiply 
those products with the products after each index to get our final result!

productsOfAllIntsBeforeIndex now contains the products of all the integers before and after every index, so we can call 
it productsOfAllIntsExceptAtIndex!

### Hint 17

Almost done! Are there any edge cases we should test?

### Hint 18

What if the input array contains zeroes? What if the input array only has one integer?

### Hint 19

We'll be fine with zeroes.

But what if the input array has fewer than two integers?

### Hint 20

Well, there won't be any products to return because at any index there are no “other” integers. So let's throw an 
exception.
