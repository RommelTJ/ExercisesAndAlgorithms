### Breakdown

The first thing we want to optimize is our getter methods (per the instructions).

Our first thought might be to throw our temperatures into an array or linked list as 
they come in. With this method, getting the maxTemp and minTemp would take O(n) time. 
It would also cost us O(n) space. But we can do better.

### Hint 1

What if we kept track of the maxTemp and minTemp as each new number was inserted?

### Hint 2

That's easy enough:

```
public class TempTracker {

    private int minTemp = Integer.MAX_VALUE;
    private int maxTemp = Integer.MIN_VALUE;

    public void insert(int temperature) {
        minTemp = Math.min(minTemp, temperature);
        maxTemp = Math.max(maxTemp, temperature);
    }

    public int getMax() {
        return maxTemp;
    }

    public int getMin() {
        return minTemp;
    }
}
```

This wins us O(1) time for getMax() and getMin(), while keeping O(1) time for insert() 
and removing the need to store all the values.

Can we do something similar for getMean()? 

### Hint 3

Unlike with minTemp and maxTemp, the new temp and the previous mean won't give us enough 
information to calculate the new mean. What other information will we need to track?

### Hint 4

To calculate a mean we need to know:

* How many values there are
* The sum of all the values

### Hint 5

So we can augment our class to keep track of the numberOfReadings and totalSum. Then 
we can compute the mean as values are inserted:

```
public class TempTracker {

    // for mean
    private int numberOfReadings;
    private long totalSum;
    private double mean;

    // for min and max
    private int minTemp = Integer.MAX_VALUE;
    private int maxTemp = Integer.MIN_VALUE;

    public void insert(int temperature) {

        // for mean
        numberOfReadings++;
        totalSum += temperature;
        mean = (double) totalSum / numberOfReadings;

        // for min and max
        minTemp = Math.min(minTemp, temperature);
        maxTemp = Math.max(maxTemp, temperature);
    }

    public int getMax() {
        return maxTemp;
    }

    public int getMin() {
        return minTemp;
    }

    public double getMean() {
        return mean;
    }
}
```

Can we do something similar for the mode? What other information will we need to track 
to compute the mode?

### Hint 6

To calculate the mode, we need to know how many times each value has been inserted.

How can we track this? What data structures should we use?
