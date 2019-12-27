import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectoriesFilesPrinterTest {
    private final String path = "C:\\Users\\Alex\\IdeaProjects\\JavaTasks5\\Task4\\test\\TEST_DIR";
    private final String file = "C:\\Users\\Alex\\IdeaProjects\\JavaTasks5\\Task4\\test\\result.txt";
    private String expected;

    @BeforeEach
    void setUp() throws IOException {
        expected = "";
        expected += ">" + path + "\n";

        new File(path + "\\dir1\\newdir").mkdirs();
        new File(path + "\\dev").mkdir();
        new File(path + "\\dev\\null").createNewFile();
        new File(path + "\\dir2").mkdir();
        new File(path + "\\dir2\\2-1").mkdir();

        expected += "|>dev\n" +
                "| |null\n" +
                "|>dir1\n" +
                "| |>newdir\n" +
                "|>dir2\n" +
                "| |>2-1\n";

        new File(path + "\\emptydir").mkdir();
        new File(path + "\\qq.txt").createNewFile();

        expected += "|>emptydir\n" +
                "|qq.txt";
    }


    @Test
    void testMain() throws IOException {
        DirectoriesFilesPrinter.main(new String[]{path, file});
        var ls = Files.readAllLines(Paths.get(file));
        String actual = String.join("\n", ls);

        assertEquals(expected, actual);
        // rmdir /s C:\Users\Alex\IdeaProjects\JavaTasks5\Task4\test\TEST_DIR
    }
}