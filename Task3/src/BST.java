public class BST<Key extends Comparable<Key>, Value> {
    private Node<Key, Value> root;

    private Value put(Node<Key, Value> node) {
        root = isEmpty() ? node : root.insert(node);
        return node.getValue();
    }

    public Value put(Key key, Value value) {
        return put(new Node<>(key, value));
    }

    public Value get(Key key) {
        Node<Key, Value> node = root.search(key);
        return node != null ? node.getValue() : null;
    }

    public boolean containsKey(Key key) {
        return root != null && root.search(key) != null;
    }

    public Value remove(Key key) {
        if (isEmpty()) {
            return null;
        }
        Value v = get(key);
        root = root.remove(key);
        return v;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public String print() {
        String s = "";
        s = printNode(root, s);
        return s.substring(0, s.length() - 1);
    }

    private String printNode(Node<Key, Value> node, String current) {
        if (node != null) {
            current += printNode(node.getLeft(), "");
            current += node.getKey().toString() + " ";
            if (node.getRight() != null)
                current += printNode(node.getRight(), "");
        }
        return current;
    }
}