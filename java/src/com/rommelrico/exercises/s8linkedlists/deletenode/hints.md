### Breakdown

It might be tempting to try to traverse the list from the beginning until we encounter the node we want to delete. 
But in this situation, we don't know where the head of the list is—we only have a reference to the node we want to 
delete.

But hold on—how do we even delete a node from a linked list in general, when we do have a reference to the first node? 

### Hint 1

We'd change the previous node's pointer to skip the node we want to delete, so it just points straight to the node 
after it. 

### Hint 2

So we need a way to skip over the current node and go straight to the next node. But we don't even have access to the 
previous node!

Other than rerouting the previous node's pointer, is there another way to skip from the previous pointer's value to the 
next pointer's value?

### Hint 3

What if we modify the current node instead of deleting it?

