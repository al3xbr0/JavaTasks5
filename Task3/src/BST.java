public class BST<Key extends Comparable<Key>, Value> {
    private Node<Key, Value> root, current;

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
            //current = root;
        } else {
            root = root.insert(node);
        }
    }

    public void add(Key... vals) {
        for (Key v : vals) {
            add(new Node<>(v, null));
        }
    }

    public void remove(Key key) {
        if (!isEmpty()) {
            root = root.remove(key);
        }
    }

    public void print() {
        printNode(root);
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