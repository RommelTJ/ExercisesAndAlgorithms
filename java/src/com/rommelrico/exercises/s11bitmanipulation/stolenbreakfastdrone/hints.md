### Breakdown

A brute force approach would use two nested loops to go through every ID and check every other ID 
to see if there's a duplicate.

This would take O(n^2) time and O(1) space. Can we bring that runtime down?

### Hint 1

Well, we know every integer appears twice, except for one integer, which appears once. Can we just 
track how many times each integer appears?

### Hint 2

We could iterate through the array and store each integer in a hash map, where the keys are the 
integers and the values are the number of times we've seen that integer so far. At the end, we'd 
just need to return the integer we saw one time.

```
public static int findUniqueDeliveryId(int[] deliveryIds) {

    Map<Integer, Integer> idsToOccurrences = new HashMap<>();

    for (int deliveryId : deliveryIds) {
        if (idsToOccurrences.containsKey(deliveryId)) {
            idsToOccurrences.put(deliveryId, idsToOccurrences.get(deliveryId) + 1);
        } else {
            idsToOccurrences.put(deliveryId, 1);
        }
    }

    for (Map.Entry<Integer, Integer> entry : idsToOccurrences.entrySet()) {
        if (entry.getValue() == 1) {
            return entry.getKey();
        }
    }

    return 0;
}
```

### Hint 3

Alright, we got our runtime down to O(n). That's probably the best runtime we can get - to find our 
unique integer we'll definitely have to look at every integer in the worst case.

But now we've added O(n) space, for our hash map. Can we bring that down?

### Hint 4

Well, we could use booleans as our values, instead of integers. If we see an integer, we'll add it 
as a key in our hash map with a boolean value of true. If we see it again, we'll change its value to 
false. At the end, our non-repeated order ID will be the one integer with a value of true.

How much space does this save us? Depends how our language stores booleans vs integers. Often 
booleans take up just as much space as integers.

And even if each boolean were just 1 bit, that'd still be O(n) space overall.

So using booleans probably doesn't save us much space here. Any other ideas?

### Hint 5

Let's zoom out and think about what we're working with. The only data we have is integers. How are 
integers stored? 

### Hint 6

Our machine stores integers as binary numbers using bits. What if we thought about this problem on 
the level of individual bits?

### Hint 7

Let's think about the bitwise operations AND, OR, XOR, NOT and bit shifts.

Is one of those operations helpful for finding our unique integer?

### Hint 8

We're seeing every integer twice, except one. Is there a bitwise operation that would let the second 
occurrence of an integer cancel out the first?

If so, we could start with a variable uniqueDeliveryId set to 0 and run some bitwise operation with 
that variable and each number in our array. If duplicate integers cancel each other out, then we'd 
only be left with the unique integer at the end!

Which bitwise operation would let us do that?

