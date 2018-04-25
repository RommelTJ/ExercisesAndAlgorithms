### Breakdown

Because a cycle could result from the last node linking to the first node, we might need to look at 
every node before we even see the start of our cycle again. So it seems like we can’t do better than O(n) 
runtime.

How can we track the nodes we've already seen?

### Hint 1

Using a hash set, we could store all the nodes we've seen so far. The algorithm is simple:

1. If the current node is already in our hash set, we have a cycle. Return true.
2. If the current node is null we've hit the end of the list. Return false.
3. Else throw the current node in our hash set and keep going.

What are the time and space costs of this approach? Can we do better?

### Hint 2

Our runtime is O(n), the best we can do. But our space cost is also O(n). Can we get our space cost down 
to O(1) by storing a constant number of nodes?

### Hint 3

Think about a looping list and a linear list. What happens when you traverse one versus the other?

### Hint 4

A linear list has an end — a node that doesn't have a next node. But a looped list will run forever. We 
know we don't have a loop if we ever reach an end node, but how can we tell if we've run into a loop?

### Hint 5

We can't just run our method for a really long time, because we'd never really know with certainty if we 
were in a loop or just a really long list.

Imagine that you're running on a long, mountainous running trail that happens to be a loop. What are some 
ways you can tell you're running in a loop?

### Hint 6

One way is to look for landmarks. You could remember one specific point, and if you pass that point 
again, you know you’re running in a loop. Can we use that principle here?

### Hint 7

Well, our cycle can occur after a non-cyclical "head" section in the beginning of our linked list. So 
we'd need to make sure we chose a "landmark" node that is in the cyclical "tail" and not in the 
non-cyclical "head." That seems impossible unless we already know whether or not there's a cycle...

Think back to the running trail. Besides landmarks, what are some other ways you could tell you’re 
running in a loop? What if you had another runner? (Remember, it’s a singly-linked list, so no running 
backwards!)

### Hint 8

A tempting approach could be to have the other runner stop and act as a "landmark," and see if you pass 
her again. But we still have the problem of making sure our "landmark" is in the loop and not in the 
non-looping beginning of the trail.

What if our "landmark" runner moves continuously but slowly?

### Hint 9

If we sprint quickly down the trail and the landmark runner jogs slowly, we will eventually "lap" (catch 
up to) the landmark runner!

But what if there isn't a loop?

### Hint 10

Then we (the faster runner) will simply hit the end of the trail (or linked list).

So let's make two variables, slowRunner and fastRunner. We'll start both on the first node, and every 
time slowRunner advances one node, we’ll have fastRunner advance two nodes.

If fastRunner catches up with slowRunner, we know we have a loop. If not, eventually fastRunner will hit 
the end of the linked list and we'll know we don't have a loop.

This is a complete solution! Can you code it up?

Make sure the method eventually terminates in all cases!
