### Breakdown

Users? Connections? Routes? What data structures can we build out of that? Let's run 
through some common ones and see if anything fits here.
* Arrays? Nope - those are a bit too simple to express our network of users.
* Hash maps? Maybeee.
* Graphs? Yeah, that seems like it could work!

Let's run with graphs for a bit and see how things go. Users will be nodes in our graph, 
and we'll draw edges between users who are close enough to message each other.

Our input hash map already represents the graph we want in adjacency list format. Each key 
in the hash map is a node, and the associated value - an array of connected nodes - is an 
adjacency list.

Is our graph directed or undirected? Weighted or unweighted? 

### Hint 1

For directed vs. undirected, we'll assume that if Min can transmit a message to Jayden, 
then Jayden can also transmit a message to Min. Our sample input definitely suggests this 
is the case. And it makes sense - they're the same distance from each other, after all. 
That means our graph is undirected.

What about weighted? We're not given any information suggesting that some transmissions are 
more expensive than others, so let's say our graph is unweighted.

These assumptions seem pretty reasonable, so we'll go with them here. But, this is a great 
place to step back and check in with your interviewer to make sure they agree with what 
you've decided so far.

Okay, how do we start looking around our graph to find the shortest route from one user to 
another?

Or, more generally, how do we find the shortest path from a start node to an end node in an 
unweighted, undirected graph?

### Hint 2

There are two common ways to explore undirected graphs: depth-first search (DFS) and 
breadth-first search (BFS).

Which do we want here?
 
### Hint 3

Since we're interested in finding the shortest path, BFS is the way to go.

Remember: both BFS and DFS will eventually find a path if one exists. The difference 
between the two is:
* BFS always finds the shortest path.
* DFS usually uses less space.

Okay, so let's do a breadth-first search of our graph starting from the sender and 
stopping when we find the recipient. Since we're using breadth-first search, we know that 
the first time we see the recipient, we'll have traveled to them along the shortest path.

To code this up, let's start with a standard implementation of breadth-first search:

It's a good idea to know breadth-first and depth-first search well enough to quickly write 
them out. They show up in a lot of graph problems.

```
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public static void bfs(Map<String, String[]> graph, String startNode, String endNode) {

    Queue<String> nodesToVisit = new ArrayDeque<>();
    nodesToVisit.add(startNode);

    // keep track of what nodes we've already seen
    // so we don't process them twice
    Set<String> nodesAlreadySeen = new HashSet<String>();
    nodesAlreadySeen.add(startNode);

    while (!nodesToVisit.isEmpty()) {
        String currentNode = nodesToVisit.remove();

        // stop when we reach the end node
        if (currentNode.equals(endNode)) {
            // found it!
            break;
        }

        for (String neighbor: graph.get(currentNode)) {
            if (!nodesAlreadySeen.contains(neighbor)) {
                nodesAlreadySeen.add(neighbor);
                nodesToVisit.add(neighbor);
            }
        }
    }
}
```

