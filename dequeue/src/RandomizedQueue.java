import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int STARTING_SIZE = 8;
    private Item[] nodes;
    private int size, head, tail, count;
    private boolean sampling;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = STARTING_SIZE;
        nodes = newItemArray(size);
        head = 0;
        count = 0;
        sampling = false;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        nodes[tail++] = item;
        count++;
        if (tail == size) {
            resize(size*2);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item val = nodes[head];
        nodes[head++] = null;
        count--;
        if (count < size/2) {
            resize(size/2);
        }
        return val;
    }

    // return a random item (but do not remove it)
    // TODO: fix so no duplicates, and constant time
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (!sampling) {
            sampling = true;
//            StdRandom.shuffle(copy);
        }
        Item it = null;
        while (it == null) {
            it = nodes[head + StdRandom.uniform(count)];
        }
        return it;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomGetIterator();
    }

    // return the items in uniformly random order.
    private class RandomGetIterator implements Iterator<Item> {
        private Item[] copy;
        int copyhead;
        public RandomGetIterator() {
            copyhead = 0;
            copy = newItemArray(count);
            int i = 0;
            for (int j = head; j < tail; j++) {
                copy[i++] = nodes[j];
            }
            StdRandom.shuffle(copy);
        }
        public boolean hasNext() { return copyhead < count; }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy[copyhead++];
        }
        public int getHead() {
            return copyhead;
        }
    }

    private void resize(int capacity) {
        Item[] temp = nodes;
        size = capacity;
        nodes = newItemArray(capacity);
        for (int i = 0; i < count; i++) {
            nodes[i] = temp[head+i];
        }
        head = 0;
        tail = head + count;
    }

    private Item[] newItemArray(int capacity) {
        return (Item[]) new Object[capacity];
    }

//    public String toString() {
//        StringBuilder s = new StringBuilder();
//        s.append("[ ");
//        int y = 1, z = this.size();
//        for (Item item : this) {
//            s.append(item);
//            if (y++ != z) s.append(", ");
//        }
//        s.append(" ]");
//        return s.toString();
//    }

    // unit testing (optional)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rque = new RandomizedQueue<Integer>();

//        rque.enqueue(1);
//        rque.enqueue(2);
//        rque.enqueue(3);
//        rque.enqueue(4);
//        rque.enqueue(5);
//        rque.enqueue(6);
//        rque.enqueue(7);
//        StdOut.println(rque);
//        rque.enqueue(8);
//        rque.dequeue();
//        rque.dequeue();
//        rque.dequeue();
//        StdOut.println("8 -> "+rque);
//        StdOut.println(rque.dequeue()+" <- "+rque);

//        rque.enqueue(9);
//        StdOut.println("9 -> "+rque);

        for (int i = 0; i < 100; i++) {
            rque.enqueue(i+1);
        }
        StdOut.println(rque);
        for (int i = 0; i < rque.size(); i++) {
            StdOut.print(rque.sample() + ", ");
        }

        // Test iterator
//        StdOut.print("[ ");
//        for (int i : rque) {
//            StdOut.print(i + ", ");
//        }
//        StdOut.print(" ]");
    }
}

