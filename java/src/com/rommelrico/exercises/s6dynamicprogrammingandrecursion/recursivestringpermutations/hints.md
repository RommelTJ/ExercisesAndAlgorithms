### Breakdown

Let's break the problem into subproblems. How could we re-phrase the problem of getting all 
permutations for "cats" in terms of a smaller but similar subproblem?
 
### Hint 1

Let's make our subproblem be getting all permutations for all characters except the last one. 
If we had all permutations for "cat," how could we use that to generate all permutations for 
"cats"? 

### Hint 2

We could put the "s" in each possible position for each possible permutation of "cat"!

These are our permutations of "cat":
```
cat
cta
atc
act
tac
tca
```

For each of them, we add "s" in each possible position. So for "cat":
```
cat
    scat
    csat
    cast
    cats
```

And for "cta":
```
cta
    scta
    csta
    ctsa
    ctas
```

And so on.


### Hint 3

Now that we can break the problem into subproblems, we just need a base case and we have a 
recursive algorithm! 
