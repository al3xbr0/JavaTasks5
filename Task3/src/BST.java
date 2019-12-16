public class BST<Key extends Comparable<Key>, Value> {
    private Node<Key, Value> root;

    public Value put(Node<Key, Value> node) {
        root = isEmpty() ? node : root.insert(node);
        return node.getValue();
    }

    public Value put(Key key, Value value) {
        return put(new Node<>(key, value));
    }

    public void put(Node<Key, Value>... nodes) {
        for (var node : nodes) {
            put(node);
        }
    }

    public Value get(Key key) {
        Node<Key, Value> node = root.search(key);
        return node != null ? node.getValue() : null;
    }

    public boolean containsKey(Key key) {
        return root != null && root.search(key) != null;
    }

    public Value remove(Key key) {
        if (!isEmpty()) {
            return null;
        }
        Value v = get(key);
        root = root.remove(key);
        return v;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void print() {
        printNode(root);
        System.out.println();
    }

    private void printNode(Node<Key, Value> node) {
        if (node != null) {
            printNode(node.getLeft());
            System.out.print(node.getKey() + " ");
            if (node.getRight() != null)
                printNode(node.getRight());
        }
    }
}