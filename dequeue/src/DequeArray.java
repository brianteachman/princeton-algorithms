import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Deque: Double ended queue
 *
 * Performance requirements:
 * 1.) All deque and iterator operations within constant worst-case time.
 * 2.) A deque containing n items must use at most 48n + 192 bytes of memory.
 *     with space proportional to the number of items currently in the deque.
 */
public class DequeArray<Item> implements Iterable<Item> {

    private Item[] nodes;
    private int size;
    private int head;
    private int tail;

    private int count = 0;

    // construct an empty deque
    public DequeArray(int elemsize) {
        size = elemsize;
        nodes = (Item[]) new Object[size];
        head = (size / 2) - 1;
        tail = (size / 2);
    }

    // is the deque empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (head < 1) {
            resize();
        }
        nodes[head--] = item; //TODO: fix first element black hole
        count++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        nodes[tail++] = item; //TODO: fix first element black hole
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item val = nodes[head];
        nodes[head--] = null;
        count--;
        return val;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item val = nodes[tail];
        nodes[tail--] = null;
        count--;
        return val;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Item current = nodes[head];
        public boolean hasNext() { return current != null; }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }
            Item item = current;
            return item;
        }
    }

    // ------------------------------------------------------------------------

    private void resize() {
        Item[] clone = nodes.clone();
        size *= 2;
        nodes = (Item[]) new Object[size];
        StdOut.println(nodes.length);

//        int offset = (nodes.length / 2);
        int offset = clone.length;
        for (int i = 0; i < clone.length; i++) {
            if (i < offset) {
//                if (i % 2 == 0) {
                    nodes[offset - i] = clone[i];
//                    StdOut.println(offset - i - 1);
                } else {
//                    StdOut.println(offset + (i - offset));
                    nodes[offset + (i - offset)] = clone[i];
                }
//            }
//            else {
//                if (i % 2 == 0) {
//                    StdOut.println(offset - i - 1);
//                } else {
//                    StdOut.println(offset + (i - offset));
//                }
//            }
        }
        for (Item node : nodes) {
            StdOut.print(node + ", ");
        }
    }

    // ------------------------------------------------------------------------

    // unit testing
    public static void main(String[] args) {

        DequeArray<Integer> deck = new DequeArray<>(4);
        deck.addFirst(2);
        deck.addFirst(1);
        deck.addLast(3);

        for (int i = 0; i < 10; i++) {
            StdOut.print(deck.removeFirst() + ", ");
//            Random rnum = new Random(20);
//            DequeArray<Integer> deck = new DequeArray<>(i+1);

//            deck.addFirst(2);
//            deck.addFirst(1);
//            deck.addLast(3);
//            StdOut.println(deck);
//            StdOut.println(deck.size() + " items");
            //        while (!deck.isEmpty()) {
            //            StdOut.print(deck.removeFirst());
            //        }
        }
    }
}
