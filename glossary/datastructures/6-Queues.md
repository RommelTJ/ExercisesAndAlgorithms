# Queue

## Quick reference

|           | Worst Case |
| --------- | ---------- |
| space     | O(n)       |
| enqueue   | O(1)       |
| dequeue   | O(1)       |
| peek      | O(1)       |

A queue stores items in a first-in, first-out (FIFO) order.

**Strengths**:
1. Fast operations. All queue operations take O(1) time.

**Uses**:
1. Breadth-first search uses a queue to keep track of the nodes to visit next.
2. Printers use queues to manage jobs — jobs get printed in the order they're submitted.
3. Web servers use queues to manage requests — page requests get fulfilled in the order they're 
received.
4. Processes wait in the CPU scheduler's queue for their turn to run.

## Implementation

Queues are easy to implement with linked lists:
1. To enqueue, insert at the tail of the linked list.
2. To dequeue, remove at the head of the linked list.

