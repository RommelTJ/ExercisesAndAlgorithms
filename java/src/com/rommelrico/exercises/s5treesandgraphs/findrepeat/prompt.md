### Find Repeat

We are given an array of integers where:
1. the integers are in the range 1..n
2. the array has a length of n+1

These properties mean the array must have at least 1 duplicate. Find a duplicate integer in 
O(n) time while keeping our space cost at O(1).

This is a tricky one to derive (unless you have a strong background in graph theory), so 
we'll get you started:

Imagine each item in the array as a node in a linked list. In any linked list, each node has 
a value and a "next" pointer. In this case:
* The value is the integer from the array.
* The "next" pointer points to the value-eth node in the list (numbered starting from 1). 
  For example, if our value was 3, the "next" node would be the third node.

Drawing pictures will help a lot with this one. Grab some paper and pencil (or a whiteboard, 
if you have one). 

