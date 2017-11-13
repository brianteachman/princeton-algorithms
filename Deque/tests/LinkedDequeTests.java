import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class LinkedDequeTests {

    @Test
    void dequeSizeTest() {
        LinkedDeque<String> deck = new LinkedDeque<String>();
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
        LinkedDeque<String> deck = new LinkedDeque<String>();
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
        LinkedDeque<String> deck = new LinkedDeque<String>();
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
        LinkedDeque<String> emptyDeck = new LinkedDeque<String>();
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            emptyDeck.removeFirst();
        });
    }

    @Test
    void removeLastThrowsExceptionIfEmptyTest() {
        LinkedDeque<String> emptyDeck = new LinkedDeque<String>();
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            emptyDeck.removeLast();
        });
    }

    @Test
    void isEmptyTest() {
        LinkedDeque<String> deck = new LinkedDeque<String>();
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
