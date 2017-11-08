import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Quiz1_UnionFind {

    public static void main(String[] args) {
        question1();
        question2();
        question3();
    }

    /*
    * Social network connectivity.
    *
    * Given a social network containing members and a log file containing
    * m timestamps at which times pairs of members formed friendships,
    * design an algorithm to determine the earliest time at which all
    * members are connected (i.e., every member is a friend of a friend
    * of a friend ... of a friend). Assume that the log file is sorted by
    * timestamp and that friendship is an equivalence relation. The running
    * time of your algorithm should be mlog⁡n or better and use extra
    * space proportional to n.
    */
    private static void question1() {

    }

    /*
    * Union-find with specific canonical element.
    *
    * Add a method find() to the union-find data type so that find(i)
    * returns the largest element in the connected component containing i.
    * The operations, union(), connected(), and find() should all take
    * logarithmic time or better.
    *
    * For example, if one of the connected components is {1,2,6,9}, then
    * the find() method should return 9 for each of the four elements in
    * the connected components.
    */
    private static void question2() {
        UnionFind unionFind = new UnionFind(10);
        unionFind.union(1, 2);
        unionFind.union(2, 6);
        unionFind.union(6,9);

        StdOut.println(unionFind.find(2));
    }

    /*
    * Successor with delete.
    *
    * Given a set of n integers S={0,1,...,n−1} and a sequence of requests
    * of the following form:
    *
    *   Remove x from S
    *   Find the successor of x: the smallest y in S such that y≥x.
    *
    * design a data type so that all operations (except construction)
    * take logarithmic time or better in the worst case.
    */
    private static void question3() {
    }
}
