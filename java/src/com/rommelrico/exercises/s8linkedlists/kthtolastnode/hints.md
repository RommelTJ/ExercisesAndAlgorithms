### Breakdown

It might be tempting to iterate through the list until we reach the end and then walk backward k nodes.

But we have a singly linked list! We can’t go backward. What else can we do?

### Hint 1

What if we had the length of the list?

### Hint 2

Then we could calculate how far to walk, starting from the head, to reach the kth to last node.

If the list has n nodes, and our target is the kth to last node, then the distance from the head to 
the target is n−k. 

Well, we don't know the length of the list (n). But can we figure it out?

### Hint 3

Yes, we could iterate from the head to the tail and count the nodes!

Can you implement this approach in code?

### Hint 4

```
public static LinkedListNode kthToLastNode(int k, LinkedListNode head) {

    // STEP 1: get the length of the list
    // start at 1, not 0
    // else we'd fail to count the head node!
    int listLength = 1;
    LinkedListNode currentNode = head;

    // traverse the whole list,
    // counting all the nodes
    while (currentNode.next != null) {
        currentNode = currentNode.next;
        listLength += 1;
    }

    // STEP 2: walk to the target node
    // calculate how far to go, from the head,
    // to get to the kth to last node
    int howFarToGo = listLength - k;

    currentNode = head;
    for (int i = 0; i < howFarToGo; i++) {
        currentNode = currentNode.next;
    }

    return currentNode;
}
```

What are our time and space costs? 

### Hint 5

O(n) time and O(1) space, where n is the length of the list.

More precisely, it takes n steps to get the length of the list, and another n−k steps to reach the 
target node. In the worst case k=1, so we have to walk all the way from head to tail again to reach 
the target node. This is a total of 2n steps, which is O(n).

Can we do better?

### Hint 6

Mmmmaaaaaaybe.

The fact that we walk through our whole list once just to get the length, then walk through the list 
again to get to the kth to last element sounds like a lot of work. Perhaps we can do this in just one 
pass?

### Hint 7

What if we had like a "stick" that was k nodes wide. We could start it at the beginning of the list 
so that the left side of the stick was on the head and the right side was on the kth node.

Then we could slide the stick down the list until the right side hit the end. At that point, the left 
side of the stick would be on the kth to last node!

How can we implement this? Maybe it'll help to keep two pointers?

### Hint 8

We can allocate two variables that'll be references to the nodes at the left and right sides of the "stick"!

```
public static LinkedListNode kthToLastNode(int k, LinkedListNode head) {

    LinkedListNode leftNode  = head;
    LinkedListNode rightNode = head;

    // move rightNode to the kth node
    for (int i = 0; i < k - 1; i++) {
        rightNode = rightNode.next;
    }

    // starting with leftNode on the head,
    // move leftNode and rightNode down the list,
    // maintaining a distance of k between them,
    // until rightNode hits the end of the list
    while (rightNode.next != null) {
        leftNode  = leftNode.next;
        rightNode = rightNode.next;
    }

    // since leftNode is k nodes behind rightNode,
    // leftNode is now the kth to last node!
    return leftNode;
}
```

This'll work, but does it actually save us any time?

