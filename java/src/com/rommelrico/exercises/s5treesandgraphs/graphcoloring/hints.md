### Breakdown

Let's take a step back. Is it always possible to find a legal coloring with D+1 colors?

Let's think about it. Each node has at most D neighbors, and we have D+1 colors. So, if we 
look at any node, there's always at least one color that's not taken by its neighbors.

So yes - D+1 is always enough colors for a legal coloring.

Okay, so there is always a legal coloring. Now, how can we find it?

### Hint 1

A brute force approach would be to try every possible combination of colors until we find a 
legal coloring. Our steps would be:
1. For each possible graph coloring,
2. If the coloring is legal, then return it
3. Otherwise, move on to the next coloring

For example, looking back at our sample graph, D is 3, so we can use 4 colors. The 
combinations of 4 colors for all 12 nodes are:

```
red, red, red, red, red, red, red, red, red, red, red, red
red, red, red, red, red, red, red, red, red, red, red, yellow
red, red, red, red, red, red, red, red, red, red, red, green
red, red, red, red, red, red, red, red, red, red, red, blue
red, red, red, red, red, red, red, red, red, red, yellow, red
[...]
blue, blue, blue, blue, blue, blue, blue, blue, blue, blue, blue, green
blue, blue, blue, blue, blue, blue, blue, blue, blue, blue, blue, blue
```

And we'd keep trying combinations until we reach one that legally colors the graph.

This would work. But what's the complexity?

Here we'd try 4^{12} combinations (every combination of 4 colors for 12 nodes). In general, 
we'll have to check O(D^N) colorings. And that's not all - each time we try a coloring, we 
have to check all M edges to see if the vertices at both ends have different colors. So, our 
runtime is O(M*D^N). That's exponential time since N is in an exponent.

Since this algorithm is so inefficient, it's probably not what the interviewer is looking for. 
With practice, it gets easier to quickly judge if an approach will be inefficient. Still, 
sometimes it's a good idea in an interview to briefly explain inefficient ideas and why you 
think they're inefficient. It shows rigorous thinking.

How can we color the graph more efficiently?

### Hint 2

Well, we're wasting a lot of time trying color combinations that don't work. If the first 2 
nodes are neighbors, we shouldn't try any combinations where the first 2 colors are the same. 
 
### Hint 3

Instead of assigning all the colors at once, what if we colored the nodes one by one?

### Hint 4

We could assign a color to the first node, then find a legal color for the second node, then 
for the third node, and keep going node by node.

```
import java.util.HashSet;
import java.util.Set;

public static void colorGraph(GraphNode[] graph, String[] colors) {

    for (GraphNode node : graph) {

        // get the node's neighbors' colors, as a set so we
        // can check if a color is illegal in constant time
        Set<String> illegalColors = new HashSet<>();
        for (GraphNode neighbor : node.getNeighbors()) {
            if (neighbor.hasColor()) {
                illegalColors.add(neighbor.getColor());
            }
        }

        Set<String> legalColors = new HashSet<>();
        for (String color : colors) {
            if (!illegalColors.contains(color)) {
                legalColors.add(color);
            }
        }

        // assign the first legal color
        node.setColor(legalColors.iterator().next());
    }
}
```

Is it possible we'll back ourselves into a corner somehow and run out of colors for some nodes?

Let's think back to our earlier argument about whether a coloring always exists:
"Each node has at most D neighbors, and we have D+1 colors. So, if we look at any node, 
there's always at least one color that's not taken by its neighbors."

That reasoning works here, too! So no - we'll never back ourselves into a corner.

Ok, what's our runtime? 

### Hint 5

We're iterating through each node in the graph, so the loop body executes N times. In each 
iteration of the loop:
1. We look at the current node's neighbors to figure out what colors are already taken. 
   That's O(D), since any given node can have up to D neighbors.
2. Then, we look at all the colors (there are O(D) of them) to see which ones are available.
3. Finally, we pick the first color that's free and assign it to the node (O(1)).

So our runtime is N∗(D+D+1), which is O(N∗D).

Can we tighten our analysis a bit here? Take a look at step 1, where we collect the 
neighbors' colors:

