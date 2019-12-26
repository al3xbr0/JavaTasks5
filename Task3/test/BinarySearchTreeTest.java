import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    private BinarySearchTree<Integer, Integer> bst;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree<>();
    }

    @Test
    void isEmpty() {
        assertTrue(bst.isEmpty());
        bst.put(1, 444);
        assertFalse(bst.isEmpty());
    }

    @Test
    void containsKey() {
        assertFalse(bst.containsKey(1));
        assertFalse(bst.containsKey(4));
        bst.put(3, 90);
        bst.put(15, 3);
        assertTrue(bst.containsKey(15));
    }

    @Test
    void get() {
        bst.put(2, 16);
        bst.put(7, 10);
        bst.put(1, 121);
        bst.put(100, 2);
        assertEquals(121, bst.get(1));
        assertNull(bst.get(0));
    }

    @Test
    void remove() {
        bst.put(1, 14);
        bst.put(10, 19);
        bst.put(2, 2);
        assertEquals(19, bst.remove(10));
        assertFalse(bst.containsKey(10));
        bst.remove(1);
        bst.remove(2);
        assertTrue(bst.isEmpty());
    }

    @Test
    void print() {
        bst.put(33, 7);
        bst.put(5, 9);
        bst.put(35, 2);
        bst.put(1, 4);
        bst.put(20, 57);
        bst.put(99, 0);
        bst.put(17, 33);
        bst.put(18, 21);
        bst.put(19, 80);
        bst.put(31, 3);
        bst.put(4, 69);

        String expected = "1 4 5 17 18 19 20 31 33 35 99";
        assertEquals(expected, bst.print());
    }
}