### Breakdown

O(n*lg{n}) is the time to beat. Even if our array of scores were already sorted we'd have to 
do a full walk through the array to confirm that it was in fact fully sorted. So we have to 
spend at least O(n) time on our sorting method. If we're going to do better than O(n*lg{n}), 
we're probably going to do exactly O(n).

What are some common ways to get O(n) runtime? 

### Hint 1

One common way to get O(n) runtime is to use a greedy algorithm. But in this case we're not 
looking to just grab a specific value from our input set (e.g. the "largest" or the "greatest 
difference") — we're looking to reorder the whole set. That doesn't lend itself as well to a 
greedy approach.

Another common way to get O(n) runtime is to use counting. We can build an array scoreCounts 
where the indices represent scores and the values represent how many times the score appears. 
Once we have that, can we generate a sorted array of scores?

### Hint 2

What if we did an in-order walk through scoreCounts. Each index represents a score and its 
value represents the count of appearances. So we can simply add the score to a new array 
sortedScores as many times as count of appearances. 

