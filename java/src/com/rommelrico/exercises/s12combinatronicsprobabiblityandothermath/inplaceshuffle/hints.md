### Breakdown

It helps to start by ignoring the in-place requirement, then adapt the approach to work in-place.

Also, the name "shuffle" can be slightly misleading - the point is to arrive at a random ordering 
of the items from the original array. Don't fixate too much on preconceived notions of how you 
would "shuffle" e.g. a deck of cards.

### Hint 1

How might we do this by hand?

### Hint 2

We can simply choose a random item to be the first item in the resulting array, then choose another random item 
(from the items remaining) to be the second item in the resulting array, etc.

Assuming these choices were in fact random, this would give us a uniform shuffle. To prove it rigorously, we can show 
any given item aaa has the same probability (1/n​​) of ending up in any given spot.

First, some stats review: to get the probability of an outcome, you need to multiply the probabilities of all the 
steps required for that outcome. Like so:

| Outcome        | Steps                           | Probability                                           | 
-----------------|---------------------------------|-------------------------------------------------------|
| Item #1 is a   | a is picked first               | 1/n                                                   |
| Item #2 is a   | a not picked, a picked second   | (n-1)/n * 1/(n-1) = 1/n                               |
| Item #3 is a   | a picked third                  | (n-1)/n * (n-2)/(n-1) * 1/(n-2) = 1/n                 |
| Item #4 is a   | a picked fourth                 | (n-1)/n * (n-2)/(n-1) * (n-3)/(n-2) * 1/(n-3) = 1/n   |

So, how do we implement this in code?

### Hint 3

If we didn't have the "in place" requirement, we could allocate a new array, then one-by one take a random item from 
the input array, remove it, put it in the first position in the new array, and keep going until the input array is 
empty (well, probably a copy of the input array—best not to destroy the input)

How can we adapt this to be in-place?

### Hint 4

What if we make our new "random" array simply be the front of our input array?
