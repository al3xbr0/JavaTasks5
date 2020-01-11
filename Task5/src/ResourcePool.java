import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class ResourcePool<T extends Callable<String>> {
    private static final String EQUATIONS_File = "equations.txt";
    private static final String SOLUTIONS_File = "equationsSolutions.txt";
    private static final int POOL_SIZE = 10;
    private static final int TASKS_AMOUNT = 10000;
    private static final int IDLE_TIME = 4 * 1000;

    private final ArrayList<Solver<T>> solvers = new ArrayList<>();
    private final Queue<T> taskQueue = new LinkedList<>();

    private final ArrayList<String> solutions = new ArrayList<>();

    public synchronized boolean hasTasks() {
        return !taskQueue.isEmpty();
    }

    public synchronized T getNext() {
        return taskQueue.poll();
    }

    public synchronized void addTask(T task) {
        taskQueue.add(task);
    }

    public synchronized void addSolution(String solution) {
        solutions.add(solution);
    }

    public ArrayList<String> getSolutions() {
        return solutions;
    }

    private void startSolvers() {
        for (int i = 0; i < POOL_SIZE; i++) {
            solvers.add(new Solver<>(this));
        }
        for (var t : solvers) {
            t.start();
        }
        while (hasTasks()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() throws InterruptedException {
        startSolvers();
        while (hasTasks()) {
            Thread.sleep(IDLE_TIME);
        }
        System.out.printf("Solved %d quadratic equations\n", TASKS_AMOUNT);
        System.out.println("Pool has no more tasks to do.");
    }


    public static void main(String[] args) {
        generateEquations(TASKS_AMOUNT);

        ResourcePool<QuadraticEquation> pool = new ResourcePool<>();
        Scanner in = null;
        try {
            in = new Scanner(new File(EQUATIONS_File));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < TASKS_AMOUNT; i++) {
            String[] str = in.nextLine().split(" ");
            pool.addTask(new QuadraticEquation(Double.parseDouble(str[0]),
                    Double.parseDouble(str[1]), Double.parseDouble(str[2])));
        }

        try {
            pool.start();

            var c = pool.getSolutions();
            Files.write(Paths.get(SOLUTIONS_File), c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateEquations(int number) {
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            lines.add(new QuadraticEquation().toString());
        }

        try {
            Files.write(Paths.get(EQUATIONS_File), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}