We said looking at each node's neighbors was O(D) since each node can have at most D 
neighbors... but each node might have way fewer neighbors than that.

Can we say anything about the total number of neighbors we'll look at across all of the 
loop iterations? How many neighbors are there in the entire graph?

Each edge creates two neighbors: one for each node on either end. So when our code looks at 
every neighbor for every node, it looks at 2∗M neighbors in all. With O(M) neighbors, 
collecting all the colors over the entire for each loop takes O(M) time.

Using this tighter analysis, we've taken our runtime from N∗(D+D+1) down to N∗(D+1)+M. 
That's O((N∗D)+M) time.

Of course, that complexity doesn't look any faster, at least not asymptotically. But in the 
underlying expression, we've gotten rid of one of the two N∗D factors.

Can we get rid of the other one to bring our complexity down to linear time?

### Hint 6

The remaining N∗D factor comes from step 2: looking at every color for every node to 
populate legalColors.

Do we have to look at every color for every node?

### Hint 7

When we're coloring a node, we just need one color that hasn't been taken by any of the 
node's neighbors. We can stop looking at colors as soon as we find one:

```
import java.util.HashSet;
import java.util.Set;

public static void colorGraph(GraphNode[] graph, String[] colors) {

    for (GraphNode node : graph) {

        // get the node's neighbors' colors, as a set so we
        // can check if a color is illegal in constant time
        Set<String> illegalColors = new HashSet<>();
        for (GraphNode neighbor : node.getNeighbors()) {
            if (neighbor.hasColor()) {
                illegalColors.add(neighbor.getColor());
            }
        }

        // assign the first legal color
        for (String color : colors) {
            if (!illegalColors.contains(color)) {
                node.setColor(color);
                break;
            }
        }
    }
}
```

Okay, now what's the time cost of assigning the first legal color to every node (the whole 
last block)?

We'll try at most illegalColors.size() + 1 colors in total. That's how many we'd need if we 
happen to test all the colors in illegalColors first, before finally testing the one legal 
color last.

Remember the "+1" we get from testing the one legal color last! It's going to be important 
in a second.

How many colors are in illegalColors? It's at most the number of neighbors, if each 
neighbor has a different color.

Let's use that trick of looking at all of the loop iterations together. In total, over the 
course of the entire loop, how many neighbors are there?

Well, each of our M edges adds two neighbors to the graph: one for each node on either end. 
So that's 2∗M neighbors in total. Which means 2∗M illegal colors in total.

But remember: we said we'd try as many as illegalColors.size() + 1 colors per node. We still 
have to factor in that "+1"! Across all N of our nodes, that's an additional N colors. So we 
try 2∗M+N colors in total across all of our nodes.

That's O(M+N) time for assigning the first legal color to every node. Add that to the O(M) 
for finding all the illegal colors, and we get O(M+N) time in total for our graph coloring 
method.

Is this the fastest runtime we can get? We'll have to look at every node (O(N)) and every 
edge (O(M)) at least once, so yeah, we can't get any better than O(N+M).

How about our space cost?

The only data structure we allocate with non-constant space is the hash set of illegal 
colors. What's the most space that ever takes up?

In the worst case, the neighbors of the node with the maximum degree will all have 
different colors, so our space cost is O(D).

Before we're done, what about edge cases? 

### Hint 8

For graph problems in general, edge cases are:
* nodes with no edges
* cycles
* loops

What if there are nodes with no edges? Will our method still color every node?

### Hint 9

Yup, no problem. Isolated nodes tend to cause problems when we're traversing a graph 
(starting from one node and "walking along" edges to other nodes, like we do in a 
depth-first or breadth-first search). We're not doing that here - instead, we're iterating 
over an array of all the nodes.

What if the graph has a cycle? Will our method still work?

### Hint 10

Yes, it will. Cycles also tend to cause problems with graph traversal, because we can end 
up in infinite loops (going around and around the cycle). But we're not actually traversing 
our graph here.

What if the graph has a loop?

### Hint 11

That's a problem. A node with a loop is adjacent to itself, so it can't have the same color 
as... itself. So it's impossible to "legally color" a node with a loop. So we should throw 
an error.

How can we detect loops?

### Hint 12

We know a node has a loop if the node is in its own hash set of neighbors.

