import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyQueueTest {
    private MyQueue<Integer> queue;

    @Before
    public void setUp() {
        queue = new MyQueue<>();
    }

    @Test
    public void peek() {
        Assert.assertNull(queue.peek());
        queue.add(1);
        queue.add(2);
        queue.add(3);
        Integer expected = 1;
        Assert.assertEquals(expected, queue.peek());
    }
}