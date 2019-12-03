import java.util.Queue;

public class QueuE<T> extends DLL<T> implements Queue<T> {
    public boolean offer(T t) {
        return add(t);
    }

    public T peek() {
        if (isEmpty())
            return null;
        return get(0);
    }

    @Override
    public void add(int i, T t) {
        throw new UnsupportedOperationException();
    }

    public T remove() {
        throw new UnsupportedOperationException();
    }

    public T poll() {
        throw new UnsupportedOperationException();
    }

    public T element() {
        throw new UnsupportedOperationException();
    }
}