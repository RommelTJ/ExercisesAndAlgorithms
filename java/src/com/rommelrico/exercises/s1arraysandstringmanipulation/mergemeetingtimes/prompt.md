### Merge Meeting Times

Your company built an in-house calendar tool called HiCal. You want to add a feature to see the times in a day when 
everyone is available.

To do this, you’ll need to know when any team is having a meeting. In HiCal, a meeting is stored as an object of a 
Meeting class with integer variables startTime and endTime. These integers represent the number of 30-minute blocks 
past 9:00am.

For example:  
`new Meeting(2, 3);  // meeting from 10:00 – 10:30 am`  
`new Meeting(6, 9);  // meeting from 12:00 – 1:30 pm`  

Write a method mergeRanges() that takes a list of meeting time ranges and returns a list of condensed ranges.
For example, given:  
`[Meeting(0, 1), Meeting(3, 5), Meeting(4, 8), Meeting(10, 12), Meeting(9, 10)]`  
your method would return:  
`[Meeting(0, 1), Meeting(3, 8), Meeting(9, 12)]`  

Do not assume the meetings are in order. The meeting times are coming from multiple teams.

Write a solution that's efficient even when we can't put a nice upper bound on the numbers representing our time 
ranges. Here we've simplified our times down to the number of 30-minute slots past 9:00 am. But we want the method 
to work even for very large numbers, like Unix timestamps. In any case, the spirit of the challenge is to merge
meetings where startTime and endTime don't have an upper bound.
