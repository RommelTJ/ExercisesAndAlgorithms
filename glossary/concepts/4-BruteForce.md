# Brute Force

A brute force algorithm finds a solution by trying all possible answers and picking the best one.

Say you're a cashier and need to give someone 67 cents (US) using as few coins as possible. How would you do it?

You could try running through all potential coin combinations and pick the one that adds to 67 cents using the fewest 
coins. That's a brute force algorithm, since you're trying all possible ways to make change.

Here are a few other brute force algorithms:
* Trying to fit as many overlapping meetings as possible in a conference room? Run through all possible schedules, and 
pick the schedule that fits the most meetings in the room.
* Trying to find the cheapest route through a set of cities? Try all possible routes and pick the cheapest one.
* Looking for a minimum spanning tree in a graph? Try all possible sets of edges, and pick the cheapest set that's also 
a tree.

Brute force solutions are usually very slow since they involve testing a huge number of possible answers.

Brute force approaches are rarely the most efficient. Other approaches, like greedy algorithms or dynamic programming 
tend to be faster.

Even so, talking through a brute force solution can be a good first step in a coding interview. It's usually pretty 
easy to derive, so it allows you to quickly make progress and come up with something that works. From there, you have 
some helpful boundaries for refining your algorithm—you're only interested in solutions that are faster (and/or more 
space efficient) than the brute force solution you've already come up with. 
