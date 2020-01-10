import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class DirectoriesFilesPrinter {
    private static PrintStream out;

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            return;
        }
        String path = args[0];
        String resultFile = args[1];
        out = new PrintStream(new FileOutputStream(resultFile));

        File root = new File(path);
        out.println(">" + root.getAbsolutePath());
        DirectoriesFilesPrinter.find(root, 1);
    }

    private static void find(File path, int d) {
        var dirList = path.listFiles(File::isDirectory);
        var filesList = path.listFiles(File::isFile);

        for (var f : dirList) {
            out.println("| ".repeat(d - 1)
                    + "|>" + f.getName());
            find(f, d + 1);
        }
        for (var f : filesList) {
            out.println("| ".repeat(d - 1)
                    + "|" + f.getName());
        }
    }
}