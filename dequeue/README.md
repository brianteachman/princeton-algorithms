# Generic Queue's 

Write a generic data type for a deque and a randomized queue. 

## Dequeue: 

A double-ended queue or deque (pronounced “deck”) is a generalization of a stack and a queue that supports adding and removing items from either the front or the back of the data structure.

**Performance requirements**: Your deque and iterator implementation must support each deque operation (including construction) in constant worst-case time. A deque containing n items must use at most 48n + 192 bytes of memory and use space proportional to the number of items currently in the deque.

---

## Randomized queue: 

A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random from items in the data structure.

**Iterator**: Each iterator must return the items in uniformly random order. The order of two or more iterators to the same randomized queue must be mutually independent; each iterator must maintain its own random order. 

**Performance requirements**: Your randomized queue implementation must support each randomized queue operation (besides creating an iterator) in constant amortized time. That is, any sequence of m randomized queue operations (starting from an empty queue) must take at most cm steps in the worst case, for some constant c. A randomized queue containing n items must use at most 48n + 192 bytes of memory. Additionally, your iterator implementation must support operations next() and hasNext() in constant worst-case time; and construction in linear time; you may (and will need to) use a linear amount of extra memory per iterator. 

---

## Links:

* [http://coursera.cs.princeton.edu/algs4/assignments/queues.html](http://coursera.cs.princeton.edu/algs4/assignments/queues.html)
* [http://coursera.cs.princeton.edu/algs4/checklists/queues.html](http://coursera.cs.princeton.edu/algs4/checklists/queues.html)
* [https://algs4.cs.princeton.edu/14analysis/](https://algs4.cs.princeton.edu/14analysis/)