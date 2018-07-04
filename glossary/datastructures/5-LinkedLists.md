# Linked Lists

## Quick reference

|         | Worst Case |
| ------- | ---------- |
| space   | O(n)       |
| prepend | O(1)       |
| append  | O(1)       |
| lookup  | O(n)       |
| insert  | O(n)       |
| delete  | O(n)       |

A linked list organizes items sequentially, with each item storing a pointer to the next one.

Picture a linked list like a chain of paperclips linked together. It's quick to add another 
paperclip to the top or bottom. It's even quick to insert one in the middle — just disconnect the 
chain at the middle link, add the new paperclip, then reconnect the other half.

An item in a linked list is called a node. The first node is called the head. The last node is 
called the tail.

Confusingly, sometimes people use the word tail to refer to "the whole rest of the list after the 
head."

Unlike an array, consecutive items in a linked list are not necessarily next to each other in memory.

**Strengths**:
1. Fast operations on the ends. Adding elements at either end of a linked list is O(1). Removing 
the first element is also O(1).
2. Flexible size. There's no need to specify how many elements you're going to store ahead of 
time. You can keep adding elements as long as there's enough space on the machine.

**Weaknesses**:
1. Costly lookups. To access or edit an item in a linked list, you have to take O(i) time to walk 
from the head of the list to the ith item.

**Uses**:
1. Stacks and queues only need fast operations on the ends, so linked lists are ideal.

