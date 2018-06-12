### Breakdown

We need to find some way to break this problem down into subproblems.

### Hint 1

Here's one way: for each denomination, we can use it once, or twice, or...as many times as it takes to 
reach or overshoot the amount with coins of that denomination alone.

### Hint 2

For each of those choices of how many times to include coins of each denomination, we're left with the 
subproblem of seeing how many ways we can get the remaining amount from the remaining denominations.

Here's that approach in pseudocode:

```
public static int numberOfWays(amount, denominations) {
    answer = 0;
    for (denomination : denominations) {
        for (numTimesToUseDenomination : possibleNumTimesToUseDenominationWithoutOvershootingAmount) {
            answer += numberOfWays(amountRemaining, otherDenominations);
        }
    }
    return answer;
}
```

The answer for some of those subproblems will of course be 0. For example, there's no way to get 1¢ with 
only 2¢ coins.

### Hint 3

As a recursive method, we could formalize this as:

```
public static int changePossibilitiesTopDown(int amount, int[] denominations) {
    return changePossibilitiesTopDown(amount, denominations, 0);
}

private static int changePossibilitiesTopDown(int amountLeft, int[] denominations, int currentIndex) {

    // base cases:
    // we hit the amount spot on. yes!
    if (amountLeft == 0) {
        return 1;
    }

    // we overshot the amount left (used too many coins)
    if (amountLeft < 0) {
        return 0;
    }

    // we're out of denominations
    if (currentIndex == denominations.length) {
        return 0;
    }

    System.out.printf("checking ways to make %d with %s\n", 
        amountLeft, Arrays.toString(Arrays.copyOfRange(denominations,
        currentIndex, denominations.length)));

    // choose a current coin
    int currentCoin = denominations[currentIndex];

    // see how many possibilities we can get
    // for each number of times to use currentCoin
    int numPossibilities = 0;
    while (amountLeft >= 0) {
        numPossibilities += changePossibilitiesTopDown(amountLeft, denominations,
            currentIndex + 1);
        amountLeft -= currentCoin;
    }

    return numPossibilities;
}
```

But there's a problem—we'll often duplicate the work of checking remaining change possibilities. Note the 
duplicate calls with the input 4, [1,2,3]:

```
checking ways to make 4 with [1, 2, 3]
checking ways to make 4 with [2, 3]
checking ways to make 4 with [3]
checking ways to make 2 with [3]
checking ways to make 3 with [2, 3]
checking ways to make 3 with [3]
checking ways to make 1 with [3]
checking ways to make 2 with [2, 3]
checking ways to make 2 with [3]
checking ways to make 1 with [2, 3]
checking ways to make 1 with [3]
4
```

For example, we check ways to make 2 with [3] twice.

We can do better. How do we avoid this duplicate work and bring down the time cost? 

### Hint 4

One way is to memoize.

### Hint 5

Here's what the memoization might look like:

```java
class Change {

    private Map<String, Integer> memo = new HashMap<>();

    public int changePossibilitiesTopDown(int amount, int[] denominations) {
        return changePossibilitiesTopDown(amount, denominations, 0);
    }

    private int changePossibilitiesTopDown(int amountLeft, int[] denominations, int currentIndex) {

        // check our memo and short-circuit if we've already solved this one
        String memoKey = amountLeft + ", " + currentIndex;
        if (memo.containsKey(memoKey)) {
            System.out.println("grabbing memo [" + memoKey + "]");
            return memo.get(memoKey);
        }

        // base cases:
        // we hit the amount spot on. yes!
        if (amountLeft == 0) return 1;

        // we overshot the amount left (used too many coins)
        if (amountLeft < 0) return 0;

        // we're out of denominations
        if (currentIndex == denominations.length) return 0;

        System.out.printf("checking ways to make %d with %s\n", 
            amountLeft, Arrays.toString(Arrays.copyOfRange(denominations, 
            currentIndex, denominations.length)));

        // choose a current coin
        int currentCoin = denominations[currentIndex];

        // see how many possibilities we can get
        // for each number of times to use currentCoin
        int numPossibilities = 0;
        while (amountLeft >= 0) {
            numPossibilities += changePossibilitiesTopDown(amountLeft, denominations,
                currentIndex + 1);
            amountLeft -= currentCoin;
        }

        // save the answer in our memo so we don't compute it again
        memo.put(memoKey, numPossibilities);
        return numPossibilities;
    }
}
```

