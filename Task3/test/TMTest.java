import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TMTest {
    private TM<Integer, Integer> tm;

    @BeforeEach
    void setUp() {
        tm = new TM<>();
    }

    @Test
    void get() {
        tm.put(3, 22);
        tm.put(0, 5);
        assertEquals(5, tm.get((Object) 0));
    }

    @Test
    void containsKey() {
        tm.put(4, 14);
        tm.put(11, 26);
        assertFalse(tm.containsKey((Object) 3));
        assertTrue(tm.containsKey((Object) 4));
    }
}