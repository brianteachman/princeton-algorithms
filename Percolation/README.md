Percolation
--

If sites are independently set to be open with probability `p` (and therefore blocked with probability `1 − p`), what is the probability that the system percolates? When `p` equals 0, the system does not percolate; when `p` equals 1, the system percolates.

When n is sufficiently large, there is a threshold value `p*` such that when `p < p*` a random n-by-n grid almost never percolates, and when `p > p*`, a random n-by-n grid almost always percolates. No mathematical solution for determining the percolation threshold p* has yet been derived.

Your task is to write a program to estimate `p*`.

## Monte Carlo Simulation:

Use a Monte Carlo simulation to estimate the percolation threshold:

1. Initialize all sites to be blocked. (site = 0)
2. Repeatedly open a random site until the system percolates:
3. The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold. 

## Notes:

* Any methods or fields outside the API need to be private, except main.
* Your Percolation class must use WeightedQuickUnionUF.
* It's OK to use an extra row and/or column to deal with the 1-based indexing of the percolation grid.
* Each of the methods (except the constructor) in PercolationV1 must use a constant number of union-find operations.

References:
---
* http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
* https://algs4.cs.princeton.edu/code/index.php
* https://introcs.cs.princeton.edu/java/11style/
* https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/WeightedQuickUnionUF.html
* https://en.wikipedia.org/wiki/Pairing_function
* http://szudzik.com/ElegantPairing.pdf
* https://codepen.io/sachmata/post/elegant-pairing


