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
    private final int size;
    private final int trials;

    private final double[] pvals;
//    private double[] runtimes;
    private static final double CONFIDENCE = 1.96;

    // perform trials independent experiments on an n-by-n grid
    PercolationStats(int n, int T) {
        if (n < 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        trials = T;
        pvals = new double[trials];
//        runtimes = new double[trials];

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
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(pvals);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (size == 1) return Double.NaN;
        return StdStats.stddev(pvals);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE *this.stddev() / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE *this.stddev() / Math.sqrt(trials));
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