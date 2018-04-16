# How to get unstuck

## When you’re stuck on getting started

1) Write a sample input on the whiteboard and turn it into the correct output "by hand." Notice the process you use. 
Look for patterns, and think about how to implement your process in code.

Trying to reverse a string? Write “hello” on the board. Reverse it “by hand”—draw arrows from each character’s current 
position to its desired position. 

2) Solve a simpler version of the problem. Remove or simplify one of the requirements of the problem. Once you have a 
solution, see if you can adapt that approach for the original question.

Trying to find the k-largest element in a set? Walk through finding the largest element, then the second largest, then 
the third largest. Generalizing from there to find the k-largest isn’t so bad.

3) Start with an inefficient solution. Even if it feels stupidly inefficient, it’s often helpful to start with 
something that’ll return the right answer. From there, you just have to optimize your solution. Explain that this is 
only your first idea, and that you suspect there are faster solutions.

Suppose you were given two lists of sorted numbers and asked to find the median of both lists combined. It’s messy, 
but you could simply:

    1. Concatenate the arrays together into a new array.
    2. Sort the new array.
    3. Return the value at the middle index.

Notice that you could’ve also arrived at this algorithm by using trick (2): Solve a simpler version of the problem. 
"How would I find the median of one sorted list of numbers? Just grab the item at the middle index. Now, can I adapt 
that approach for getting the median of two sorted lists?"

