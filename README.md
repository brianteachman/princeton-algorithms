The assignment:
-------

If sites are independently set to be open with probability `p` (and therefore blocked with probability `1 − p`), what is the probability that the system percolates? When `p` equals 0, the system does not percolate; when `p` equals 1, the system percolates.

When n is sufficiently large, there is a threshold value `p*` such that when `p < p*` a random n-by-n grid almost never percolates, and when `p > p*`, a random n-by-n grid almost always percolates. No mathematical solution for determining the percolation threshold p* has yet been derived.

Your task is to write a program to estimate `p*`.

Notes:
-------
* Any methods or fields outside the API need to be private, except main.
* Your Percolation class must use WeightedQuickUnionUF.
* It's OK to use an extra row and/or column to deal with the 1-based indexing of the percolation grid.
* Each of the methods (except the constructor) in Percolation must use a constant number of union-find operations.

Links:
-------
* http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
* https://algs4.cs.princeton.edu/code/index.php
* https://introcs.cs.princeton.edu/java/11style/
* https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/WeightedQuickUnionUF.html
