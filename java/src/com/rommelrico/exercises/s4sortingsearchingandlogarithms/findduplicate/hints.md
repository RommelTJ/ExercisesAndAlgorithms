### Breakdown

This one's a classic! We just do one walk through the array, using a set to keep track of 
which items we've seen!

```
public static int findRepeat(int[] numbers) {
    Set<Integer> numbersSeen = new HashSet<>();
    for (int number : numbers) {
        if (numbersSeen.contains(number)) {
            return number;
        } else {
            numbersSeen.add(number);
        }
    }

    // whoops--no duplicate
    throw new IllegalArgumentException("no duplicate!");
}
```

Bam. O(n) time and O(n) space ...

Right, we're supposed to optimize for space. O(n) is actually kinda high space-wise. Hm. 
We can probably get O(1)... 

### Hint 1

We can "brute force" this by taking each number in the range 1..n and, for each, walking 
through the array to see if it appears twice.

```
public static int findRepeat(int[] numbers) {
    for (int needle = 1; needle < numbers.length; needle++) {
        boolean hasBeenSeen = false;
        for (int number : numbers) {
            if (number == needle) {
                if (hasBeenSeen) {
                    return number;
                } else {
                    hasBeenSeen = true;
                }
            }
        }
    }

    // whoops--no duplicate
    throw new IllegalArgumentException("no duplicate!");
}
```

This is O(1) space and O(n^2) time.

That space complexity can't be beat, but the time cost seems a bit high. Can we do better? 

### Hint 2

One way to beat O(n^2) time is to get O(n * lg n) time. Sorting takes O(n * lg n) time. And 
if we sorted the array, any duplicates would be right next to each other!

But if we start off by sorting our array we'll need to take O(n) space to store the sorted 
array... unless we sort the input array in place!

Okay, so this'll work:
1. Do an in-place sort of the array (for example an in-place merge sort).
2. Walk through the now-sorted array from left to right.
3. Return as soon as we find two adjacent numbers which are the same.

This'll keep us at O(1) space and bring us down to O(n * lg n) time.

But destroying the input is kind of a drag — it might cause problems elsewhere in our code. 
Can we maintain this time and space cost without destroying the input?

### Hint 3

Let's take a step back. How can we break this problem down into subproblems?

### Hint 4

If we're going to do O(n * lg n) time, we'll probably be iteratively doubling something or 
iteratively cutting something in half. That's how we usually get a "lg n". So what if we 
could cut the problem in half somehow? 

### Hint 5

Well, binary search works by cutting the problem in half after figuring out which half of 
our input array holds the answer.

But in a binary search, the reason we can confidently say which half has the answer is 
because the array is sorted. For this problem, when we cut our unsorted array in half we 
can't really make any strong statements about which elements are in the left half and which 
are in the right half.

What if we could cut the problem in half a different way, other than cutting the array in half?

### Hint 6

With this problem, we're looking for a needle (a repeated number) in a haystack (array). What 
if instead of cutting the haystack in half, we cut the set of possibilities for the needle 
in half?

The full range of possibilities for our needle is 1..n. How could we test whether the actual 
needle is in the first half of that range (1..n/2) or the second half (n/2+1..n)?

A quick note about how we're defining our ranges: when we take n/2​ we're doing integer 
division, so we throw away the remainder. To see what's going on, we should look at what 
happens when n is even and when n is odd:
* If n is 6 (an even number), we have n/2 = 3 and n/2 + 1 = 4, so our ranges are 1..3 and 4..6.
* If n is 5 (an odd number), n/2 = 2 (we throw out the remainder) and n/2 + 1 = 3, so our 
ranges are 1..2 and 3..5.

So we can notice a few properties about our ranges:
1. They aren't necessarily the same size.
2. They don't overlap.
3. Taken together, they represent the original input array's range of 1..n. In math 
terminology, we could say their union is 1..n.

So, how do we know if the needle is in 1..n/2​​ or n/2 + 1..n?

### Hint 7

Think about the original problem statement. We know that we have at least one repeat because 
there are n+1 items and they are all in the range 1..n, which contains only n distinct integers.

This notion of "we have more items than we have possibilities, so we must have at least one 
repeat" is pretty powerful. It's sometimes called the pigeonhole principle. Can we exploit 
the pigeonhole principle to see which half of our range contains a repeat?

### Hint 8

Imagine that we separated the input array into two subarrays — one containing the items in 
the range 1..n/2 and the other containing the items in the range n/2 + 1..n.

Each subarray has a number of elements as well as a number of possible distinct integers 
(that is, the length of the range of possible integers it holds).

Given what we know about the number of elements vs the number of possible distinct integers 
in the original input array, what can we say about the number of elements vs the number of 
distinct possible integers in these subarrays?

### Hint 9

The sum of the subarrays' numbers of elements is n + 1 (the number of elements in the 
original input array) and the sum of the subarrays' numbers of possible distinct integers is n 
(the number of possible distinct integers in the original input array).

Since the sums of the subarrays' numbers of elements must be 1 greater than the sum of the 
subarrays' numbers of possible distinct integers, one of the subarrays must have at least 
one more element than it has possible distinct integers.

Not convinced? We can prove this by contradiction. Suppose neither array had more elements 
than it had possible distinct integers. In other words, both arrays have at most the same 
number of items as they have distinct possibilities. The sum of their numbers of items would 
then be at most the total number of possibilities across each of them, which is n. This is a 
contradiction — we know that our total number of items from the original input array is n+1, 
which is greater than n.

Now that we know one of our subarrays has 1 or more items more than it has distinct 
possibilities, we know that subarray must have at least one duplicate, by the same 
pigeonhole argument that we use to know that the original input array has at least one 
duplicate.

So once we know which subarray has the count higher than its number of distinct 
possibilities, we can use this same approach recursively, cutting that subarray into two 
halves, etc, until we have just 1 item left in our range.

Of course, we don't need to actually separate our array into subarrays. All we care about is 
how long each subarray would be. So we can simply do one walk through the input array, 
counting the number of items that would be in each subarray.

Can you formalize this in code?

Careful — if we do this recursively, we'll incur a space cost in the call stack! 
Do it iteratively instead.

