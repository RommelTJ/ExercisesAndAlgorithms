### Breakdown

Let's break this problem into subproblems. How can we divide this problem into smaller parts?

### Hint 1

We could look at the two rectangles’ "horizontal overlap" or "x overlap" separately from 
their "vertical overlap" or "y overlap."

Lets start with a helper method findXOverlap().

### Hint 2

Need help finding the x overlap?

Since we’re only working with the x dimension, we can treat the two rectangles' widths as 
ranges on a 1-dimensional number line.

What are the possible cases for how these ranges might overlap or not overlap? Draw out 
some examples!

### Hint 3

There are four relevant cases:

1. The ranges partially overlap,
2. One range is completely contained in the other, 
3. The ranges don't overlap, and 
4. The ranges "touch" at a single point. 

Let's start with the first 2 cases. How do we compute the overlapping range?

### Hint 4

One of our ranges starts "further to the right" than the other. We don't know ahead of 
time which one it is, but we can check the starting points of each range to see which one 
has the highestStartPoint. That highestStartPoint is always the left-hand side of the 
overlap, if there is one.

Not convinced? Draw some examples!

Similarly, the right-hand side of our overlap is always the lowestEndPoint. That may or 
may not be the end point of the same input range that had the highestStartPoint—compare 
cases (1) and (2).

This gives us our x overlap! So we can handle cases (1) and (2). How do we know when 
there is no overlap?

### Hint 5

If highestStartPoint > lowestEndPoint, the two rectangles do not overlap.

But be careful—is it just greater than or is it greater than or equal to?

### Hint 6

It depends how we want to handle case (4) above.

If we use greater than, we treat case (4) as an overlap. This means we could end up 
returning a rectangle with zero width, which ... may or may not be what we're looking 
for. You could make an argument either way.

Let's say a rectangle with zero width (or zero height) isn't a rectangle at all, so we 
should treat that case as "no intersection."

Can you finish findXOverlap()?

### Hint 7

Here's one way to do it:

```
public class XOverlap {

    private int startPoint;
    private int width;

    public XOverlap(int startPoint, int width) {
        this.startPoint = startPoint;
        this.width = width;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public int getWidth() {
        return width;
    }
}

public XOverlap findXOverlap(int x1, int width1, int x2, int width2) {

    // find the highest ("rightmost") start point and lowest ("leftmost") end point
    int highestStartPoint = Math.max(x1, x2);
    int lowestEndPoint = Math.min(x1 + width1, x2 + width2);

    // return empty overlap if there is no overlap
    if (highestStartPoint >= lowestEndPoint) {
        return new XOverlap(0, 0);
    }

    // compute the overlap width
    int overlapWidth = lowestEndPoint - highestStartPoint;

    return new XOverlap(highestStartPoint, overlapWidth);
}
```

How can we adapt this for the rectangles’ ys and heights? 

### Hint 8

Can we just make one findRangeOverlap() method that can handle x overlap and y overlap?

### Hint 9

Yes! We simply use more general parameter names:

```
public class RangeOverlap {

    private int startPoint;
    private int length;

    public RangeOverlap(int highestStartPoint, int overlapLength) {
        this.startPoint = highestStartPoint;
        this.length = overlapLength;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public int getLength() {
        return length;
    }
}

public RangeOverlap findRangeOverlap(int point1, int length1, int point2, int length2) {

    // find the highest start point and lowest end point.
    // the highest ("rightmost" or "upmost") start point is
    // the start point of the overlap.
    // the lowest end point is the end point of the overlap.
    int highestStartPoint = Math.max(point1, point2);
    int lowestEndPoint = Math.min(point1 + length1, point2 + length2);

    // return empty overlap if there is no overlap
    if (highestStartPoint >= lowestEndPoint) {
        return new RangeOverlap(0, 0);
    }

    // compute the overlap length
    int overlapLength = lowestEndPoint - highestStartPoint;

    return new RangeOverlap(highestStartPoint, overlapLength);
}
```

We've solved our subproblem of finding the x and y overlaps! Now we just need to put the 
results together. 
