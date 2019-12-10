import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node<Key, Value> root;

    public BST() {
    }

    public BST(Node<Key, Value> node) {
        root = node;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void add(Node<Key, Value> node) {
        if (isEmpty()) {
            root = node;
        } else {
            root = root.insert(node);
        }
    }

    public void add(Node<Key, Value>... nodes) {
        for (var node : nodes) {
            add(node);
        }
    }

    public void remove(Key key) {
        if (!isEmpty()) {
            root = root.remove(key);
        }
    }

    public boolean contains(Key key) {
        return root.search(key) != null;
    }

    public Value getElement(Key key) {
        Node<Key, Value> node = root.search(key);
        if (node == null) {
            throw new NoSuchElementException();
        }
        return node.getValue();
    }

    public void print() {
        printNode(root);
        System.out.println();
    }

    private void printNode(Node node) {
        if (node != null) {
            printNode(node.getLeft());
            System.out.print(node.key + " ");
            if (node.getRight() != null)
                printNode(node.getRight());
        }
    }
}