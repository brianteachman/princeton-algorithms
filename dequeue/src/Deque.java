import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Deque: Double ended queue
 *
 * Performance requirements:
 * 1.) All deque and iterator operations within constant worst-case time.
 * 2.) A deque containing n items must use at most 48n + 192 bytes of memory.
 *     with space proportional to the number of items currently in the deque.
 */
public class Deque<Item> implements Iterable<Item> {

    private int count = 0;

    private class Node {
        Item item;
        Node next;
    }

    private Node first, last;

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null && last == null;
//        return count == 0;
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
        if (!isEmpty()) {
            first.next = oldfirst;
        }
        count++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldlast.next = last;
        }
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        count--;

        return first.item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        count--;

        return last.item;
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



    }
}
