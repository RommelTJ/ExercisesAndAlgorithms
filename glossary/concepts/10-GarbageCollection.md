# Garbage Collection

A garbage collector automatically frees up memory that a program isn't using anymore.

For example, say we did this in Java:
```
public static int getMin(int[] nums) {
    // NOTE: this is *not* the fastest way to get the min!
    int[] numsSorted = Arrays.copyOf(nums, nums.length);
    Arrays.sort(numsSorted);
    return numsSorted[0];
}

int[] myNums = new int[] {5, 3, 1, 4, 6};
System.out.println(getMin(myNums));
```

Look at numsSorted in getMin(). We allocate that whole array inside our method, and once the method 
returns we don't need the array anymore. In fact, once the method returns we don't have any 
references to it anymore!

What happens to that array in memory? The Java garbage collector will notice we don't need it 
anymore and free up that space.

Some languages, like C, don't have a garbage collector. So we need to manually free up any memory 
we've allocated once we're done with it:
```
// make a string that can hold 15 characters
// including the terminating null byte ('\0')
str = malloc(15);

// ... do some stuff with it ...

// we're done. free that memory!
free(str);
```

We sometimes call this manual memory management.

Some languages, like C++, have both manual and automatic memory management. 
