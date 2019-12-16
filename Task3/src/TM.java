import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class TM<Key extends Comparable<Key>, Value> extends BST<Key, Value> implements Map<Key, Value> {
    public Value get(Object o) {
        return super.get((Key) o);
    }

    public boolean containsKey(Object o) {
        return super.containsKey((Key) o);
    }

    //region Unsupported Methods
    public int size() {
        throw new UnsupportedOperationException();
    }

    public boolean containsValue(Object o) {
        throw new UnsupportedOperationException();
    }

    public Value remove(Object o) {
        throw new UnsupportedOperationException();
    }

    public void putAll(Map<? extends Key, ? extends Value> map) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public Set<Key> keySet() {
        throw new UnsupportedOperationException();
    }

    public Collection<Value> values() {
        throw new UnsupportedOperationException();
    }

    public Set<Entry<Key, Value>> entrySet() {
        throw new UnsupportedOperationException();
    }
    //endregion
}