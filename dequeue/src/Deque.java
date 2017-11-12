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
public class Deque<Item> implements Iterable<Item> {

    private static final String ERROR_EMPTY = "This deque is empty.";

    private class Node {
        Item item;
        Node next;
        public String toString() {
            return "this: "+item+", next: "+next+"\n";
        }
    }

    private Node first, last, oldlast;
    private int count;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        oldlast = null;
        count = 0;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[ ");
        int y = 1, z = this.size();
        for (Item item : this) {
            s.append(item);
            if (y++ != z) s.append(", ");
        }
        s.append(" ]");
        return s.toString();
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
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) {
            last = first;
        } else {
            first.next = oldfirst;
        }
        count++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        count--;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = oldlast;
        last.next = null;
        count--;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing
    public static void main(String[] args) {

        Deque<Integer> deck = new Deque<>();
        System.out.println("Count 0: "+deck);
        deck.addFirst(3);
        deck.addFirst(2);
        deck.addFirst(4);
        deck.addLast(5);
        deck.addLast(1);
        System.out.println("Count 1: "+deck);

//        System.out.println("popped "+deck.removeLast()+" "+deck);
//        deck.addLast(deck.removeFirst());
//        System.out.println("dequeued "+deck.removeFirst()+" "+deck);
//        System.out.println("dequeued "+deck.removeFirst()+" "+deck);
//        System.out.println("popped "+deck.removeLast()+" "+deck);

        for (int i : deck) {
            StdOut.print(i+", ");
        }
    }
}
