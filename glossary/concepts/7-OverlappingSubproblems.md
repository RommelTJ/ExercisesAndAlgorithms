# Overlapping Subproblems

A problem has overlapping subproblems if finding its solution involves solving the same subproblem multiple times.

As an example, let's look at the Fibonacci sequence (the series where each number is the sum of the two previous ones — 
0, 1, 1, 2, 3, 5, 8, ...).

If we wanted to compute the nnnth Fibonacci number, we could use this simple recursive algorithm:
```
public static int fib(int n) {
    if (n == 0 || n == 1) {
        return n;
    }
    return fib(n-1) + fib(n-2);
}
```

We'd call fib(n-1) and fib(n-2) subproblems of fib(n).

Now let's look at what happens when we call fib(5):
A binary tree showing the recursive calls of calling fib of 5. Every fib of n call calls fib of n minus 1 and fib of n 
minus 2. So calling fib of 5 calls fib of 4 and fib of 3, which keep calling fib of lower numbers until reaching the 
base cases fib of 1 or fib of 0.

Our method ends up recursively calling fib(2) three times. So the problem of finding the nth Fibonacci number has 
overlapping subproblems. 
