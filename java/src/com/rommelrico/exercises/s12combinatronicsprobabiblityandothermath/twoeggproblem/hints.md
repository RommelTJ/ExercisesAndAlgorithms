### Breakdown

What if we only had one egg? How could we find the correct floor?

### Hint 1

Because we can't use the egg again if it breaks, we'd have to play it safe and drop the egg from every floor, starting 
at the bottom and working our way up. In the worst case, the egg won't break until the top floor, so we'd drop the egg 
100 times.

What does having two eggs allow us to do differently? 

### Hint 2

Since we have two eggs, we can skip multiple floors at a time until the first egg breaks, keeping track of which floors 
we dropped it from. Once that egg breaks we can use the second egg to try every floor, starting on the last floor where 
the egg didn't break and ending on the floor below the one where it did break.

How should we choose how many floors to skip with the first egg?

### Hint 3

What about trying a binary approach? We could drop the first egg halfway up the building at the 50th floor. If the egg 
doesn't break, we can try the 75th floor next. We keep going like this, dividing the problem in half at each step. As 
soon as the first egg breaks, we can start using our second egg on our (now - hopefully narrow) range of possible floors.

If we do that, what's the worst case number of total drops?

### Hint 4

The worst case is that the highest floor an egg won't break from is floor 48 or 49. We'd drop the first egg from the 
50th floor, and then we'd have to drop the second egg from every floor from 1 to 49, for a total of 50 drops. (Even if 
the highest floor an egg won't break from is floor 48, we still won't know if it will break from floor 49 until we try.)

Can we do better than this binary approach?

### Hint 5

50 is probably too many floors to skip for the first drop. In the worst case, if the first egg breaks after a small 
number of drops, the second egg will break after a large number of drops. And if we went the other way and skipped 1 
floor every time, we'd have the opposite problem! What would the worst case floor be then?

### Hint 6

The worst case would be floor 98 or 99 - the first egg would drop a large number of times (at every floor from 2 - 100 
skipping one floor each time) and the last egg would drop a small number of times (only on floor 99), for a total of 
51 drops.

Can we balance this out? Is there some number between 50 and 1 - the number of floors we'll skip with each drop of the 
first egg - where the first and second eggs would drop close to the same number of times in the worst case?

### Hint 7

Yes, we could skip 10 floors each time. The worst case would again be floor 98 or 99, but we'd only drop the first egg 
10 times and the second egg 9 times, for a total of 19 drops!

Is that the best we can do?

### Hint 8

Let's look at what happens with this strategy each time the first egg doesn't break. How does the worst case total 
number of drops change? 

### Hint 9

The worst case total number of drops increases by one each time the first egg doesn't break.

For example, if the egg breaks on our first drop from the 10th floor, we may have to drop the second egg at each floor 
between 1 and 9 for a worst case of 10 total drops. But if the egg breaks when we skip to the 20th floor we will have a 
worst case of 11 total drops (once for the 10th floor, once for the 20th, and all of the floors between 11 and 19)!

How can we keep the worst case number of drops from increasing each time the first egg doesn't break?

### Hint 10

Since the maximum number of drops increases by one each time we skip the same amount of floors, we could skip one fewer 
floor each time we drop the first egg!

But how do we choose how many floors to skip the first time?

### Hint 11

Well, we know two things that can help us:
1. We want to skip as few floors as possible the first time we drop an egg, so if our first egg breaks right away we 
   don't have a lot of floors to drop our second egg from.
2. We always want to be able to reduce the number of floors we're skipping by one. We don't want to get towards the 
   top and not be able to skip any floors any more.

Now that we know this, can we figure out the ideal number of floors to skip the first time?

### Hint 12

To be able to decrease the number of floors we skip by one every time we move up, and to minimize the number of floors 
we skip the first time, we want to end up skipping just one floor at the very top. Can we model this with an equation?

### Hint 13

Let's say n is the number of floors we'll skip the first time, and we know 1 is the number of floors we'll skip last. 
Our equation will be:  
n + (n−1) + (n−2) + ... + 1 = 100

How can we solve for n? Notice that we're summing every number from 1 to n.

### Hint 14

The left side is a triangular series. Knowing this, can we simplify and solve the equation?

### Hint 15

We know the left side reduces to:   
(n^2 + n) / 2

so we can plug that in:  
(n^2 + n) / 2 = 100

and we can rearrange to get a quadratic equation:
n^2 + n - 200 = 0

which gives us 13.651.

We can't skip a fraction of a floor, so how many floors should we skip the first time? And what's our final worst case 
total number of drops?

