### Breakdown

Our first thought might be to build our reversed list "from the beginning," starting with the head of the final 
reversed linked list.

The head of the reversed list will be the tail of the input list. To get to that node we'll have to walk 
through the whole list once (O(n) time). And that's just to get started.

That seems inefficient. Can we reverse the list while making just one walk from head to tail of the input list?

### Hint 1

We can reverse the list by changing the next pointer of each node. Where should each node's next pointer... point?

### Hint 2

Each node's next pointer should point to the previous node.

How can we move each node's next pointer to its previous node in one pass from head to tail of our current list?

