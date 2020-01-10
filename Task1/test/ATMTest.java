import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ATMTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    private final String expectedInvalid = "ERROR: Invalid input!";
    private final String expectedNegative = "ERROR: Sum and Values must be positive!";

    private ATM task;

    @Test
    void EmptyInput() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> task = new ATM("", new String[]{""}));
        assertEquals(expectedInvalid, e.getMessage());
    }

    @Test
    void TextInput1() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> task = new ATM("qwerty", new String[]{"a"}));
        assertEquals(expectedInvalid, e.getMessage());
    }

    @Test
    void TextInput2() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> task = new ATM("4", new String[]{"2", "a"}));
        assertEquals(expectedInvalid, e.getMessage());
    }

    @Test
    void NegativeInput1() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> task = new ATM("-50", new String[]{"1", "2"}));
        assertEquals(expectedNegative, e.getMessage());
    }

    @Test
    void NegativeInput2() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> task = new ATM("0", new String[]{"4", "2"}));
        assertEquals(expectedNegative, e.getMessage());
    }

    @Test
    void NegativeInput3() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> task = new ATM("10", new String[]{"-3", "7"}));
        assertEquals(expectedNegative, e.getMessage());
    }

    @Test
    void Test1() {
        System.setOut(new PrintStream(out));
        task = new ATM("5", new String[]{"3", "2"});
        task.solve(false);
        String actual = out.toString().replace(" \r\n", "");
        assertEquals("2 3", actual);
    }

    @Test
    void Test2() {
        System.setOut(new PrintStream(out));
        task = new ATM("5", new String[]{"1", "2"});
        assertEquals(3, task.solve(false));

        String[] actual = out.toString().split(" \r\n");
        String[] expected = {"1 2 2", "1 1 1 2", "1 1 1 1 1", "3 combinations in total"};
        assertArrayEquals(expected, actual);
    }

    @Test
    void Test3() {
        System.setOut(new PrintStream(out));
        task = new ATM("10", new String[]{"5", "1", "2"});
        assertEquals(10, task.solve(false));

        String[] actual = out.toString().split(" \r\n");
        String[] expected = {"5 5", "2 2 2 2 2", "1 2 2 5", "1 1 2 2 2 2",
                "1 1 1 2 5", "1 1 1 1 2 2 2", "1 1 1 1 1 5", "1 1 1 1 1 1 2 2",
                "1 1 1 1 1 1 1 1 2", "1 1 1 1 1 1 1 1 1 1", "10 combinations in total"};
        assertArrayEquals(expected, actual);
    }

    @Test
    void TestFormatted() {
        System.setOut(new PrintStream(out));
        task = new ATM("15", new String[]{"5", "4", "2", "3"});
        assertEquals(14, task.solve(true));

        String expected =
                        "+----------+----------+----------+----------+----------+\n" +
                        "| number   | $2       | $3       | $4       | $5       |\n" +
                        "+----------+----------+----------+----------+----------+\n" +
                        "| 1        | x0       | x0       | x0       | x3       |\n" +
                        "| 2        | x0       | x1       | x3       | x0       |\n" +
                        "| 3        | x0       | x2       | x1       | x1       |\n" +
                        "| 4        | x0       | x5       | x0       | x0       |\n" +
                        "| 5        | x1       | x0       | x2       | x1       |\n" +
                        "| 6        | x1       | x1       | x0       | x2       |\n" +
                        "| 7        | x1       | x3       | x1       | x0       |\n" +
                        "| 8        | x2       | x1       | x2       | x0       |\n" +
                        "| 9        | x2       | x2       | x0       | x1       |\n" +
                        "| 10       | x3       | x0       | x1       | x1       |\n" +
                        "| 11       | x3       | x3       | x0       | x0       |\n" +
                        "| 12       | x4       | x1       | x1       | x0       |\n" +
                        "| 13       | x5       | x0       | x0       | x1       |\n" +
                        "| 14       | x6       | x1       | x0       | x0       |\n" +
                        "+----------+----------+----------+----------+----------+\n" +
                        "|       14 combinations in total                       |\n" +
                        "+----------+----------+----------+----------+----------+";

        String[] actualS = out.toString().split("\r\n");
        String actual = String.join("\n", actualS);
        assertEquals(expected, actual);
    }
}