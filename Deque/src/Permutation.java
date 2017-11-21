import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Write a client program Permutation.java that takes an integer k
 * as a command-line argument; reads in a sequence of strings from
 * standard input using StdIn.readString(); and prints exactly k
 * of them, uniformly at random. Print each item from the sequence
 * at most once.
 *
 * Performance:
 * 1.) The running time of Permutation must be linear in the size of the input.
 * 2.) You may use only a constant amount of memory plus either one LinkedDeque or
 *     RandomizedQueue object of maximum size at most n.
 */
public class Permutation {

    /**
     * Assume that 0 ≤ k ≤ n, where n is the number of strings on standard input.
     */
    public static void main(String[] args) {
        RandomizedQueue<String> rque = new RandomizedQueue<String>();

        int k = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {
            rque.enqueue(StdIn.readString());
        }

        while (k > 0) {
            k--;
            StdOut.println(rque.sample());
        }
    }
}
