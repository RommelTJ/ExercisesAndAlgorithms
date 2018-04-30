# Big O Notation

## The idea behind big O notation

Big O notation is the language we use for talking about how long an algorithm takes to run. It's how we compare the 
efficiency of different approaches to a problem.

It's like math except it's an awesome, not-boring kind of math where you get to wave your hands through the details 
and just focus on what's basically happening.

With big O notation we express the runtime in terms of how quickly it grows relative to the input, as the input gets 
arbitrarily large.

Let's break that down:

1. **how quickly the runtime grows** — It's hard to pin down the exact runtime of an algorithm. It depends on the speed of 
the processor, what else the computer is running, etc. So instead of talking about the runtime directly, we use big O 
notation to talk about how quickly the runtime grows.
2. **relative to the input** — If we we're measuring our runtime directly, we could express our speed in seconds. Since 
we're measuring how quickly our runtime grows, we need to express our speed in terms of something else. With Big O 
notation, we use the size of the input, which we call "n." So we can say things like the runtime grows "on the order 
of the size of the input" (O(n)) or "on the order of the square of the size of the input" (O(n^2)).
3. **as the input gets arbitrarily large** — Our algorithm may have steps that seem expensive when n is small but are 
eclipsed eventually by other steps as n gets huge. For big O analysis, we care most about the stuff that grows fastest 
as the input grows, because everything else is quickly eclipsed as n gets very large. (If you know what an asymptote 
is, you might see why "big O analysis" is sometimes called "asymptotic analysis.")

If this seems abstract so far, that's because it is. Let's look at some examples.

## Examples

```
public static void printFirstItem(int[] items) {
    System.out.println(items[0]);
}
```
   
This method runs in O(1) time (or "constant time") relative to its input. The input array could be 1 item or 1,000 
items, but this method would still just require one "step."

```
public static void printAllItems(int[] items) {
    for (int item : items) {
        System.out.println(item);
    }
}
```

This method runs in O(n) time (or "linear time"), where n is the number of items in the array. If the array has 10 
items, we have to print 10 times. If it has 1,000 items, we have to print 1,000 times.

```
public static void printAllPossibleOrderedPairs(int[] items) {
    for (int firstItem : items) {
        for (int secondItem : items) {
            System.out.println(firstItem + ", " + secondItem);
        }
    }
}
```

Here we're nesting two loops. If our array has n items, our outer loop runs n times and our inner loop runs n times for 
each iteration of the outer loop, giving us n^2​​ total prints. Thus this method runs in O(n^2) time (or "quadratic 
time"). If the array has 10 items, we have to print 100 times. If it has 1,000 items, we have to print 1,000,000 times. 

##  N could be the actual input, or the size of the input
   
Both of these methods have O(n) runtime, even though one takes an integer as its input and the other takes an array:

```
public static void sayHiNTimes(int n) {
    for (int i = 0; i < n; i++) {
        System.out.println("hi");
    }
}
```

```
public static void printAllItems(int[] items) {
   for (int item : items) {
       System.out.println(item);
   }
}
```
   
So sometimes n is an actual number that's an input to our method, and other times n is the number of items in an input 
array (or an input map, or an input object, etc.). 

##  Drop the constants
   
This is why big O notation rules. When you're calculating the big O complexity of something, you just throw out the 
constants. So like:

```
public static void printAllItemsTwice(int[] items) {
   for (int item : items) {
       System.out.println(item);
   }

   // once more, with feeling
   for (int item : items) {
       System.out.println(item);
   }
}
```
   
This is O(2n), which we just call O(n).

```
public static void printFirstItemThenFirstHalfThenSayHi100Times(int[] items) {
   System.out.println(items[0]);

   int middleIndex = items.length / 2;
   int index = 0;

   while (index < middleIndex) {
       System.out.println(items[index]);
       index++;
   }

   for (int i = 0; i < 100; i++) {
       System.out.println("hi");
   }
}
```
   
This is O(1 + n/2 + 100), which we just call O(n).

Why can we get away with this? Remember, for big O notation we're looking at what happens as n gets arbitrarily large. 
As n gets really big, adding 100 or dividing by 2 has a decreasingly significant effect.

