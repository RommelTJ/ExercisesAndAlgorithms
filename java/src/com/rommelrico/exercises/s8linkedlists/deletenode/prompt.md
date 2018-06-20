### Delete Node

Delete a node from a singly-linked list, given only a variable pointing to that node.

The input could, for example, be the variable b below:

```
public class LinkedListNode {

    public int value;
    public LinkedListNode next;

    public LinkedListNode(int value) {
        this.value = value;
    }
}

LinkedListNode a = new LinkedListNode(1);
LinkedListNode b = new LinkedListNode(2);
LinkedListNode c = new LinkedListNode(3);

a.next = b;
b.next = c;

deleteNode(b);
```
