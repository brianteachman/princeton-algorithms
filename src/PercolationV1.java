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
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.HashMap;


public class PercolationV1 {

    int virtualTop; // top member of tree
    int virtualBottom;

    //TODO: these are dependant on data structure, which I haven't picked yet
    private int firstElement; // The starting row/col value, either 0 or 1
    private int lastElement;  // The last row/col value, either n-1 or n

    private int size; // size of n, nxn matrix

    private class Site {
        private int id;
        private int row;
        private int col;

        public Site(int row, int col) {
            this.row = row;
            this.col = col;
            this.id = pair(row, col);
        }
    }

//    private int[] sites; // A nx1 array of compressed 2D sites
    private HashMap<Integer, Boolean> sites; // <id, open/closed>
    private int openSites = 0; // Open site count

    private static final int WEIGHT = 3;
    public WeightedQuickUnionUF finder;

    /**
     * Create n-by-n grid, with all sites blocked
     *
     * Should take time proportional to n2.
     *
     * @param n The n value for an nxn matrix, where n > 0
     */
    public PercolationV1(int n) throws IllegalArgumentException {
        if (n < 0) {
            throw new IllegalArgumentException("N must be greater than 0.");
        }
        this.size = n;
        this.firstElement = 0;
        this.lastElement = n-1;

        finder = new WeightedQuickUnionUF(n*n+2);

        initModel();
    }

    /* ------------------------------------------------------------------------
     * All methods should take constant time plus a constant number of calls to
     * the union–find methods union(), find(), connected(), and count().
     * ----------------------------------------------------------------------*/

    /**
     * Open site (row, col) if it is not open already
     *
     * pre:  Object initialized (the constructor has been called)
     * post: Open site (row, col) if it is not open already
     *
     * @param row   The row component of the site, in the range 1..n
     * @param col   The column component of the site, in the range 1..n
     * @return void
     */
    public void open(int row, int col) throws IllegalArgumentException {
        validateBounds(row, col);

        int site = this.pair(row, col);

        // 1) validate the index of the received site
        if ( ! this.sites.containsKey(site)) {
            throw new IllegalArgumentException("There is no site " + site + " here.");
        }

        // 2) mark the site as open
        this.sites.put(site, true);
        openSites++;

        // 3) link the site to its open neighbors (use WeightedQuickUnionUF)
        for (int i=-1; i < 2; i++) {
            for (int j=-1; j < 2; j++) {
                if (isUnionable(row, col, i, j)) {
                    this.finder.union(site, this.pair(row+i, col+j));
                    System.out.println(site+" paired: " + (row+i) + ", " + (col+j));
                }
            }
        }
    }

    private boolean isUnionable(int row, int col, int i, int j) {
        return // won't run out of bounds
                (row+i > -1 && col+j > -1)
                && (row+i < this.size && row+j < this.size)
                // not the current cell
                && (i != 0 && j != 0)
                // site is open
                && this.isOpen(row+i, col+j);
    }

    /**
     * Is site (row, col) open?
     *
     * @param row   The row component of the site, in the range 1..n
     * @param col   The column component of the site, in the range 1..n
     * @return boolean
     */
    public boolean isOpen(int row, int col) throws IllegalArgumentException  {
        validateBounds(row, col);
        int pid = this.pair(row, col);
        return sites.containsKey(pid) && sites.get(pid);
    }

    /**
     * Is site (row, col) full?
     *
     * @param row   The row component of the site, in the range 1..n
     * @param col   The column component of the site, in the range 1..n
     * @return boolean
     */
    public boolean isFull(int row, int col) throws IllegalArgumentException {
        validateBounds(row, col);
        return sites.get(this.pair(row, col));
    }

    /**
     * The number of open sites
     *
     * @return int
     */
    public int numberOfOpenSites() {
        return openSites;
    }

    /**
     * Does the system percolate?
     *
     * The system percolates if there is a full site in the bottom row.
     *
     * For example, the insulating/metallic materials example, the open sites
     * correspond to metallic materials, so that a system that percolates has
     * a metallic path from top to bottom, with full sites conducting.
     *
     * @return boolean
     */
    public boolean percolates() {
        return false;
    }

    /*-----------------------------------------------------------------------------
     *  Helper Methods
     *---------------------------------------------------------------------------*/

    private boolean isOutOfBounds(int row, int col) {
        return row < this.firstElement || col < this.firstElement ||
                row > this.lastElement || col > this.lastElement;
    }

    private void validateBounds(int row, int col) {
        if (isOutOfBounds(row, col)) {
            throw new IllegalArgumentException("Either the row or the column is out of bounds.");
        }
    }

    /**
     * Initialize the model, an nxn blocked grid containing blocked/unblocked sites
     */
    private void initModel() {
        this.sites = new HashMap<Integer, Boolean>();
        for (int i=this.firstElement; i < this.lastElement; i++) {
            for (int j=this.firstElement; j < this.lastElement; j++) {
                this.makeSite(i, j);
            }
        }
    }

    private void setupVirtualRoots() {
        this.virtualTop = this.pair(this.size, this.size);
        this.virtualBottom = this.pair(this.size + 1, this.size + 1);
    }

    /**
     * Generates a site to be open or closed
     *
     * @param row
     * @param col
     */
    private void makeSite(int row, int col) {
        this.sites.put(this.pair(row, col), false);
        for (int i=0; i < Math.floorDiv(this.size, this.WEIGHT); i++) {
            if (row == getRand() || row == getRand()) {
                this.open(row, col);
            }
        }
    }

    private int getRand() {
        return StdRandom.uniform(this.size);
//        return StdRandom.uniform(1, this.size);
    }

    //An Elegant Pairing Function by Matthew Szudzik @ Wolfram Research, Inc.
    private static int pair(int x, int y) {
        return (x >= y) ? (x * x + x + y) : (y * y + x);
    }

    private static int[] unpair(int z) {
        int sqrtz = (int) Math.floor(Math.sqrt(z)),
            sqz = sqrtz * sqrtz;
        int[] rowCol = new int[2];
        if ((z - sqz) >= sqrtz) {
            rowCol[0] = sqrtz;           // row
            rowCol[1] = z - sqz - sqrtz; // column
        }
        else {
            rowCol[0] = z - sqz; // row
            rowCol[1] = sqrtz;   // column
        }
        return rowCol;
    }

    /*-----------------------------------------------------------------------------
     *  Test Client
     *---------------------------------------------------------------------------*/

    public static void main(String[] args) {
        int n = 8;

        // 1. Make a connection
        PercolationV1 perc = new PercolationV1(n);
//        perc.open(1, 1);
//        perc.open(1, 2);

//        testPairingFunction(perc);

        // 2. Test the connection
        System.out.println(perc.finder.connected(1, 2));

        System.out.println(perc.openSites + " sites open.");
    }

    private static void testPairingFunction(PercolationV1 perc) {
        int pair1 = perc.pair(2,3);
        int pair2 = perc.pair(3,2);

        int[] site1 = perc.unpair(pair1);
        int[] site2 = perc.unpair(pair2);

        System.out.println(pair1 + " -> (" + site1[0] + ", " + site1[1] + ")");
        System.out.println(pair2 + " -> (" + site2[0] + ", " + site2[1] + ")");
    }
}
