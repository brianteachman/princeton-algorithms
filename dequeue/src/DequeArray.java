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

    public static final String ERROR_EMPTY = "This deque is empty.";

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
    public DequeArray() {
        this(8);
    }

    public String toString() {
        int i = 1;
        String str = "[ ";
        for (Item node : nodes) {
            if (node != null) {
                str += node;
                if (i++ != count) str += ", ";
            }
        }
        return str + " ]";
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
//        StdOut.println(head + " (" + nodes[head] + ") n= " + count);
        nodes[head] = null;
        count--;
//        StdOut.println(head + " (" + nodes[head] + ") n= " + count);
        return val;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException(ERROR_EMPTY);
        }
        tail -= 1;
        Item val = nodes[tail];
//        StdOut.println(tail + " (" + nodes[tail] + ") n= " + count);
        nodes[tail--] = null;
        count--;
//        StdOut.println(tail + " (" + nodes[tail] + ") n= " + count);
        return val;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
//        private Item current = ;
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

    private void resize(Item[] items) {

//        StdOut.println(this);

        Item[] clone = items.clone();
        size *= 2;
        head = (size/2)-1;
        tail = (size/2);
        nodes = (Item[]) new Object[size];

//        StdOut.println(head+".."+tail);

        for (int i = 0; i < (clone.length/2); i++) {
            int j = ((clone.length/2)-1)-i;
            int k = (clone.length/2)+i;
            if ( clone[j] != null) nodes[head--] = clone[j];
            if ( clone[k] != null) nodes[tail++] = clone[k];

//            StdOut.println("size "+size+", head = "+j+" -> "+head+",\t tail = "+k+" -> "+tail);
        }
//        StdOut.println("RESIZED");
    }

    // ------------------------------------------------------------------------

    // unit testing
    public static void main(String[] args) {

        DequeArray<Integer> deck = new DequeArray<Integer>();

        // 1, 2, 3, 4
        deck.addFirst(2);
        deck.addFirst(1);
        deck.addLast(3);
        deck.addLast(4);
        System.out.println("           "+deck);

        System.out.println("dequeued "+deck.removeFirst()+" "+deck);
        System.out.println("dequeued "+deck.removeFirst()+" "+deck);
//        deck.addLast(deck.removeFirst());
        System.out.println("popped   "+deck.removeLast()+" "+deck);

        deck.addFirst(2);
        System.out.println("push:    3 "+deck);

        System.out.println("queued:  1 "+deck);



//        for (int i = 0; i < 20; i++) {
//            Random rnum = new Random();
//            if (i % 2 == 0) {
//                deck.addFirst(rnum.nextInt(20) + 1);
//            }
//            else {
//                deck.addLast(rnum.nextInt(20) + 1);
//            }
//            System.out.println("Count "+deck.size()+": "+deck);
//        }
    }
}
