package com.rommelrico.exercises.q7rectangularlove;

/**
 * Solution
 *
 * We divide the problem into two halves:
 * 1. The intersection along the x-axis
 * 2. The intersection along the y-axis
 *
 * Both problems are basically the same as finding the intersection of two "ranges" on a 1-dimensional number line.
 *
 * So we write a helper method findRangeOverlap() that can be used to find both the x overlap and the y overlap, and
 * we use it to build the rectangular overlap.
 *
 * Complexity
 *
 * O(1) time and O(1) space.
 *
 * What We Learned
 *
 * This is an interesting one because the hard part isn't the time or space optimization—it's getting something that
 * works and is readable.
 *
 * For problems like this, I often see candidates who can describe the strategy at a high level but trip over
 * themselves when they get into the details.
 *
 * Don't let it happen to you. To keep your thoughts clear and avoid bugs, take time to:
 * 1. Think up and draw out all the possible cases. Like we did with the ways ranges can overlap.
 * 2. Use very specific and descriptive variable names.
 *
 */
public class RectangularLove {

    private static class RangeOverlap {

        private int startPoint;
        private int length;

        public RangeOverlap(int startPoint, int length) {
            this.startPoint = startPoint;
            this.length = length;
        }

        public int getStartPoint() {
            return startPoint;
        }

        public int getLength() {
            return length;
        }
    }

    private static RangeOverlap findRangeOverlap(int point1, int length1, int point2, int length2) {

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

    public static Rectangle findRectangularOverlap(Rectangle rect1, Rectangle rect2) {

        // get the x and y overlap points and lengths
        RangeOverlap xOverlap = findRangeOverlap(rect1.getLeftX(), rect1.getWidth(),
                rect2.getLeftX(), rect2.getWidth());
        RangeOverlap yOverlap = findRangeOverlap(rect1.getBottomY(), rect1.getHeight(),
                rect2.getBottomY(), rect2.getHeight());

        // return "zero" rectangle if there is no overlap
        if (xOverlap.getLength() == 0 || yOverlap.getLength() == 0) {
            return new Rectangle(0, 0, 0, 0);
        }

        return new Rectangle(
                xOverlap.getStartPoint(),
                yOverlap.getStartPoint(),
                xOverlap.getLength(),
                yOverlap.getLength()
        );
    }

    public static void main(String[] args) {
        Rectangle rectangle1 = new Rectangle(0, -20, 20, 20);
        Rectangle rectangle2 = new Rectangle(0, -20, 10, 50);
        Rectangle love = findRectangularOverlap(rectangle1, rectangle2);
        System.out.println(love);
    }

}
