### Breakdown

How can we re-phrase this problem in terms of smaller subproblems?

### Hint 1

Breaking the problem into smaller subproblems will clearly involve reducing the size of at least one of our stacks of 
cards. So to start, let's try taking the first card out of shuffledDeck.

What should be true of this card if shuffledDeck is a riffle of half1 and half2?

### Hint 2

If shuffledDeck is a riffle of half1 and half2, then the first card from shuffledDeck should be either the same as the 
first card from half1 or the same as the first card from half2.

If we're looking at our decks face-up, from above (so we only see the top card), this could be a single riffle:
Looking at three face up stacks of cards, the top card of the shuffled deck is 10, the top card of half 1 is 7, and 
the top card of half 2 is 10.

While this could not:
Looking at three face up stacks of cards, the top card of the shuffled deck is 32, the top card of half 1 is 7, and 
the top card of half 2 is 10.

Now that we know the first card checks out, how do we get to our subproblem?

### Hint 3

Let's "throw out" the top card from shuffledDeck as well as the card it matched with from the top of half1 or half2. 
Those cards are now "accounted for."

Now we're left with a smaller version of the original problem, which we can solve using the same approach! So we keep 
doing this over and over until we exhaust shuffledDeck. If we get to the end and each card "checks out," we return true.

How do we implement this in code?

### Hint 4

Now that we have a problem that's the same as the original problem except smaller, our first thought might be to use 
recursion. All we need is a base case. What's our base case?

### Hint 5

We stop when we run out of cards in our shuffledDeck. So that's our base case: when we've checked all cards in 
shuffledDeck, we return true because we know all of the cards have been "accounted for."

```
private static int[] removeTopCard(int[] cards) {
    return Arrays.copyOfRange(cards, 1, cards.length);
}

public static boolean isSingleRiffle(int[] half1, int[] half2, int[] shuffledDeck) {

    // base case
    if (shuffledDeck.length == 0) {
        return true;
    }

    // if the top of shuffledDeck is the same as the top of half1
    // (making sure first that we have a top card in half1)
    if (half1.length > 0 && half1[0] == shuffledDeck[0]) {

        // take the top cards off half1 and shuffledDeck and recurse
        return isSingleRiffle(removeTopCard(half1), half2, removeTopCard(shuffledDeck));

    // if the top of shuffledDeck is the same as the top of half2
    } else if (half2.length > 0 && half2[0] == shuffledDeck[0]) {

        // take the top cards off half2 and shuffledDeck and recurse
        return isSingleRiffle(half1, removeTopCard(half2), removeTopCard(shuffledDeck));

    // top of shuffledDeck doesn't match top of half1 or half2
    // so we know it's not a single riffle
    } else {
        return false;
    }
}
```

This solution will work. But we can do better.

Before we talk about optimization, note that our inputs are of small and constant size. This method will take hardly 
any time or space, even if it could be more efficient. In industry, especially at small startups that want to move 
quickly, optimizing this might be considered a "premature optimization." But if we're going to do something 
inefficient, we should at least know about it. Great engineers have both the skill to see how to optimize their code 
and the wisdom to know when those optimizations aren't worth it. At this point in the interview I recommend saying 
"I think we can optimize this a bit further, although given the constraints on the input this probably won't be very 
resource-intensive anyway...should we talk about optimizations?"

Okay, back to our show. This method will take O(n^2) time and O(n^2) additional space.

Whaaaaat? Yeah. Take a look at this snippet:

```
private static int[] removeTopCard(int[] cards) {
    return Arrays.copyOfRange(cards, 1, cards.length);
}

return isSingleRiffle(removeTopCard(half1), half2, removeTopCard(shuffledDeck));
```

In particular this expression:

`Arrays.copyOfRange(cards, 1, cards.length);`  

That's a slice, and it costs O(m) time and space, where m is the size of the resulting array. That's going to 
determine our overall time and space cost here—the rest of the work we're doing is constant space and time.

In our recursing we'll build up n frames on the call stack. Each of those frames will hold a different slice of our 
original shuffledDeck (and half1 and half2, though we only slice one of them in each recursive call).

So, what's the total time and space cost of all our slices?

If shuffledDeck has n items to start, taking our first slice takes n−1 time and space (plus half that, since we're 
also slicing one of our halves—but that's just a constant multiplier so we can ignore it). In our second recursive 
call, slicing takes n−2 time and space. Etcetera.

So our total time and space cost for slicing comes to:  
`(n - 1) + (n - 2) + ... + 2 + 1`    

This is a common series that turns out to be O(n^2).

We can do better than this O(n^2) time and space cost. One way we could to that is to avoid slicing and instead keep 
track of indices in the array: 

```
public static boolean isSingleRiffle(int[] half1, int[] half2, int[] shuffledDeck) {
    return isSingleRiffle(half1, half2, shuffledDeck, 0, 0, 0);
}

private static boolean isSingleRiffle(int[] half1, int[] half2, int[] shuffledDeck,
        int shuffledDeckIndex, int half1Index, int half2Index) {

    // base case we've hit the end of shuffledDeck
    if (shuffledDeckIndex == shuffledDeck.length) {
        return true;
    }

    // if we still have cards in half1
    // and the "top" card in half1 is the same
    // as the top card in shuffledDeck
    if ((half1Index < half1.length)
            && (half1[half1Index] == shuffledDeck[shuffledDeckIndex])) {
        half1Index++;

    // if we still have cards in half2
    // and the "top" card in half2 is the same
    // as the top card in shuffledDeck
    } else if ((half2Index < half2.length)
            && (half2[half2Index] == shuffledDeck[shuffledDeckIndex])) {
        half2Index++;

    // if the top card in shuffledDeck doesn't match the top
    // card in half1 or half2, this isn't a single riffle.
    } else {
        return false;
    }

    // the current card in shuffledDeck has now been "accounted for"
    // so move on to the next one
    shuffledDeckIndex++;
    return isSingleRiffle(half1, half2, shuffledDeck, shuffledDeckIndex, half1Index, half2Index);
}
```

So now we're down to O(n) time, but we're still taking O(n) space in the call stack because of our recursion. We can 
rewrite this as an iterative method to get that memory cost down to O(1).

### Hint 6

What's happening in each iteration of our recursive method? Sometimes we're taking a card off of half1 and sometimes 
we're taking a card off of half2, but we're always taking a card off of shuffledDeck.

So what if instead of taking cards off of shuffledDeck 1-by-1, we iterated over them?
