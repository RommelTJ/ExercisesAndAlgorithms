### Breakdown

The nth Fibonacci number is defined in terms of the two previous Fibonacci numbers, so this seems to lend itself to 
recursion.

```
fib(n) = fib(n - 1) + fib(n - 2);
```

Can you write up a recursive solution?

### Hint 1

As with any recursive method, we just need a base case and a recursive case:
1. Base case: n is 0 or 1. Return n.
2. Recursive case: Return fib(n - 1) + fib(n - 2).

```
public static int fibRecursive(int n) {
    if (n == 0 || n == 1) {
        return n;
    }
    return fibRecursive(n - 1) + fibRecursive(n - 2);
}
```

Okay, this'll work! What's our time complexity?

### Hint 2

It's not super obvious. We might guess nnn, but that's not quite right. Can you see why?

### Hint 3

Each call to fib() makes two more calls. Let's look at a specific example. Let's say n=5. If we call fib(5), how many 
calls do we make in total?

### Hint 4

Try drawing it out as a tree where each call has two child calls, unless it's a base case.

### Hint 5

We can notice this is a binary tree whose height is n, which means the total number of nodes is O(2^n).

So our total runtime is O(2^n). That's an "exponential time cost," since the n is in an exponent. Exponential costs 
are terrible. This is way worse than O(n^2) or even O(n^{100}).

Our recurrence tree above essentially gets twice as big each time we add 1 to n. So as n gets really big, our runtime 
quickly spirals out of control.

The craziness of our time cost comes from the fact that we're doing so much repeat work. How can we avoid doing this 
repeat work? 

### Hint 6

We can memoize!
 
Let's wrap fib() in a class with an instance variable where we store the answer for any n that we compute:

```
import java.util.Map;
import java.util.HashMap;

public class Fibber {

    private Map<Integer, Integer> memo = new HashMap<>();

    public int fib(int n) {

        // edge case: negative index
        if (n < 0) {
            throw new IllegalArgumentException("Index was negative. No such thing as a negative index in a series.");
        }

        // base case: 0 or 1
        else if (n == 0 || n == 1) {
            return n;
        }

        // see if we've already calculated this
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int result = fib(n - 1) + fib(n - 2);

        // memoize
        memo.put(n, result);

        return result;
    }
}
```

What's our time cost now?

### Hint 7

The computer will build up a call stack with fib(5), fib(4), fib(3), fib(2), fib(1). Then we'll start returning, and on 
the way back up our tree we'll be able to compute each node's 2nd call to fib() in constant time by just looking in the 
memo. n time in total.

What about space? memo takes up n space. Plus we're still building up a call stack that'll occupy n space. Can we avoid 
one or both of these space expenses?

### Hint 8

Look again at that tree. Notice that to calculate fib(5) we worked "down" to fib(4), fib(3), fib(2), etc.

What if instead we started with fib(0) and fib(1) and worked "up" to n?