Look at the nodesAlreadySeen hash set - that's really important and easy to forget. If we 
didn't have it, our algorithm would be slower (since we'd be revisiting tons of nodes) and 
it might never finish (if there's no path to the end node).

We're using an ArrayDeque instead of an ArrayList because we want an efficient 
first-in-first-out (FIFO) structure with O(1) inserts and removes. If we used an ArrayList, 
appending would be O(1), but removing elements from the front would be O(n).

This seems like we're on the right track: we're doing a breadth-first search, which gets us 
from the start node to the end node along the shortest path.

But we're still missing an important piece: we didn't actually store our path anywhere. We 
need to reconstruct the path we took. How do we do that?

Well, we know that the first node in the path is startNode. And the next node in the path 
is... well... hmm.

Maybe we can start from the end and work backward? We know that the last node in the path 
is endNode. And the node before that is... hmm.

We don't have enough information to actually reconstruct the path.

What additional information can we store to help us? 

### Hint 4

Well, to reconstruct our path, we'll need to somehow recover how we found each node. When do 
we find new nodes?

We find new nodes when iterating through currentNode's neighbors.

So, each time we find a new node, let's jot down what currentNode was when we found it. Like 
this:

```
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public static String[] bfsGetPath(Map<String, String[]> graph, String startNode, String endNode) {

    Queue<String> nodesToVisit = new ArrayDeque<>();
    nodesToVisit.add(startNode);

    // keep track of what nodes we've already seen
    // so don't process them twice
    Set<String> nodesAlreadySeen = new HashSet<String>();
    nodesAlreadySeen.add(startNode);

    // keep track of how we got to each node
    // we'll use this to reconstruct the shortest path at the end
    Map<String, String> howWeReachedNodes = new HashMap<>();
    howWeReachedNodes.put(startNode, null);

    while (!nodesToVisit.isEmpty()) {
        String currentNode = nodesToVisit.remove();

        // stop when we reach the end node
        if (currentNode.equals(endNode)) {
            String[] path = null;

            // somehow reconstruct the path here
            return path;
        }

        for (String neighbor : graph.get(currentNode)) {
            if (!nodesAlreadySeen.contains(neighbor)) {
                nodesAlreadySeen.add(neighbor);
                nodesToVisit.add(neighbor);

                // keep track of how we got to this node
                howWeReachedNodes.put(neighbor, currentNode);
            }
        }
    }

    return null;
}
```

Great. Now we just have to take that bookkeeping and use it to reconstruct our path! How do 
we do that? 

### Hint 5

Let's start by looking up startNode in our hash map. Oops, it's just null.

Oh. Right. Our hash map tells us which node comes before a given node on the shortest path. 
And nothing comes before the start node.

What about the endNode? If we look up the end node in howWeReachedNodes, we'll get the node 
we were visiting when we found endNode. That's the node that comes right before the end node 
along the shortest path!

So, we'll actually be building our path backward from endNode to startNode.

Here's what this could look like in code:

```
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public static String[] reconstructPath(Map<String, String> howWeReachedNodes,
    String startNode, String endNode) {

    List<String> shortestPath = new ArrayList<>();

    // start from the end of the path and work backwards
    String currentNode = endNode;

    while (currentNode != null) {
        shortestPath.add(currentNode);
        currentNode = howWeReachedNodes.get(currentNode);
    }

    return shortestPath.toArray(new String[shortestPath.size()]);
}
```

One small thing though. Won't this return a path that has the recipient at the beginning?

Oh. Since we started our backtracking at the recipient's node, our path is going to come 
out backward. So, let's reverse it before returning it:

```
Collections.reverse(shortestPath);  // now from startNode to endNode
return shortestPath.toArray(new String[shortestPath.size()]);  // return in the right order
```

Okay. That'll work!

But, before we're done, let's think about edge cases and optimizations.

What are our edge cases, and how should we handle them? 

### Hint 6

What happens if there isn't a route from the sender to the recipient?

If that happens, then we'll finish searching the graph without ever reconstructing and 
returning the path. That's a valid outcome - it just means we can't deliver the message 
right now. So, if we finish our search and haven't returned yet, let's return null to 
indicate that no path was found.

What about if either the sender or recipient aren't in our user network?

That's invalid input, so we should throw an exception.

Any other edge cases?

Those two should be it. So, let's talk about optimizations. Can we make our algorithm run 
faster or take less space?

### Hint 7

One thing that stands out is that we have two data structures - nodesAlreadySeen and 
howWeReachedNodes - that are updated in similar ways. In fact, every time we add a node to 
nodesAlreadySeen, we also add it to howWeReachedNodes. Do we need both of them?

We definitely need howWeReachedNodes in order to reconstruct our path. What about 
nodesAlreadySeen?

Every node that appears in nodesAlreadySeen also appears in our hash map. So, instead of 
keeping a separate hash set tracking nodes we've already seen, we can just use the keys in 
howWeReachedNodes. This lets us get rid of nodesAlreadySeen, which saves us O(n) space.

