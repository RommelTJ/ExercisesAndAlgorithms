### Breakdown
    
A just-in-time approach is to have getMax() simply walk through the stack (popping all the elements off 
and then pushing them back on) to find the max element. This takes O(n) time for each call to getMax(). 
But we can do better.

### Hint 1

To get O(1) time for getMax(), we could store the max integer as a member variable (call it max). But 
how would we keep it up to date? 

### Hint 2

For every push(), we can check to see if the item being pushed is larger than the current max, assigning 
it as our new max if so. But what happens when we pop() the current max? We could re-compute the current 
max by walking through our stack in O(n) time. So our worst-case runtime for pop() would be O(n). We can 
do better. 

### Hint 3

What if when we find a new current max (newMax), instead of overwriting the old one (oldMax) we held onto 
it, so that once newMax was popped off our stack we would know that our max was back to oldMax? 

### Hint 4

What data structure should we store our set of maxs in? We want something where the last item we put in 
is the first item we get out ("last in, first out"). 

### Hint 5

We can store our maxs in another stack!
