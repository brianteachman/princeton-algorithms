/*-----------------------------------------------------------------------------
 *  Author:        Brian Teachman
 *  Written:       11/2/2017
 *  Last updated:  11/6/2017
 *
 *  Compilation:   javac PercolationStats.java
 *  Execution:     java PercolationStats <n> <T>
 *
 *  Project 1: Percolation, using quick union find data type. Determines if a
 *             path through an nxn matrix can be found (percolates).
 *
 *  Run analysis of Percolation
 *---------------------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE = 1.96;
    private final int size;
    private final int trials;
    private final double mean;
    private final double stdDev;
//    private double[] runtimes;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int T) {
        if (n < 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        trials = T;
//        runtimes = new double[trials];
        double[] pvals = new double[trials];
        for (int i = 0; i < trials; i++) {
//            Stopwatch timer = new Stopwatch();
            Percolation perc = new Percolation(size);
            while (!perc.percolates()) {
                int x = StdRandom.uniform(1, size+1);
                int y = StdRandom.uniform(1, size+1);
                if (!perc.isOpen(x, y)) {
                    perc.open(x, y);
                }
            }
            pvals[i] = perc.numberOfOpenSites() / ((double) size*size);
//            runtimes[i] = timer.elapsedTime();
        }
        mean = StdStats.mean(pvals);
        stdDev = StdStats.stddev(pvals);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (size == 1) return Double.NaN;
        return stdDev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - (CONFIDENCE *stdDev / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + (CONFIDENCE *stdDev / Math.sqrt(trials));
    }

    // test client
    public static void main(String[] args) {

        int n = (args.length > 0) ? Integer.parseInt(args[0]) : 20;
        int trials = (args.length > 1) ? Integer.parseInt(args[1]) : 10;

        PercolationStats stats = new PercolationStats(n, trials);
        StdOut.println("Mean                   = " + stats.mean());
        StdOut.println("Standard deviation     = " + stats.stddev());
        StdOut.println("95% percent confidence = ["
                        + stats.confidenceHi()+ ", "+stats.confidenceLo()+"]");
//        StdOut.println("Runtime: "+timer.elapsedTime());
    }
}