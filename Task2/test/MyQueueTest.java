import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyQueueTest {
    private MyQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new MyQueue<>();
    }

    @Test
    void peek() {
        Assertions.assertNull(queue.peek());
        queue.add(1);
        queue.add(2);
        queue.add(3);
        Integer expected = 1;
        Assertions.assertEquals(expected, queue.peek());
    }
}