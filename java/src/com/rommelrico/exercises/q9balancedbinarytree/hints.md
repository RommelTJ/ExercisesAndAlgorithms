### Breakdown

Sometimes it's good to start by rephrasing or "simplifying" the problem.

The requirement of "the difference between the depths of any two leaf nodes is no greater than 1" 
implies that we'll have to compare the depths of all possible pairs of leaves. That'd be expensive — if 
there are n leaves, there are O(n^2) possible pairs of leaves.

But we can simplify this requirement to require less work. For example, we could equivalently say:
* "The difference between the min leaf depth and the max leaf depth is 1 or less"
* "There are at most two distinct leaf depths, and they are at most 1 apart"

### Hint 1

If you're having trouble with a recursive approach, try using an iterative one.

### Hint 2

To get to our leaves and measure their depths, we'll have to traverse the tree somehow. What methods do 
we know for traversing a tree?

### Hint 3

Depth-first and breadth-first are common ways to traverse a tree. Which one should we use here? 

### Hint 4

The worst-case time and space costs of both are the same—you could make a case for either.

But one characteristic of our algorithm is that it could short-circuit and return false as soon as it 
finds two leaves with depths more than 1 apart. So maybe we should use a traversal that will hit leaves 
as quickly as possible...

### Hint 5

Depth-first traversal will generally hit leaves before breadth-first, so let's go with that. How could 
we write a depth-first walk that also keeps track of our depth?
