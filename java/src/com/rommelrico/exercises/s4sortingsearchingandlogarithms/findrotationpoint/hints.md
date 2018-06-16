### Breakdown

The array is mostly ordered. We should exploit that fact.

### Hint 1

What's a common algorithm that takes advantage of the fact that an array is sorted to find an 
item efficiently? 

### Hint 2

Binary search! We can write an adapted version of binary search for this.

### Hint 3

In each iteration of our binary search, how do we know if the rotation point is to our left or to 
our right? 

### Hint 4

Try drawing out an example array!

### Hint 5

```
[ "k","v","a","b","c","d","e","g","i" ]
                   ^
```

If our "current guess" is the middle item, which is 'c' in this case, is the rotation point to 
the left or to the right? How do we know? 

### Hint 6

Notice that every item to the right of our rotation point is always alphabetically before the 
first item in the array.

So the rotation point is to our left if the current item is less than the first item. Else it's 
to our right.
