public class DiceSimulation {
    private int trials = 0;
    private int min = Integer.MAX_VALUE;
    private int max = 0;
    private int sum = 0;

    private Die die1 = new Die();
    private Die die2 = new Die();

    // A trial = tossing a pair of dice until sum of 7 is obtained.
    // A result = sum of all tosses, but not including, the toss that resulted in 7.
    public int runTrial() {
        int trialSum = 0;
        int pairSum = 0;
        while (7 != (pairSum = die1.toss() + die2.toss())) {
            trialSum += pairSum;
        }
        if (trialSum > max) {
            max = trialSum;
        }
        if (trialSum < min) {
            min = trialSum;
        }
        sum += trialSum;
        trials++;
        return trialSum;
    }

    public void report() {
        System.out.println("After " + trials + " simulations: ");
        System.out.println("Biggest sum: " + max);
        System.out.println("Smallest sum: " + min);
        System.out.println("The average is: " + (double)sum/trials);
    }

    public static void main(String[] args) {
        int trials = Integer.parseInt(args[0]);
        DiceSimulation sim = new DiceSimulation();
        for (int count=0; count<trials; count++) {
            sim.runTrial();
        }
        sim.report();
    }
}
