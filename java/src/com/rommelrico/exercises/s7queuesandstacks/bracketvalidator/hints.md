### Breakdown
    
We can use a greedy approach to walk through our string character by character, making sure the string 
validates "so far" until we reach the end.

What do we do when we find an opener or closer?

### Hint 1

Well, we'll need to keep track of our openers so that we can confirm they get closed properly. What 
data structure should we use to store them? When choosing a data structure, we should start by deciding 
on the properties we want. In this case, we should figure out how we will want to retrieve our openers 
from the data structure! So next we need to know: what will we do when we find a closer?  

### Hint 2

Suppose we're in the middle of walking through our string, and we find our first closer:
```
[ { ( ) ] . . . .
      ^
```

How do we know whether or not that closer in that position is valid? 

### Hint 3

A closer is valid if and only if it's the closer for the most recently seen, unclosed opener. In this 
case, '(' was seen most recently, so we know our closing ')' is valid.

So we want to store our openers in such a way that we can get the most recently added one quickly, and 
we can remove the most recently added one quickly (when it gets closed). Does this sound familiar?

### Hint 4

What we need is a stack! 

