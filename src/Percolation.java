/*-----------------------------------------------------------------------------
 *  Author:        Brian Teachman
 *  Written:       10/27/2017
 *  Last updated:  11/2/2017
 *
 *  Compilation:   javac Percolation.java
 *  Execution:     java Percolation
 *
 *  Project 1: Percolation, using quick union find data type. Determines if a
 *             path through an nxn matrix can be found (percolates).
 *
 *  An example percolation problem:
 *  --------------------------------
 *  Given a composite systems comprised of randomly distributed insulating and
 *  metallic materials: what fraction of the materials need to be metallic so
 *  that the composite system is an electrical conductor?
 *---------------------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

//    boolean[][] sites;
    boolean[] sites;
    int size;
    WeightedQuickUnionUF unionFind;
    int virtualTop;
    int virtualBottom;
    private int openSites;

    /* ------------------------------------------------------------------------
     * Specified API
     * ----------------------------------------------------------------------*/

    // create n-by-n grid, with all sites blocked (0)
    // time proportional to n2
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n must greater than 0.");
        }
//        sites = new boolean[n][n];
        sites = new boolean[n*n];
        size = n;
        unionFind = new WeightedQuickUnionUF(n*n+6);
        virtualTop = n*n;
        virtualBottom = n*n+1;
        for (int i=1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                int site = getId(i, j);
//                sites[i-1][j-1] = false;
                sites[site] = false;
                // connect top and bottom row to respective virtual site
                if (i == 1) unionFind.union(virtualTop, site);
                if (i == size) unionFind.union(virtualBottom, site);
//                System.out.println(site + " open? " + sites[site]);
            }
        }
//        System.out.println(virtualTop);
//        System.out.println(virtualBottom);
    }

    // open site (row, col) if it is not open already
    // constant time
    public void open(int row, int col) {

        validateSiteExist(row, col);

        int site = getId(row, col);

        // mark the site as open
//        sites[row-1][col-1] = true;
        sites[site] = true;
        openSites++;

        // link the site to its open neighbors (use WeightedQuickUnionUF)
        if (isUnionable(row-1, col)) { // above
            unionFind.union(site, getId(row-1, col));
        }
        if (isUnionable(row, col-1)) { // to the left
            unionFind.union(site, getId(row, col-1));
        }
        if (isUnionable(row, col+1)) { // to the right
            unionFind.union(site, getId(row, col+1));
        }
        if (isUnionable(row+1, col)) { // below
            unionFind.union(site, getId(row+1, col));
        }
        if (row == 1) unionFind.union(virtualTop, site);
        if (row == size) unionFind.union(virtualBottom, site);
    }

    // is site (row, col) open?
    // constant time
    public boolean isOpen(int row, int col) throws IllegalArgumentException {
        validateSiteExist(row, col);
//        return sites[row-1][col-1];
        return sites[getId(row, col)];
    }

    // is site (row, col) full?
    // constant time
    public boolean isFull(int row, int col) throws IllegalArgumentException {
        int site = getId(row, col);
        return isOpen(row, col) && unionFind.connected(virtualTop, site);
    }

    // number of open sites
    // constant time
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    // constant time
    public boolean percolates() {
        return unionFind.connected(virtualTop, virtualBottom);
    }

    /* ------------------------------------------------------------------------
     * Helper Methods
     * ----------------------------------------------------------------------*/

    private int getId(int row, int col) {
        return size * (row-1) + (col-1);
    }

    // validate the index of the received site
    private void validateSiteExist(int row, int col) throws IllegalArgumentException {
        if ( ! isValidIndex(row, col)) {
            throw new IllegalArgumentException("Valid scalars are 1-"+size+". <"+row+", "+col+">");
        }
    }

    private boolean isValidIndex(int row, int col) {
        return (0 < row && 0 < col) && (row <= size && col <= size);
    }

    private boolean isUnionable(int row, int col) {
        return this.isValidIndex(row, col) // True if doesn't run out of bounds
                && this.isOpen(row, col);  // and the site is open
        // note: isValidIndex returns false before isOpen can throw exception
    }

    /* ------------------------------------------------------------------------
     * Test client
     * ----------------------------------------------------------------------*/

    public static void main(String[] args) {
        Stopwatch timer = new Stopwatch();
        int weight = 10;
        int n = 20;
        Percolation perc = new Percolation(n);

        // Test can percolate
//        int[] x = {1, 2, 3, 3, 4, 5};
//        int[] y = {1, 1, 1, 2, 2, 2};
//        for (int i=1; i <= n; i++) {
//            System.out.print("Site "+perc.getId(x[i], y[i])+" open: ");
//            System.out.println(perc.isOpen(x[i], y[i]));
//            perc.open(x[i], y[i]);
//            System.out.print("Site "+perc.getId(x[i], y[i])+" open: ");
//            System.out.println(perc.isOpen(x[i], y[i]));
//        }

        int i = 0;
        while ( ! perc.percolates()) {
            int x = StdRandom.uniform(1, n+1);
            int y = StdRandom.uniform(1, n+1);
            if ( ! perc.isFull(x, y) ) {
                perc.open(x, y);
            }
//            perc.showState();
        }
        System.out.println("Runtime: "+timer.elapsedTime());
        System.out.println(perc.numberOfOpenSites() + " sites opened.");
        System.out.println(perc.percolates()?"It percolates.":"No percolation here.");
    }

    public void showState() {
        for (int i=0; i<size*size; i++) {
            System.out.println("Site " + (i+1) + ": " + (sites[i] ? "open" : "closed"));
        }
    }
}