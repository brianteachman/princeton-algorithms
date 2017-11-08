import edu.princeton.cs.algs4.StdOut;

/*
 * Weighted Quick Union with Compression
 */
public class UnionFind {

    private int[] id;
    private int[] sz;

    /**
     * Initialize union-find data structure with N objects (0 to N – 1)
     *
     * @param N
     */
    UnionFind(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i=0; i < N; i++) {
            // set id of each object to itself (N-array access)
            id[i] = i;
            // initialize size array
            sz[i] = 0;
        }
    }

    /**
     * Chase parent pointers until reach root (depth of i array accesses)
     *
     * @param i
     * @return
     */
    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    /**
     * Quick Find
     *
     * Are p and q in the same component? (depth of p and q array accesses)
     *
     * @param p
     * @param q
     * @return
     */
    boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    /**
     * Link root of smaller tree to root of larger tree (depth of p and q array accesses)
     *
     * At most 2N-2 array access
     *
     * @param p
     * @param q
     */
    void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[i] += sz[j];
        }
        else {
            id[j] = i;
            sz[j] += sz[i];
        }
    }

    /*
     * Find the greatest connected value
     */
    int find(int p) {
        int i = root(p);
//        int j = root(id.length-1);
//        return binarySearch(p);
        return binarySearch(i);
    }

    private int binarySearch(int x) {
        // Binary Search [Olog(n)]
        int start = 0;
        int end = id.length-1;
        while (start <= end)
        {
            // fint the midpoint
            int mid = start + (end - start)/2;

            // Check if x is present at mid
            if (id[mid] == x) return mid;

            // If x greater, ignore left half
            if (id[mid] < x) {
                start = mid + start;
            }
            // If x is smaller, ignore right half
            else {
                end = mid - start;
            }
        }

        // if we reach here, then element was not present
        return -1;
    }

    /**
     * Returns the number of components
     *
     * @return
     */
    int count() {
        return -1;
    }
}
