import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;
    }

    private Node first, last;

    // construct an empty randomized queue
    public RandomizedQueue() {

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return false;
    }

    // return the number of items on the randomized queue
    public int size() {
        return -1;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return last.item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return last.item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomGetIterator();
    }

    /**
     * Return the items in uniformly random order.
     */
    private class RandomGetIterator implements Iterator<Item> {
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

    // unit testing (optional)
    public static void main(String[] args) {

    }
}

