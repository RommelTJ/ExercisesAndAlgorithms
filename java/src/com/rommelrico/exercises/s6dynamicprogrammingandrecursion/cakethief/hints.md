### Breakdown

The brute force approach is to try every combination of cakes, but that would take a really long time — you'd 
surely be captured.

What if we just look at the cake with the highest value?

### Hint 1

We could keep putting the cake with the highest value into our duffel bag until adding one more would go over our 
weight capacity. Then we could look at the cake with the second highest value, and so on until we find a cake that's 
not too heavy to add.

Will this work?

### Hint 2

Nope. Let's say our capacity is 100 kg and these are our two cakes:
```
new CakeType(1, 30);
new CakeType(50, 200);
```

With our approach, we’ll put in two of the second type of cake for a total value of 400 shillings. But we could 
have put in a hundred of the first type of cake, for a total value of 3000 shillings!

Just looking at the cake's values won’t work. Can we improve our approach?

### Hint 3

Well, why didn't it work?

### Hint 4

We didn’'t think about the weight! How can we factor that in?

### Hint 5

What if instead of looking at the value of the cakes, we looked at their value/weight ratio? Here are our example 
cakes again:
```
new CakeType(1, 30);
new CakeType(50, 200);
```

The second cake has a higher value, but look at the value per kilogram.

The second type of cake is worth 4 shillings/kg (200/50), but the first type of cake is worth 30 shillings/kg (30/1)!

Ok, can we just change our algorithm to use the highest value/weight ratio instead of the highest value? We know it 
would work in our example above, but try some more tests to be safe.

### Hint 6

We might run into problems if the weight of the cake with the highest value/weight ratio doesn't fit evenly into the 
capacity. Say we have these two cakes:
```
new CakeType(3, 40);
new CakeType(5, 70);
```

If our capacity is 8 kg, no problem. Our algorithm chooses one of each cake, giving us a haul worth 110 shillings, 
which is optimal.

But if the capacity is 9 kg, we're in trouble. Our algorithm will again choose one of each cake, for a total value 
of 110 shillings. But the actual optimal value is 120 shillings — three of the first type of cake!

So even looking at the value/weight ratios doesn't always give us the optimal answer!

Let's step back. How can we ensure we get the optimal value we can carry?

### Hint 7

Try thinking small. How can we calculate the maximum value for a duffel bag with a weight capacity of 1 kg? 
(Remember, all our weights and values are integers.)

### Hint 8

