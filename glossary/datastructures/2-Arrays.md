# Arrays

## Quick reference
|        | Worst Case  |
| ------ | ----------- |
| space  | O(n)        |
| lookup | O(1)        |
| append | O(1)        |
| insert | O(n)        |
| delete | O(n)        |

An array organizes items sequentially, one after another in memory.

Each position in the array has an index, starting at 0.

**Strengths**:
* Fast lookups. Retrieving the element at a given index takes O(1) time, regardless of the length 
of the array.
* Fast appends. Adding a new element at the end of the array takes O(1) time.

**Weaknesses**:
* Fixed size. You need to specify how many elements you're going to store in your array ahead of 
time. (Unless you're using a fancy dynamic array.)
* Costly inserts and deletes. You have to "scoot over" the other elements to fill in or close 
gaps, which takes worst-case O(n) time.

## Java Implementation

Here's what arrays look like in Java.
```
// instantiate an array that holds 10 integers
int gasPrices[] = new int[10];

gasPrices[0] = 346;
gasPrices[1] = 360;
gasPrices[2] = 354;
```

## Inserting

If we want to insert something into an array, first we have to "scoot over" everything starting at 
the index we're inserting into, to make space.

In the worst case we're inserting into the 0th index in the array (prepending), so we have to 
"scoot over" everything in the array. That's O(n) time.

## Deleting

Array elements are stored adjacent to each other. So when we remove an element, we have to "scoot 
over" all the elements after it to fill in the gap. 

In the worst case we're deleting the 0th item in the array, so we have to "scoot over" everything 
else in the array. That's O(n) time.

## Data structures built on arrays

Arrays are the building blocks for lots of other, more complex data structures.

Don't want to specify the size of your array ahead of time? One option: use a dynamic array.

Want to look up items by something other than an index? Use a hash map.

