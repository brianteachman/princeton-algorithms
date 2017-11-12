import java.util.NoSuchElementException;

/**
 * Write a client program Permutation.java that takes an integer k
 * as a command-line argument; reads in a sequence of strings from
 * standard input using StdIn.readString(); and prints exactly k
 * of them, uniformly at random. Print each item from the sequence
 * at most once.
 *
 * Performance:
 * 1.) The running time of Permutation must be linear in the size of the input.
 * 2.) You may use only a constant amount of memory plus either one Deque or
 *     RandomizedQueue object of maximum size at most n.
 */
public class Permutation {

    /**
     * Assume that 0 ≤ k ≤ n, where n is the number of strings on standard input.
     */
    public static void main(String[] args) {

//        int k = Integer.parseInt(args[0]);
        int k = 3;
        RandomizedQueue<String> sub = new RandomizedQueue<String>();
        Deque<String> deck = new Deque<String>();

        try
        {
//            String values = StdIn.readString();
            String values = "A B C D E F G H I";
            while(values != null)
            {
                sub.enqueue(values);
                values = StdIn.readString();
            }
        }
        catch(NoSuchElementException e)
        {

        }

        while(k > 0)
        {
            k--;
            StdOut.println(sub.dequeue());
        }
    }
}
