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

public class PercolationStats {
    int size;
    int trials;
    double mean;

    double[] pvals;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 0 || trials < 0) {
            throw new IllegalArgumentException();
        }
        this.size = n;
        this.trials = trials;

        pvals = new double[n*n];
    }

    // sample mean of percolation threshold
    public double mean() {
        double sum = 0;
        for (double p: pvals) {
            sum += p;
        }
        return sum/(size*size);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (trials == 1) return Double.NaN;
        mean = this.mean();
        double stdmean = 0;
        for (double p: pvals) {
            stdmean += (p-mean)*(p-mean);
        }
        return Math.sqrt( stdmean / ((size*size)-1) );
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - ( 1.96*this.stddev() / Math.sqrt(size*size) );
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean + ( 1.96*this.stddev() / Math.sqrt(size*size) );
    }

    // test client
    public static void main(String[] args) {
        int n = (args.length > 0)? Integer.parseInt(args[0]): 20;
        int trials = (args.length > 1)? Integer.parseInt(args[1]): 10;

        Percolation perc = new Percolation(n);
        PercolationStats stats = new PercolationStats(n, trials);

        stats.pvals = new double[10];
        for (int i=0; i < n*n; i++) {
            int x = StdRandom.uniform(1, n+1);
            int y = StdRandom.uniform(1, n+1);

            if ( ! perc.isFull(x, y) ) {
                perc.open(x, y);
                stats.pvals[i] = perc.numberOfOpenSites()/(n*n*1.0);
            }
            if (perc.percolates()) break;
        }

        System.out.println("Mean: " + stats.mean());
        System.out.println("Standard deviation: " + stats.stddev());
    }
}