### Breakdown

One way to break the problem down is to come up with a way to confirm that a single node is in a valid 
place relative to its ancestors. Then if every node passes this test, our whole tree is a valid BST.

### Hint 1

What makes a given node "correct" relative to its ancestors in a BST? Well, it must be greater than 
any node it is in the right subtree of, and less than any node it is in the left subtree of. 

### Hint 2

So we could do a walk through our binary tree, keeping track of the ancestors for each node and 
whether the node should be greater than or less than each of them. If each of these greater-than or 
less-than relationships holds true for each node, our BST is valid.

The simplest ways to traverse the tree are depth-first and breadth-first. They have the same time 
cost (they each visit each node once). Depth-first traversal of a tree uses memory proportional to the 
depth of the tree, while breadth-first traversal uses memory proportional to the breadth of the tree 
(how many nodes there are on the "level" that has the most nodes).

Because the tree's breadth can as much as double each time it gets one level deeper, depth-first 
traversal is likely to be more space-efficient than breadth-first traversal, though they are strictly 
both O(n) additional space in the worst case. The space savings are obvious if we know our binary 
tree is balanced—its depth will be O(lg{n}) and its breadth will be O(n).

But we're not just storing the nodes themselves in memory, we're also storing the value from each 
ancestor and whether it should be less than or greater than the given node. Each node has O(n) 
ancestors (O(lg{n}) for a balanced binary tree), so that gives us O(n^2) additional memory cost 
(O(n*lg{n}) for a balanced binary tree). We can do better.

### Hint 3

Let's look at the inequalities we'd need to store for a given node.

Notice that we would end up testing that the blue node is <20, <30, and <50. Of course, <30 and <50 
are implied by <20. So instead of storing each ancestor, we can just keep track of a lowerBound and 
upperBound that our node's value must fit inside.
