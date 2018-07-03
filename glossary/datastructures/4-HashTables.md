# Hash Table

## Quick reference

|        | Average | Worst Case |
| ------ | ------- | ---------- |
| space  | O(n)    | O(n)       |
| insert | O(1)    | O(n)       |
| lookup | O(1)    | O(n)       |
| delete | O(1)    | O(n)       |

A hash table organizes data so you can quickly look up values for a given key.

**Strengths**:
1. Fast lookups. Lookups take O(1) time on average.
2. Flexible keys. Most data types can be used for keys, as long as they're hashable.

**Weaknesses**:
1. Slow worst-case lookups. Lookups take O(n) time in the worst case.
2. Unordered. Keys aren't stored in a special order. If you're looking for the smallest key, the 
largest key, or all the keys in a range, you'll need to look through every key to find it.
3. Single-directional lookups. While you can look up the value for a given key in O(1) time, 
looking up the keys for a given value requires looping through the whole dataset—O(n) time.

