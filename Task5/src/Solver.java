import java.util.concurrent.Callable;

public class Solver<T extends Callable<String>> extends Thread {
    private final ResourcePool<T> pool;

    Solver(ResourcePool<T> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        while (pool.hasTasks()) {
            T task = pool.getNext();
            if (task == null) {
                return;
            }

            try {
                String res = task.call();
                pool.addSolution(Thread.currentThread().getName() + " solved " + res);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}