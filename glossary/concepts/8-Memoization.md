# Memoization

Memoization ensures that a method doesn't run for the same inputs more than once by keeping a record of the results for 
the given inputs (usually in a hash map).

For example, a simple recursive method for computing the nth Fibonacci number:

```
public static int fib(int n) {

    if (n < 0) {
        throw new IllegalArgumentException(
            "Index was negative. No such thing as a negative index in a series.");
    }

    // base cases
    if (n == 0 || n == 1) {
        return n;
    }

    System.out.printf("computing fib(%d)\n", n);
    return fib(n - 1) + fib(n - 2);
}
```

Will run on the same inputs multiple times:
```
// output for fib(5)
computing fib(5)
computing fib(4)
computing fib(3)
computing fib(2)
computing fib(2)
computing fib(3)
computing fib(2)
5
```

We can imagine the recursive calls of this method as a tree, where the two children of a node are the two recursive 
calls it makes. We can see that the tree quickly branches out of control: 
A binary tree showing the recursive calls of calling fib of 5. Every fib of n call calls fib of n minus 1 and fib of n 
minus 2. So calling fib of 5 calls fib of 4 and fib of 3, which keep calling fib of lower numbers until reaching the 
base cases fib of 1 or fib of 0.

To avoid the duplicate work caused by the branching, we can wrap the method in a class that stores an instance 
variable, memo, that maps inputs to outputs. Then we simply: 
1. check memo to see if we can avoid computing the answer for any given input, and
2. save the results of any calculations to memo.

```
import java.util.Map;
import java.util.HashMap;

class Fibber {

    private Map<Integer, Integer> memo = new HashMap<>();

    public int fib(int n) {

        if (n < 0) {
            throw new IllegalArgumentException(
                "Index was negative. No such thing as a negative index in a series.");

        // base cases
        } else if (n == 0 || n == 1) {
            return n;
        }

        // see if we've already calculated this
        if (memo.containsKey(n)) {
            System.out.printf("grabbing memo[%d]\n", n);
            return memo.get(n);
        }

        System.out.printf("computing fib(%d)\n", n);
        int result = fib(n - 1) + fib(n - 2);

        // memoize
        memo.put(n, result);

        return result;
    }
}
```

We save a bunch of calls by checking the memo:
```
// output of new Fibber().fib(5)
computing fib(5)
computing fib(4)
computing fib(3)
computing fib(2)
grabbing memo[2]
grabbing memo[3]
5
```

Now in our recurrence tree, no node appears more than twice:
A binary tree showing the memos and recursive calls of calling fib of 5. Starting with the calls for fib of n minus 1, 
fib of 5 calls fib of 4, which calls fib of 3, which calls fib of 2, which calls fib of 1. then, for the fib of n minus 
2 calls, fib of 5 gets the memo fib of 3, fib of 4 gets the memo fib of 2, fib of 3 gets the memo fib of 1, and fib of 
2 calls fib of 0.

Memoization is a common strategy for dynamic programming problems, which are problems where the solution is composed of 
solutions to the same problem with smaller inputs (as with the Fibonacci problem, above). The other common strategy for 
dynamic programming problems is going bottom-up, which is usually cleaner and often more efficient. 
