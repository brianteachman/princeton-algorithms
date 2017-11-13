import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * LinkedDeque: Double ended queue
 *
 * Performance requirements:
 * 1.) All deque and iterator operations within constant worst-case time.
 * 2.) A deque containing n items must use at most 48n + 192 bytes of memory.
 *     with space proportional to the number of items currently in the deque.
 */
public class Deque<Item> implements Iterable<Item> {

    private static final int STARTING_SIZE = 8;
    private static final String ERROR_EMPTY = "This deque is empty.";

    private Item[] nodes;
    private int count, head, tail; // tail - head = count
    private int size;          // current max number of elements

    // construct an empty deque
    public Deque() {
        size = STARTING_SIZE;
        nodes = newItemArray(size);
        head = (size / 2) - 1;
        tail = (size / 2);
        count = 0;
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
        nodes[head--] = item;
        count++;
        if (head == 0) {
            resize(nodes);
        }
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        nodes[tail++] = item;
        count++;
        if (tail == size-1) {
            resize(nodes);
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException(ERROR_EMPTY);
        }
        head += 1;
        Item val = nodes[head];
        nodes[head] = null;
        count--;
        return val;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException(ERROR_EMPTY);
        }
        tail -= 1;
        Item val = nodes[tail];
        nodes[tail] = null;
        count--;
        return val;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private int current = head;
        public boolean hasNext() { return current < tail-1; }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return nodes[++current]; // increment from head to next value
        }
    }

    private void resize(Item[] items) {
        Item[] clone = items.clone();
        size *= 2;
        head = (size/2)-1;
        tail = (size/2);
        nodes = newItemArray(size);

        for (int i = 0; i < (clone.length/2); i++) {
            int j = ((clone.length/2)-1)-i;
            int k = (clone.length/2)+i;
            if (clone[j] != null) nodes[head--] = clone[j];
            if (clone[k] != null) nodes[tail++] = clone[k];
        }
    }

    private Item[] newItemArray(int capacity) {
        return (Item[]) new Object[capacity];
    }

    //    public String toString() {
//        StringBuilder s = new StringBuilder();
//        s.append("[ ");
//        int y = 0, z = this.size();
//        for (Item item : this) {
//            s.append(item);
//            if (y++ != z) s.append(", ");
//        }
//        s.append(" ]");
//        return s.toString();
//    }

    // ------------------------------------------------------------------------

    // unit testing
    public static void main(String[] args) {

        Deque<Integer> deck = new Deque<Integer>();

        // test mutators
//        deck.addFirst(2);
//        deck.addFirst(1);
//        deck.addLast(3);
//        deck.addLast(4);
//        StdOut.println("           "+deck);
//        StdOut.println("dequeued "+deck.removeFirst()+" "+deck);
//        deck.addFirst(2);
//        StdOut.println("queued   2 "+deck);
//        StdOut.println("dequeued "+deck.removeFirst()+" "+deck);
//        deck.addLast(deck.removeFirst());
//        StdOut.println("roll over  "+deck);
//        StdOut.println("popped   "+deck.removeLast()+" "+deck);
//        deck.addLast(2);
//        StdOut.println("push     2 "+deck);

        StdOut.println("           "+deck);

        // test resize
        for (int i = 0; i < 20; i++) {
            int rnum = StdRandom.uniform(20)+1;
            if (i % 2 == 0) {
                deck.addFirst(rnum);
            }
            else {
                deck.addLast(rnum);
            }
//            StdOut.println("Count "+deck.size()+": "+deck);
        }
        StdOut.println(deck.size());
//        for (int i : deck) {
//            StdOut.println(i);
//        }
    }
}
