### Breakdown

To start, try writing an example value for stockPricesYesterday and finding the maximum profit "by hand." 
What's your process for figuring out the maximum profit?

### Hint 1

The brute force approach would be to try every pair of times (treating the earlier time as the buy time 
and the later time as the sell time) and see which one is higher.

```java
public static int getMaxProfit(int[] stockPricesYesterday) {

    int maxProfit = 0;

    // go through every time
    for (int outerTime = 0; outerTime < stockPricesYesterday.length; outerTime++) {

        // for every time, go through every other time
        for (int innerTime = 0; innerTime < stockPricesYesterday.length; innerTime++) {

            // for each pair, find the earlier and later times
            int earlierTime = Math.min(outerTime, innerTime);
            int laterTime   = Math.max(outerTime, innerTime);

            // and use those to find the earlier and later prices
            int earlierPrice = stockPricesYesterday[earlierTime];
            int laterPrice   = stockPricesYesterday[laterTime];

            // see what our profit would be if we bought at the
            // min price and sold at the current price
            int potentialProfit = laterPrice - earlierPrice;

            // update maxProfit if we can do better
            maxProfit = Math.max(maxProfit, potentialProfit);
        }
    }

    return maxProfit;
}
```

But that will take O(n2)O(n^2)O(n​2​​) time, since we have two nested loops—for every time, 
we're going through every other time. Also, **it's not correct**: we won't ever report a negative profit! 
Can we do better? 

### Hint 2

Well, we’re doing a lot of extra work. We’re looking at every pair twice. We know we have to buy before we sell, so in 
our inner for loop we could just look at every price after the price in our outer for loop.

That could look like this: 

```java
public static int getMaxProfit(int[] stockPricesYesterday) {

    int maxProfit = 0;

    // go through every price and time
    for (int earlierTime = 0; earlierTime < stockPricesYesterday.length; earlierTime++) {
        int earlierPrice = stockPricesYesterday[earlierTime];

        // and go through all the LATER prices
        for (int laterTime = earlierTime + 1; laterTime < stockPricesYesterday.length; laterTime++) {
            int laterPrice = stockPricesYesterday[laterTime];

            // see what our profit would be if we bought at the
            // min price and sold at the current price
            int potentialProfit = laterPrice - earlierPrice;

            // update maxProfit if we can do better
            maxProfit = Math.max(maxProfit, potentialProfit);
        }
    }

    return maxProfit;
}
```

What’s our runtime now? 

### Hint 3

Well, our outer for loop goes through all the times and prices, but our inner for loop goes through one fewer price 
each time. So our total number of steps is the 
sum n+(n−1)+(n−2)...+2+1n + (n - 1) + (n - 2) ... + 2 + 1n+(n−1)+(n−2)...+2+1, which is still O(n2)O(n^2)O(n​2​​) time.

We can do better!

### Hint 4

If we're going to do better than O(n2)O(n^2)O(n​2​​), we're probably going to do it in either O(nlgn)O(n\lg{n})O(nlgn) 
or O(n)O(n)O(n). O(nlgn)O(n\lg{n})O(nlgn) comes up in sorting and searching algorithms where we're recursively cutting 
the array in half. It's not obvious that we can save time by cutting the array in half here. Let's first see how well 
we can do by looping through the array only once. 

### Hint 5

Since we're trying to loop through the array once, let's use a greedy approach, where we keep a running maxProfit 
until we reach the end. We'll start our maxProfit at $0. As we're iterating, how do we know if we've found a new 
maxProfit? 

### Hint 6

At each iteration, our maxProfit is either:

1. the same as the maxProfit at the last time step, or
2. the max profit we can get by selling at the currentPrice

How do we know when we have case (2)?

### Hint 7

The max profit we can get by selling at the currentPrice is simply the difference between the currentPrice and the 
minPrice from earlier in the day. If this difference is greater than the current maxProfit, we have a new maxProfit.

So for every price, we’ll need to:

1. keep track of the lowest price we’ve seen so far
2. see if we can get a better profit

### Hint 8

Here’s one possible solution:

```java
public static int getMaxProfit(int[] stockPricesYesterday) {

    int minPrice = stockPricesYesterday[0];
    int maxProfit = 0;

    for (int currentPrice : stockPricesYesterday) {

        // ensure minPrice is the lowest price we've seen so far
        minPrice = Math.min(minPrice, currentPrice);

        // see what our profit would be if we bought at the
        // min price and sold at the current price
        int potentialProfit = currentPrice - minPrice;

        // update maxProfit if we can do better
        maxProfit = Math.max(maxProfit, potentialProfit);
    }

    return maxProfit;
}
```

We’re finding the max profit with one pass and constant space!

Are we done? Let’s think about some edge cases. What if the stock value stays the same? What if the stock value goes 
down all day? 

### Hint 9

If the stock price doesn't change, the max possible profit is 0. Our method will correctly return that. So we're good.

But if the value goes down all day, we’re in trouble. Our method would return 0, but there’s no way we could break even 
if the price always goes down.

How can we handle this?

### Hint 10

Well, what are our options? Leaving our method as it is and just returning zero is not a reasonable option—we wouldn't 
know if our best profit was negative or actually zero, so we'd be losing information. Two reasonable options could be:

1. return a negative profit. “What’s the least badly we could have done?”
2. throw an exception. “We should not have purchased stocks yesterday!”

In this case, it’s probably best to go with option (1). The advantages of returning a negative profit are:

* We more accurately answer the challenge. If profit is "revenue minus expenses", we’re returning the best we could 
have done.
* It's less opinionated. We'll leave decisions up to our method’s users. It would be easy to wrap our method in a 
helper method to decide if it’s worth making a purchase.
* We allow ourselves to collect better data. It matters if we would have lost money, and it matters how much we would 
have lost. If we’re trying to get rich, we’ll probably care about those numbers.

How can we adjust our method to return a negative profit if we can only lose money? Initializing maxProfit to 0 won’t 
work...

### Hint 11

Well, we started our minPrice at the first price, so let’s start our maxProfit at the first profit we could get—if we 
buy at the first time and sell at the second time.

```java
minPrice = stockPricesYesterday[0];
maxProfit = stockPricesYesterday[1] - stockPricesYesterday[0];
```

But we have the potential for an ArrayIndexOutOfBoundsException here, if stockPricesYesterday has fewer than 2 prices.

We do want to throw an exception in that case, since profit requires buying and selling, which we can't do with less 
than 2 prices. So, let's explicitly check for this case and handle it:

```java
if (stockPricesYesterday.length < 2) {
    throw new IllegalArgumentException("Getting a profit requires at least 2 prices");
}

int minPrice = stockPricesYesterday[0];
int maxProfit = stockPricesYesterday[1] - stockPricesYesterday[0];
```

Ok, does that work? 

### Hint 12

No! maxProfit is still always 0. What’s happening? 

### Hint 13

If the price always goes down, minPrice is always set to the currentPrice. So currentPrice - minPrice comes out to 0, 
which of course will always be greater than a negative profit.

When we’re calculating the maxProfit, we need to make sure we never have a case where we try both buying and selling 
stocks at the currentPrice.

### Hint 14

To make sure we’re always buying at an earlier price, never the currentPrice, let’s switch the order around so we 
calculate maxProfit before we update minPrice.

We'll also need to pay special attention to time 0. Make sure we don't try to buy and sell at time 0. 
