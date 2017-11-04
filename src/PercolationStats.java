/*-----------------------------------------------------------------------------
 *  Author:        Brian Teachman
 *  Written:       11/2/2017
 *  Last updated:  11/2/2017
 *
 *  Compilation:   javac PercolationStats.java
 *  Execution:     java PercolationStats
 *
 *  Project 1: Percolation, using quick union find data type. Determines if a
 *             path through an nxn matrix can be found (percolates).
 *
 *  Run analysis of Percolation
 *---------------------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    int size;
    int trials;
    Double mean;

    double[] pvals;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 0 || trials < 0) {
            throw new IllegalArgumentException();
        }
        this.size = n;
        this.trials = trials;

        pvals = new double[trials];
    }

    // sample mean of percolation threshold
    public double mean() {
        mean = StdStats.mean(pvals);
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(pvals);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        if (mean == null)  mean = this.mean();
        return mean - ( 1.96*this.stddev() / Math.sqrt(trials) );
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        if (mean == null)  mean = this.mean();
        return mean + ( 1.96*this.stddev() / Math.sqrt(trials) );
    }

    // test client
    public static void main(String[] args) {
        int n = (args.length > 0)? Integer.parseInt(args[0]): 20;
        int T = (args.length > 1)? Integer.parseInt(args[1]): 10;

        PercolationStats stats = new PercolationStats(n, T);

        stats.pvals = new double[T];

        int index = 0;
        for (int i=0; i < T; i++) {
            Percolation perc = new Percolation(n);

            for (int j=0; j < n*n; j++) {
                int x = StdRandom.uniform(1, n+1);
                int y = StdRandom.uniform(1, n+1);

                if ( ! perc.isFull(x, y) ) {
                    perc.open(x, y);
                }
                if (perc.percolates()) break;
            }

            stats.pvals[index++] = perc.numberOfOpenSites();
            System.out.println("Mean                  = " + stats.mean());
            System.out.println("Standard deviation    = " + stats.stddev());
            System.out.printf("95 percent confidence = [%f, %f]\n\n",
                    stats.confidenceHi(), stats.confidenceLo());
        }
    }
}