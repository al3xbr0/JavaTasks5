import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;

public class QuadraticEquation implements Callable<String> {
    private double a, b, c;

    public QuadraticEquation(double a, double b, double c) {
        if (a == 0d) {
            throw new IllegalArgumentException();
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public QuadraticEquation() {
        Random random = new Random();
        a = random.nextInt(256) + 1;
        b = random.nextInt(257 + 256) - 256;
        c = random.nextInt(257 + 256) - 256;
    }

    private ArrayList<Double> solve() {
        final ArrayList<Double> solution = new ArrayList<>(2);

        double D = Math.pow(b, 2) - 4 * a * c;
        if (D > 0) {
            solution.add(
                    (-b + Math.sqrt(D)) / (2 * a)
            );
            solution.add(
                    (-b - Math.sqrt(D)) / (2 * a)
            );
        } else if (D == 0) {
            solution.add(-b / 2 * a);
        }
        return solution;
    }

    @Override
    public String call() {
        String s = String.format("%.0fx^2%+.0fx%+.0f=0: ", a, b, c);

        ArrayList<Double> solution = solve();
        if (solution.size() == 0) {
            return s + "no roots";
        }
        s = s + solution.get(0) + (solution.size() > 1 ? ", " +
                solution.get(1) : "");
        return s;
    }

    @Override
    public String toString() {
        return (int) a + " " + (int) b + " " + (int) c;
    }
}