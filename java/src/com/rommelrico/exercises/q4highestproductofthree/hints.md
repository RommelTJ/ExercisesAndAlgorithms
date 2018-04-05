### Breakdown

To brute force an answer we could iterate through arrayOfInts and multiply each integer by each other integer, and then 
multiply that product by each other other integer. This would probably involve nesting 3 loops. But that would be 
an O(n^3) runtime! We can definitely do better than that.

Because any integer in the array could potentially be part of the greatest product of three integers, we must at least 
look at each integer. So we're doomed to spend at least O(n) time.

### Hint 1

Sorting the array would let us grab the highest numbers quickly, so it might be a good first step. Sorting 
takes `O(n lg n)` time. That's better than the `O(n^3)` time our brute force approach required, but we can still do 
better. 

### Hint 2

Since we know we must spend at least O(n) time, let's see if we can solve it in exactly O(n) time.

A great way to get O(n) runtime is to use a greedy approach. How can we keep track of the highestProductOf3 "so far" as 
we do one walk through the array?

### Hint 3

Put differently, for each new current number during our iteration, how do we know if it gives us a new 
highestProductOf3?

### Hint 4

We have a new highestProductOf3 if the current number times two other numbers gives a product that's higher than our 
current highestProductOf3. What must we keep track of at each step so that we know if the current number times two 
other numbers gives us a new highestProductOf3?

### Hint 5

Our first guess might be:

1. Our current highestProductOf3
2. The threeNumbersWhichGiveHighestProduct

But consider this example:  
`int[] arrayOfInts = new int[]{1, 10, -5, 1, -100};`

Right before we hit −100 (so, in our second-to-last iteration), our highestProductOf3 was 10, and the 
threeNumbersWhichGiveHighestProduct were `[10,1,1]`. But once we hit −100, suddenly we can take −100 ∗ −5 ∗ 10 to get 
5000. So we should have "held on to" that −5, even though it wasn't one of the threeNumbersWhichGiveHighestProduct.

We need something a little smarter than threeNumbersWhichGiveHighestProduct. What should we keep track of to make sure 
we can handle a case like this? 

### Hint 6

There are at least two great answers:

1. Keep track of the highest2 and lowest2 (most negative) numbers. If the current number times some combination of 
those is higher than the current highestProductOf3, we have a new highestProductOf3!
2. Keep track of the highestProductOf2 and lowestProductOf2 (could be a low negative number). If the current number 
times one of those is higher than the current highestProductOf3, we have a new highestProductOf3!

We'll go with (2). It ends up being slightly cleaner than (1), though they both work just fine.

How do we keep track of the highestProductOf2 and lowestProductOf2 at each iteration? (Hint: we may need to also keep 
track of something else.)

### Hint 7

We also keep track of the lowest number and highest number. If the current number times the current highest—or the 
current lowest, if current is negative—is greater than the current highestProductOf2, we have a new highestProductOf2. 
Same for lowestProductOf2.

So at each iteration we're keeping track of and updating:

    highestProductOf3
    highestProductOf2
    highest
    lowestProductOf2
    lowest

Can you implement this in code? Careful—make sure you update each of these variables in the right order, otherwise you 
might end up e.g. multiplying the current number by itself to get a new highestProductOf2.
