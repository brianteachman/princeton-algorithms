/*-----------------------------------------------------------------------------
 *  Author:        Brian Teachman
 *  Written:       10/27/2017
 *  Last updated:  10/27/2017
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
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private int firstElement;
    private int lastElement;

    /**
     * Create n-by-n grid, with all sites blocked
     *
     * Should take time proportional to n2.
     *
     * @param n The n value for an nxn matrix, where n > 0
     */
    public Percolation(int n) throws IllegalArgumentException {
        if (n <= 0) {
            throw new IllegalArgumentException("N must be greater than 0.");
        }
        this.firstElement = 0;
        this.lastElement = n-1;

        // create the grid
    }

    /**
     * Open site (row, col) if it is not open already
     *
     * pre:  Object initialized (the constructor has been called)
     * post: Open site (row, col) if it is not open already
     *
     * @param row
     * @param col
     * @return void
     */
    public void open(int row, int col) throws IllegalArgumentException {
        if (isValidArrayBounds(row, col)) {
            throw new IllegalArgumentException("Either the row or the column is out of bounds.");
        }

        // 1) validate the index of the received site

        // 2) mark the site as open

        // 3) link the site to its open neighbors (use WeightedQuickUnionUF)
    }

    /**
     * Is site (row, col) open?
     *
     * @param row
     * @param col
     * @return boolean
     */
    public boolean isOpen(int row, int col) throws IllegalArgumentException  {
        if (isValidArrayBounds(row, col)) {
            throw new IllegalArgumentException("Either the row or the column is out of bounds.");
        }
        return false;
    }

    /**
     * Is site (row, col) full?
     *
     * @param row
     * @param col
     * @return boolean
     */
    public boolean isFull(int row, int col) throws IllegalArgumentException {
        if (isValidArrayBounds(row, col)) {
            throw new IllegalArgumentException("Either the row or the column is out of bounds.");
        }
        return false;
    }

    /**
     * The number of open sites
     *
     * @return int
     */
    public int numberOfOpenSites() {
        return 0;
    }

    /**
     * Does the system percolate?
     *
     * @return boolean
     */
    public boolean percolates() {
        return false;
    }

    /*-----------------------------------------------------------------------------
     *  Helper Methods
     *---------------------------------------------------------------------------*/

    private boolean isValidArrayBounds(int row, int col) {
        return row < this.firstElement || col < this.firstElement ||
                row > this.lastElement || col > this.lastElement;
    }

    /*-----------------------------------------------------------------------------
     *  Test Client
     *---------------------------------------------------------------------------*/

    public static void main(String[] args) {

        // 1. Make a connection
        Percolation perc = new Percolation(4);
        perc.open(1, 1);
        perc.open(1, 2);

        // 2. Test the connection
        WeightedQuickUnionUF quf = new WeightedQuickUnionUF(4);
        System.out.println(quf.connected(1, 2));
    }
}
