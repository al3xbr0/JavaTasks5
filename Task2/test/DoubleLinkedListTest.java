import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoubleLinkedListTest {
    private DoubleLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new DoubleLinkedList<>();
    }

    @Test
    void DoubleLinkedList_NotNull() {
        assertNotNull(list);
    }

    @Test
    void isEmpty() {
        assertTrue(list.isEmpty());
        list.add(42);
        assertFalse(list.isEmpty());
    }

    @Test
    void size() {
        list.add(13);
        list.add(0);
        list.add(2, 5);
        int expected = 3;
        assertEquals(expected, list.size());
    }

    @Test
    void add() {
        list.add(12);
        list.add(17);
        list.add(0, 6);
        assertNotNull(list);
    }

    @Test
    void remove() {
        list.add(2);
        list.add(4);
        list.add(8);
        list.add(6);
        for (int i = list.size() - 1; i >= 0; i--) {
            list.remove(i);
        }
        assertTrue(list.isEmpty());
    }

    @Test
    void contains() {
        list.add(10);
        list.add(15);
        list.add(20);
        assertTrue(list.contains(15));
        assertFalse(list.contains(0));
    }

    @Test
    void get() {
        list.add(17);
        list.add(-26);
        list.add(84);
        Integer expected = -26;
        assertEquals(expected, list.get(1));
    }

    @Test
    void foreach() {
        list.add(-21);
        list.add(76);
        list.add(0);
        list.add(99);
        List<Integer> expected = Arrays.asList(-21, 76, 0, 99);
        Iterator<Integer> iterator = expected.iterator();
        list.foreach(actual -> assertEquals(iterator.next(), actual));
    }
}