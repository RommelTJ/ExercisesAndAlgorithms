# Stack

## Quick reference

|       | Worst Case |
| ----- | ---------- |
| space | O(n)       |
| push  | O(1)       |
| pop   | O(1)       |
| peek  | O(1)       |

A stack stores items in a last-in, first-out (LIFO) order.

Picture a pile of dirty plates in your sink. As you add more plates, you bury the old ones further 
down. When you take a plate off the top to wash it, you're taking the last plate you put in. 
"Last in, first out."

**Strengths**:
1. Fast operations. All stack operations take O(1) time.

**Uses**:
1. The call stack is a stack that tracks function calls in a program. When a function returns, 
which function do we "pop" back to? The last one that "pushed" a function call.
2. Depth-first search uses a stack (sometimes the call stack) to keep track of which nodes to 
visit next.
3. String parsing—stacks turn out to be useful for several types of string parsing.

