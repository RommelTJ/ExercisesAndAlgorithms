### Breakdown

How would we solve this by hand? We know our two movie lengths need to sum to flightLength. So 
for a given firstMovieLength, we need a secondMovieLength that equals flightLength - 
firstMovieLength.

To do this by hand we might go through movieLengths from beginning to end, treating each item as 
firstMovieLength, and for each of those check if there's a secondMovieLength equal to 
flightLength - firstMovieLength.

How would we implement this in code? We could nest two loops (the outer choosing 
firstMovieLength, the inner choosing secondMovieLength). That'd give us a runtime of O(n^2). We 
can do better.

### Hint 1

To bring our runtime down we'll probably need to replace that inner loop (the one that looks for 
a matching secondMovieLength) with something faster.

### Hint 2

We could sort the movieLengths first — then we could use binary search to find secondMovieLength 
in O(lg{n}) time instead of O(n) time. But sorting would cost O(n lg(n)), and we can do even 
better than that.

Could we check for the existence of our secondMovieLength in constant time?

### Hint 3

What data structure gives us convenient constant-time lookups?

### Hint 4

A hash set!

So we could throw all of our movieLengths into a hash set first, in O(n) time. Then we could loop 
through our possible firstMovieLengths and replace our inner loop with a simple check in our 
hash set. This'll give us O(n) runtime overall!

Of course, we need to add some logic to make sure we're not showing users the same movie twice...

But first, we can tighten this up a bit. Instead of two sequential loops, can we do it all in one 
loop? (Done carefully, this will give us protection from showing the same movie twice as well.)
