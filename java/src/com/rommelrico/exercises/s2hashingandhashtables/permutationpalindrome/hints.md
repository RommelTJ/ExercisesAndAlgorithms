### Breakdown

The brute force approach would be to check every permutation of the input string to see if it is 
a palindrome.

What would be the time cost? For a string of length n, there are n! permutations (n choices for 
the first character, times n−1 choices for the second character, etc). Checking each length-n 
permutation to see if it's a palindrome involves checking each character, taking O(n) time. That 
gives us O(n!*n) time overall. We can do better!

### Hint 1

Let's try rephrasing the problem. How can we tell if any permutation of a string is a palindrome? 

### Hint 2

Well, how would we check that a string is a palindrome? We could use the somewhat-common "keep 
two pointers" pattern. We'd start a pointer at the beginning of the string and a pointer at the 
end of the string, and check that the characters at those pointers are equal as we walk both 
pointers towards the middle of the string.
```
civic
^   ^

civic
 ^ ^

civic
  ^
```

Can we adapt the idea behind this approach to checking if any permutation of a string is a 
palindrome?

### Hint 3

Notice: we're essentially checking that each character left of the middle has a corresponding 
copy right of the middle. 

### Hint 4

We can simply check that each character appears an even number of times (unless there is a middle 
character, which can appear once or some other odd number of times). This ensures that the 
characters can be ordered so that each char on the left side of the string has a matching char on 
the right side of the string.

But we'll need a data structure to keep track of the number of times each character appears. What 
should we use?

### Hint 5

We could use a hash map. (Tip: using a hash map is the most common way to get from a brute force 
approach to something more clever. It should always be your first thought.)

### Hint 6

So we’ll go through all the characters and track how many times each character appears in the 
input string. Then we just have to make sure no more than one of the characters appears an odd 
numbers of times.

That will give us a runtime of O(n), which is the best we can do since we have to look at a 
number of characters dependent on the length of the input string.

Ok, so we've reached our best run time. But can we still clean our solution up a little?

### Hint 7

We don't really care how many times a character appears in the string, we just need to know 
whether the character appears an even or odd number of times.

### Hint 8

What if we just track whether or not each character appears an odd number of times? Then we can 
map characters to booleans. This will be more explicit (we don't have to check each number's 
parity, we already have booleans) and we'll avoid the risk of integer overflow if some 
characters appear a high number of times.

Can we take this a step further and clean it up even more?

### Hint 9

Even more specifically than whether characters appear an even or odd number of times, we really 
just need to know there isn't more than one character that appears an odd number of times.

What if we only track the characters that appear an odd number of times? Is there a data 
structure even simpler than a hash map we could use?

### Hint 10

We could use a hash set, adding and removing characters as we look through the input string, so 
the hash set always only holds the characters that appear an odd number of times.
