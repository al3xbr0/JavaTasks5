import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ATM {
    private int sum;
    private final List<Integer> values;
    private int valsNum;
    private int[] valsAmounts;
    private int count;

    private boolean formattedOutput;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Sum of money\nto exchange: ");
        String sum = in.nextLine();
        System.out.print("\nValues of coins\n(space-separated): ");
        String[] vals = in.nextLine().split(" ");
        System.out.println();

        ATM task = new ATM(sum, vals);
        task.solve(true);
    }

    ATM(String sum, String[] vals) {
        values = new ArrayList<>();

        try {
            this.sum = Integer.parseInt(sum);
            if (this.sum <= 0) {
                throw new IllegalArgumentException("ERROR: Sum and Values must be positive!");
            }

            for (String val : vals) {
                int v = Integer.parseInt(val);
                if (v <= 0) {
                    throw new IllegalArgumentException("ERROR: Sum and Values must be positive!");
                }
                if (!values.contains(v)) {
                    values.add(v);
                }
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ERROR: Invalid input!");
        }

        Collections.sort(values);
        valsNum = values.size();
    }

    int solve(boolean formattedOutput) {
        count = 0;
        valsAmounts = new int[valsNum];

        this.formattedOutput = formattedOutput;
        final String separator = "+----------+" + "----------+".repeat(valsNum) + "%n";
        if (formattedOutput) {

            System.out.format(separator);
            System.out.format("| number   |");
            for (int i = 0; i < valsNum; i++) {
                System.out.format(" $%-7d |", values.get(i));
            }
            System.out.format("%n");
            System.out.format(separator);
        }

        recFind(0, sum);

        if (formattedOutput) {
            System.out.format(separator);
            System.out.format("| %8d %-" + (11 * valsNum - 1) + "s |%n", count, "combinations in total");
            System.out.format(separator);
        } else {
            System.out.println(count + " combinations in total ");
        }

        return count;
    }

    private void recFind(int index, int sum) {
        if (index == valsNum) {
            count++;
            print();
        } else {
            if (index == valsNum - 1) {
                if (sum % values.get(index) == 0) {
                    valsAmounts[index] = sum / values.get(index);
                    recFind(index + 1, 0);
                }
            } else {
                for (int i = 0; i <= sum / values.get(index); i++) {
                    valsAmounts[index] = i;
                    recFind(index + 1, sum - values.get(index) * i);
                }
            }
        }
    }

    private void print() {
        if (formattedOutput) {
            System.out.format("| %-8d |", count);
            for (int i = 0; i < valsNum; i++) {
                System.out.format(" x%-7d |", valsAmounts[i]);
            }
            System.out.format("%n");
        } else {
            for (int i = 0; i < valsNum; i++) {
                System.out.print((values.get(i) + " ").repeat(valsAmounts[i]));
            }
            System.out.println();
        }
    }
}