And now our checking has no duplication:

```
checking ways to make 4 with [1, 2, 3]
checking ways to make 4 with [2, 3]
checking ways to make 4 with [3]
checking ways to make 2 with [3]
checking ways to make 3 with [2, 3]
checking ways to make 3 with [3]
checking ways to make 1 with [3]
checking ways to make 2 with [2, 3]
grabbing memo [2, 2]
checking ways to make 1 with [2, 3]
grabbing memo [1, 2]
4
```

This answer is quite good. It certainly solves our duplicate work problem. It takes O(n∗m) time and 
O(n∗m) space, where n is the size of amount and m is the number of items in denominations. (Except 
we'd need to remove the line where we print "checking ways to make..." because making all those 
subarrays will take O(m^2) space!)

However, we can do better. Because our method is recursive it will build up a large call stack of 
size O(m). Of course, this cost is eclipsed by the memory cost of memo, which is O(n∗m). But it's 
still best to avoid building up a large stack like this, because it can cause a stack overflow (yes, 
that means recursion is usually better to avoid for methods that might have arbitrarily large inputs).

It turns out we can get O(n) additional space. 

### Hint 6

A great way to avoid recursion is to go bottom-up.

### Hint 7

Our recursive approach was top-down because it started with the final value for amount and recursively 
broke the problem down into subproblems with smaller values for amount. What if instead we tried to 
compute the answer for small values of amount first, and use those answers to iteratively compute the 
answer for higher values until arriving at the final amount?

### Hint 8

We can start by making an array waysOfDoingNCents, where the index is the amount and the value at each 
index is the number of ways of getting that amount.

This array will take O(n) space, where n is the size of amount.

### Hint 9

To further simplify the problem, we can work with only the first coin in denominations, then add in the 
second coin, then the third, etc.

What would waysOfDoingNCents look like for just our first coin: 1¢? Let's call this waysOfDoingNCents1.

```
int[] waysOfDoingNCents1 = new int[] {
    1,  // 0c:  no coins
    1,  // 1c:  1 1c coin
    1,  // 2c:  2 1c coins
    1,  // 3c:  3 1c coins
    1,  // 4c:  4 1c coins
    1,  // 5c:  5 1c coins
};
```

Now what if we add a 2¢ coin?

```
int[] waysOfDoingNCents1And2 = new int[] {
    1,      // 0c:  no change
    1,      // 1c:  no change
    1 + 1,  // 2c:  new [(2)]
    1 + 1,  // 3c:  new [(2, 1)]
    1 + 2,  // 4c:  new [(2, 1, 1), (2,2)]
    1 + 2,  // 5c:  new [(2, 1, 1, 1), (2, 2, 1)]
};
```

How do we formalize this process of going from waysOfDoingNCents1 to waysOfDoingNCents1And2? 

### Hint 10

Let's suppose we're partway through already (this is a classic dynamic programming approach). Say 
we're trying to calculate waysOfDoingNCents1And2[5]. Because we're going bottom-up, we know we already 
have:

1. waysOfDoingNCents1And2 for amounts less than 5
2. a fully-populated waysOfDoingNCents1

So how many new ways should we add to waysOfDoingNCents1[5] to get waysOfDoingNCents1And2[5]?

### Hint 11

Well, if there are any new ways to get 5¢ now that we have 2¢ coins, those new ways must involve at 
least one 2¢ coin. So if we presuppose that we'll use one 2¢ coin, that leaves us with 5 − 2 = 3 left 
to come up with. We already know how many ways we can get 3¢ with 1¢ and 2¢ coins: 
waysOfDoingNCents1And2[3], which is 2.

So we can see that:  
`waysOfDoingNCents1And2[5] = waysOfDoingNCents1[5] + waysOfDoingNCents1And2[5 - 2]`

Why don't we also need to check waysOfDoingNCents1And2[5 - 2 - 2] (two 2¢ coins)? Because we already 
checked waysOfDoingNCents1And2[1] when calculating waysOfDoingNCents1And2[3]. We'd be counting some 
arrangements multiple times. In other words, waysOfDoingNCents1And2[k] already includes the full count 
of possibilities for getting k, including possibilities that use 2¢ any number of times. We're only 
interested in how many more possibilities we might get when we go from k to k + 2 and thus have the 
ability to add one more 2¢ coin to each of the possibilities we have for k.
 