If the capacity is 1 kg, we’ll only care about cakes that weigh 1 kg (for simplicity, let's ignore zeroes for now). 
And we'd just want the one with the highest value.

We could go through every cake, using a greedy approach to keep track of the max value we’ve seen so far.

### Hint 9

Here’s an example solution:

```
public static long maxDuffelBagValueWithCapacity1(CakeType[] cakeTypes) {

    long maxValueAtCapacity1 = 0L;

    for (CakeType cakeType : cakeTypes) {
        if (cakeType.weight == 1) {
            maxValueAtCapacity1 = Math.max(maxValueAtCapacity1, cakeType.value);
        }
    }

    return maxValueAtCapacity1;
}
```

(We're using long because we're looking for a max value.)

Ok, now what if the capacity is 2 kg? We’ll need to be a bit more clever.

### Hint 10

It's pretty similar. Again we'll track a max value, let's say with a variable maxValueAtCapacity2. But now we care 
about cakes that weigh 1 or 2 kg. What do we do with each cake? And keep in mind, we can lean on the code we used 
to get the max value at weight capacity 1 kg.

### Hint 11

1. If the cake weighs 2 kg, it would fill up our whole capacity if we just took one. So we just need to see if the 
   cake's value is higher than our current maxValueAtCapacity2.
2. If the cake weighs 1 kg, we could take one, and we'd still have 1 kg of capacity left. How do we know the best 
   way to fill that extra capacity? We can use the max value at capacity 1. We'll see if adding the cake's value to 
   the max value at capacity 1 is better than our current maxValueAtCapacity2.

Does this apply more generally? If we can use the max value at capacity 1 to get the max value at capacity 2, can 
we use the max values at capacity 1 and 2 to get the max value at capacity 3?

Looks like this problem might have overlapping subproblems!

### Hint 12

Let's see if we can build up to the given weight capacity, one capacity at a time, using the max values from 
previous capacities. How can we do this?

### Hint 13

Well, let's try one more weight capacity by hand - 3 kg. So we already know the max values at capacities 1 and 2. 
And just like we did with maxValueAtCapacity1 and maxValueAtCapacity2, now we’ll track maxValueAtCapacity3 and 
loop through every cake:
```
long maxValueAtCapacity3 = 0L;

for (CakeType cakeType : cakeTypes) {
    // only care about cakes that weigh 3 kg or less
    ...
}
```

What do we do for each cake?

### Hint 14

If the current cake weighs 3 kg, easy - we see if it's more valuable than our current maxValueAtCapacity3.

What if the current cake weighs 2 kg?

### Hint 15

Well, let's see what our max value would be if we used the cake. How can we calculate that?

### Hint 16

If we include the current cake, we can only carry 1 more kilogram. What would be the max value we can carry?

### Hint 17

We already know the maxValueAtCapacity1! We can just add that to the current cake's value!

### Hint 18

Now we can see which is higher - our current maxValueAtCapacity3, or the new max value if we use the cake:
```
long maxValueUsingCake = maxValueAtCapacity1 + cakeType.value;
maxValueAtCapacity3 = Math.max(maxValueAtCapacity3, maxValueUsingCake);
```

Finally, what if the current cake weighs 1 kg?

### Hint 19

Basically the same as if it weighs 2 kg:
```
long maxValueUsingCake = maxValueAtCapacity2 + cakeType.value;
maxValueAtCapacity3 = Math.max(maxValueAtCapacity3, maxValueUsingCake);
```

There's gotta be a pattern here. We can keep building up to higher and higher capacities until we reach our input 
capacity. Because the max value we can carry at each capacity is calculated using the max values at previous 
capacities, we'll need to solve the max value for every capacity from 0 up to our duffel bag's actual weight 
capacity.

Can we write a method to handle all the capacities?

### Hint 20

To start, we'll need a way to store and update all the max monetary values for each capacity.

We could use a hash map, where the keys represent capacities and the values represent the max possible monetary 
values at those capacities. Hash maps are built on arrays, so we can save some overhead by just using an array.

```
public static long maxDuffelBagValue(CakeType[] cakeTypes, int weightCapacity) {

    // array to hold the maximum possible value at every
    // integer capacity from 0 to weightCapacity
    // starting each index with value 0 long
    long[] maxValuesAtCapacities = new long[weightCapacity + 1];
}
```

What do we do next?

### Hint 21

We'll need to work with every capacity up to the input weight capacity. That's an easy loop:
```
// every integer from 0 to the input weightCapacity
for (int currentCapacity = 0; currentCapacity <= weightCapacity; currentCapacity++) {
    ...
}
```

What will we do inside this loop? This is where it gets a little tricky.

### Hint 22

We care about any cakes that weigh the current capacity or less. Let's try putting each cake in the bag and seeing 
how valuable of a haul we could fit from there.

### Hint 23

So we'll write a loop through all the cakes (ignoring cakes that are too heavy to fit):
```
for (CakeType cakeType : cakeTypes) {

    // if the cake weighs as much or less than the current capacity
    // see what our max value could be if we took it!
    if (cakeType.weight <= currentCapacity) {
        // find maxValueUsingCake
        ...
    }
}
```

And put it in our method body so far:
```
public static long maxDuffelBagValue(CakeType[] cakeTypes, int weightCapacity) {

    // we make an array to hold the maximum possible value at every
    // duffel bag weight capacity from 0 to weightCapacity
    // starting each index with value 0
    long[] maxValuesAtCapacities = new long[weightCapacity + 1];

    for (int currentCapacity = 0; currentCapacity <= weightCapacity; currentCapacity++) {

        for (CakeType cakeType : cakeTypes) {

            // if the cake weighs as much or less than the current capacity
            // see what our max value could be if we took it!
            if (cakeType.weight <= currentCapacity) {
                // find maxValueUsingCake
                ...
            }
        }
    }
}
```

How do we compute maxValueUsingCake?

### Hint 24

Remember when we were calculating the max value at capacity 3kg and we "hard-coded" the maxValueUsingCake for cakes 
that weigh 3 kg, 2kg, and 1kg?
```
// cake weighs 3 kg
long maxValueUsingCake = cakeType.value;

// cake weighs 2 kg
long maxValueUsingCake = maxValueAtCapacity1 + cakeType.value;

// cake weighs 1 kg
long maxValueUsingCake = maxValueAtCapacity2 + cakeType.value;
```

How can we generalize this? With our new method body, look at the variables we have in scope:
1. maxValuesAtCapacities
2. currentCapacity
3. cakeType

Can we use these to get maxValueUsingCake for any cake?

### Hint 25

Well, let's figure out how much space would be left in the duffel bag after putting the cake in:
```
int remainingCapacityAfterTakingCake = currentCapacity - cakeType.weight;
```

So maxValueUsingCake is:
1. the current cake's value, plus
2. the best value we can fill the remainingCapacityAfterTakingCake with

```
int remainingCapacityAfterTakingCake = currentCapacity - cakeType.weight;
long maxValueUsingCake = cakeType.value + maxValuesAtCapacities[remainingCapacityAfterTakingCake];
```

We can squish this into one line:
```
long maxValueUsingCake = cakeType.value + maxValuesAtCapacities[currentCapacity - cakeType.weight];
```

Since remainingCapacityAfterTakingCake is a lower capacity, we'll have always already computed its max value and 
stored it in our maxValuesAtCapacities!

Now that we know the max value if we include the cake, should we include it? How do we know?

### Hint 26

Let's allocate a variable currentMaxValue that holds the highest value we can carry at the current capacity. We can 
start it at zero, and as we go through all the cakes, any time the value using a cake is higher than 
currentMaxValue, we'll update currentMaxValue!
```
currentMaxValue = Math.max(maxValueUsingCake, currentMaxValue);
```

What do we do with each value for currentMaxValue? What do we need to do for each capacity when we finish looping 
through all the cakes?

### Hint 27

We save each currentMaxValue in the maxValuesAtCapacities array. We'll also need to make sure we set currentMaxValue 
to zero in the right place in our loops - we want it to reset every time we start a new capacity.

So here's our method so far:

```
public static long maxDuffelBagValue(CakeType[] cakeTypes, int weightCapacity) {

    // we make an array to hold the maximum possible value at every
    // duffel bag weight capacity from 0 to weightCapacity
    // starting each index with value 0
    long[] maxValuesAtCapacities = new long[weightCapacity + 1];

    for (int currentCapacity = 0; currentCapacity <= weightCapacity; currentCapacity++) {

        // set a variable to hold the max monetary value so far for currentCapacity
        long currentMaxValue = 0;

        for (CakeType cakeType : cakeTypes) {

            // if the current cake weighs as much or less than the current weight capacity
            // it's possible taking the cake would get a better value
            if (cakeType.weight <= currentCapacity) {

                // so we check: should we use the cake or not?
                // if we use the cake, the most kilograms we can include in addition to the cake
                // we're adding is the current capacity minus the cake's weight. we find the max
                // value at that integer capacity in our array maxValuesAtCapacities
                long maxValueUsingCake = cakeType.value 
                    + maxValuesAtCapacities[currentCapacity - cakeType.weight];

                // now we see if it's worth taking the cake. how does the
                // value with the cake compare to the currentMaxValue?
                currentMaxValue = Math.max(maxValueUsingCake, currentMaxValue);
            }
        }

        // add each capacity's max value to our array so we can use them
        // when calculating all the remaining capacities
        maxValuesAtCapacities[currentCapacity] = currentMaxValue;
    }
}
```

Looking good! But what's our final answer?

### Hint 28

Our final answer is maxValuesAtCapacities[weightCapacity]!

Okay, this seems complete. What about edge cases?

### Hint 29

Remember, weights and values can be any non-negative integer. What about zeroes? How can we handle duffel bags that 
can't hold anything and cakes that weigh nothing?

### Hint 30

Well, if our duffel bag can't hold anything, we can just return 0. And if a cake weighs 0 kg, we return infinity. 
Right?

### Hint 31

Not that simple!

What if our duffel bag holds 0 kg, and we have a cake that weighs 0 kg. What do we return?

### Hint 32

And what if we have a cake that weighs 0 kg, but its value is also 0. If we have other cakes with positive weights 
and values, what do we return?

### Hint 33

If a cake's weight and value are both 0, it's reasonable to not have that cake affect what we return at all.

If we have a cake that weighs 0 kg and has a positive value, it's reasonable to return infinity, even if the 
capacity is 0.

For returning infinity, we have a couple choices. We could return:
1. The highest possible Long. In Java, that'd be Long.MAX_VALUE.
2. Raise an exception indicating the answer is infinity.

What are the advantages and disadvantages of each option?

### Hint 34

For the first option the advantage is the highest possible Long will behave like infinity in a few ways. For 
example, it'll be greater than any other integer. But it's a still a specific number, which can be an advantage or 
disadvantage - we might want our result to always be the same type, but representing infinity as a specific number 
is "lossy" - it won't be clear if we're talking about an actual value or the special case of infinity.

The second option is a good choice if we decide infinity is usually an "unacceptable" answer. For example, we 
might decide an infinite answer means we've probably entered our inputs wrong. Then, if we really wanted to 
"accept" an infinite answer, we could always "catch" this exception when we call our method.

Either option could be reasonable. We'll go with the second one here.

