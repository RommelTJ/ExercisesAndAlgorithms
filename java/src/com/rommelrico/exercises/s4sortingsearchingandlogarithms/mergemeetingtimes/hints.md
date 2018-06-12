### Breakdown

 What if we only had two ranges? Let's take:

`[Meeting(1, 3), Meeting(2, 4)]`

These meetings clearly overlap, so we should merge them to give:

`[Meeting(1, 4)]`

But how did we know that these meetings overlap? 

### Hint 1

We could tell the meetings overlapped because the end time of the first one was after the start time of the second one! 
But our ideas of "first" and "second" are important here—this only works after we ensure that we treat the meeting that 
starts earlier as the "first" one.

How would we formalize this as an algorithm? Be sure to consider these edge cases:

1. The end time of the first meeting and the start time of the second meeting are equal. For example: 
`[Meeting(1, 2), Meeting(2, 3)]`
2. The second meeting ends before the first meeting ends. For example: `[Meeting(1, 5), Meeting(2, 3)]`

### Hint 2

Here's a formal algorithm:

1. We treat the meeting with earlier start time as "first," and the other as "second."
2. If the end time of the first meeting is equal to or greater than the start time of the second meeting, we merge the 
two meetings into one time range. The resulting time range's start time is the first meeting's start, and its end time 
is the later of the two meetings' end times.
3. Else, we leave them separate.

So, we could compare every meeting to every other meeting in this way, merging them or leaving them separate.

Comparing all pairs of meetings would take O(n^2) time. We can do better!

### Hint 3

If we're going to beat O(n^2) time, maybe we're going to get O(n) time? Is there a way to do this in one pass?

### Hint 4

It'd be great if, for each meeting, we could just try to merge it with the next meeting. But that's definitely not 
sufficient, because the ordering of our meetings is random. There might be a non-next meeting that the current meeting 
could be merged with. 

### Hint 5

What if we sorted our list of meetings by start time?

### Hint 6

Then any meetings that could be merged would always be adjacent!

So we could sort our meetings, then walk through the sorted list and see if each meeting can be merged with the one 
after it.

Sorting takes O(n lg n) time in the worst case. If we can then do the merging in one pass, that's another O(n) time, 
for O(n lg n) overall. That's not as good as O(n), but it's better than O(n^2).
