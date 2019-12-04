import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

public class DoubleLinkedList<T> implements List<T> {
    private Node<T> first = null, last = null;
    private int size = 0;

    public boolean add(T e) {
        Node<T> newNode = new Node<>(e);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.setNext(newNode);
            newNode.setPrev(last);
            last = newNode;
        }
        size++;
        return true;
    }

    public void add(int i, T t) {
        if (i == size) {
            add(t);
            return;
        }
        Node<T> iNode = getNodeByIndex(i);
        Node<T> iNodePrev = iNode.getPrev();
        Node<T> newNode = new Node<>(t);
        newNode.setPrev(iNodePrev);
        newNode.setNext(iNode);
        iNode.setPrev(newNode);
        if (iNodePrev != null) {
            iNodePrev.setNext(newNode);
        } else
            first = newNode;
        size++;
    }

    public T remove(int i) {
        Node<T> iNode = getNodeByIndex(i);
        T val = iNode.getValue();
        Node<T> iNodePrev = iNode.getPrev(), iNodeNext = iNode.getNext();
        if (iNodePrev == null) {
            first = iNodeNext;
        } else {
            iNodePrev.setNext(iNodeNext);
            iNode.setPrev(null);
        }
        if (iNodeNext == null) {
            last = iNodePrev;
        } else {
            iNodeNext.setPrev(iNodePrev);
            iNode.setNext(null);
        }
        iNode.setValue(null);
        size--;
        return val;
    }

    public boolean contains(Object o) {
        Node<T> current = first;
        while (current != null) {
            if (current.getValue().equals(o)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int i) {
        return getNodeByIndex(i).getValue();
    }

    private Node<T> getNodeByIndex(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = this.first;
        for (int j = 0; j < i; j++) {
            current = current.getNext();
        }
        return current;
    }

    void foreach(Consumer<? super T> method) {
        Node<T> current = first;
        while (current != null) {
            method.accept(current.getValue());
            current = current.getNext();
        }
    }

    //region Unsupported Methods
    public void clear() {
        throw new UnsupportedOperationException();
    }

    public T set(int i, T t) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(int i, Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    public <T1> T1[] toArray(T1[] t1s) {
        throw new UnsupportedOperationException();
    }

    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    public ListIterator<T> listIterator(int i) {
        throw new UnsupportedOperationException();
    }

    public List<T> subList(int i, int i1) {
        throw new UnsupportedOperationException();
    }
    //endregion
}