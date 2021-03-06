/*-----------------------------------------------------------------------------
 *  Author:        Brian Teachman
 *  Written:       10/27/2017
 *  Last updated:  11/6/2017
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

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final int OPEN = 0;
    private static final int FULL = 1;
//    private static final int VIRTUAL = 2;

//    private boolean[] sites;
    private boolean[][] sites;
    private final int size;
    private final WeightedQuickUnionUF unionFind;
    private final int virtualTop;
    private final int virtualBottom;
    private int openSites = 0;

    /* ------------------------------------------------------------------------
     * Specified API
     * ----------------------------------------------------------------------*/

    // create n-by-n grid, with all sites blocked (0)
    // time proportional to n^2
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n must greater than 0.");
        }
        size = n;
        int numSites = n*n;
//        sites = new boolean[numSites];
        sites = new boolean[numSites][2];
        int listSize = numSites+5;
        unionFind = new WeightedQuickUnionUF(listSize);
        virtualTop = numSites;
        virtualBottom = numSites+1;
    }

    // open site (row, col) if it is not open already
    // constant time
    public void open(int row, int col) {

//        validateSiteExist(row, col);

        int site = getId(row, col);

        // mark the site as open
//        sites[site] = true;
        sites[site][OPEN] = true;
        openSites++;

        // if fist row or last row link to respective virtual node
        if (row == 1) {
            unionFind.union(virtualTop, site);
        }
        if (row == size) {
            unionFind.union(virtualBottom, site);
        }

        // link the site to open neighbors (cardinal directions only)
        int neighbor;
        if (isUnionable(row-1, col)) { // above
            neighbor = getId(row-1, col);
            unionFind.union(site, neighbor);
        }
        if (isUnionable(row+1, col)) { // below
            neighbor = getId(row+1, col);
            unionFind.union(site, neighbor);
        }
        if (isUnionable(row, col-1)) { // to the left
            unionFind.union(site, getId(row, col-1));
        }
        if (isUnionable(row, col+1)) { // to the right
            unionFind.union(site, getId(row, col+1));
        }

        if (unionFind.connected(site, virtualTop)) {
            sites[site][FULL] = true;
        }
    }

    // is site (row, col) open?
    // constant time
    public boolean isOpen(int row, int col) {
        validateSiteExist(row, col);
//        return sites[getId(row, col)];
        return sites[getId(row, col)][OPEN];
    }

    // is site (row, col) full?
    // constant time
    public boolean isFull(int row, int col) {
        int site = getId(row, col);
        return isOpen(row, col) //&& not connected through a virtual node
//                && sites[site][FULL] //TODO: fix backwash
                && unionFind.connected(virtualTop, site);
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
    private void validateSiteExist(int row, int col) {
        if (!isValidIndex(row, col)) {
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
        int n = (args.length > 0) ? Integer.parseInt(args[0]) : 20;
        Percolation grid = new Percolation(n);

        while (!grid.percolates()) {
            int x = StdRandom.uniform(1, n+1);
            int y = StdRandom.uniform(1, n+1);
//            if (!grid.isOpen(x, y)) {
            if (!grid.isFull(x, y)) {
                grid.open(x, y);
            }
        }
        StdOut.println(grid.numberOfOpenSites() + " sites opened.");
//        StdOut.println(grid.percolates() ? "It percolates." : "No percolation here.");
    }
}