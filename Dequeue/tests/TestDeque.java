import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class TestDeque {

    @Test
    void dequeSizeTest() {
        Deque<String> deck = new Deque<String>();
        deck.addFirst("A");
        deck.addFirst("E");
        assertEquals(2, deck.size());
        deck.addLast("C");
        deck.addLast("B");
        deck.addLast("D");
        assertEquals(5, deck.size());
        deck = null;
    }

    @Test
    void removeFirstTest() {
        Deque<String> deck = new Deque<String>();
        deck.addFirst("B");
        deck.addFirst("A");
        deck.addLast("C");
        deck.addLast("D");
        deck.addLast("E");
        assertEquals(5, deck.size());
        assertEquals("A", deck.removeFirst());
        assertEquals("B", deck.removeFirst());
        assertEquals(3, deck.size());
    }

    @Test
    void removeLastTest() {
        Deque<String> deck = new Deque<String>();
        deck.addFirst("B");
        deck.addFirst("A");
        deck.addLast("C");
        deck.addLast("D");
        deck.addLast("E");
        assertEquals(5, deck.size());
        assertEquals("E", deck.removeLast());
        assertEquals("D", deck.removeLast());
        assertEquals(3, deck.size());
    }

    @Test
    void removeFirstThrowsExceptionIfEmptyTest() {
        Deque<String> emptyDeck = new Deque<String>();
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            emptyDeck.removeFirst();
        });
    }

    @Test
    void removeLastThrowsExceptionIfEmptyTest() {
        Deque<String> emptyDeck = new Deque<String>();
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            emptyDeck.removeLast();
        });
    }

    @Test
    void isEmptyTest() {
        Deque<String> deck = new Deque<String>();
        assertTrue(deck.isEmpty());
        deck.addFirst("B");
        assertFalse(deck.isEmpty());
        deck.removeFirst();
        assertTrue(deck.isEmpty());
        deck.addFirst("A");
        deck.addLast("C");
        deck.addLast("D");
        deck.addLast("E");
        assertFalse(deck.isEmpty());
    }
}
