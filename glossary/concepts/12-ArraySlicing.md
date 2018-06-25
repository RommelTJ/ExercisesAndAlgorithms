# Array Slicing

Array slicing involves taking a subset from an array and allocating a new array with those elements.

In Java you can create a new array of the elements in myArray, from startIndex to endIndex (exclusive), like this:
```
Arrays.copyOfRange(myArray, startIndex, endIndex);
```

Careful: there's a hidden time and space cost here! It's tempting to think of slicing as just 
"getting elements," but in reality you are:
```
1. allocating a new array
2. copying the elements from the original array to the new array
```

This takes O(n)O(n)O(n) time and O(n)O(n)O(n) space, where nnn is the number of elements in the 
resulting array.

That's a bit easier to see when you save the result of the slice to a variable:
```
int[] tailOfArray = Arrays.copyOfRange(myArray, 1, myArray.length);
```

But a bit harder to see when you don't save the result of the slice to a variable:

```
return Arrays.copyOfRange(myArray, 1, myArray.length);
// whoops, I just spent O(n) time and space!
```

```
for (int item : Arrays.copyOfRange(myArray, 1, myArray.length)) {
    // whoops, I just spent O(n) time and space!
}
```

So keep an eye out. Slice wisely. 

