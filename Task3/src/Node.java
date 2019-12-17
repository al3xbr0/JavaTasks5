class Node<Key extends Comparable<Key>, Value> {
    private int height;
    private final Key key;
    private final Value value;
    private Node<Key, Value> left, right;

    Key getKey() {
        return key;
    }

    Value getValue() {
        return value;
    }

    Node<Key, Value> getLeft() {
        return left;
    }

    Node<Key, Value> getRight() {
        return right;
    }

    Node(Key key, Value value) {
        height = 1;
        this.key = key;
        this.value = value;
    }

    Node<Key, Value> insert(Node<Key, Value> node) {
        if (node.key.compareTo(this.key) >= 0) {
            right = right != null ?
                    right.insert(node) : node;
        } else {
            this.left = this.left != null ?
                    this.left.insert(node) : node;
        }
        return balance();
    }

    Node<Key, Value> remove(Key k) {
        if (k.compareTo(key) > 0) {
            right = right != null ?
                    right.remove(k) : null;
        } else if (k.compareTo(key) < 0) {
            left = left != null ?
                    left.remove(k) : null;
        } else {
            Node<Key, Value> l = left,
                    r = right;
            if (r == null) {
                return l;
            }
            Node<Key, Value> min = r.findMin();
            min.right = r.removeMin();
            min.left = l;
            return min.balance();
        }
        return balance();
    }

    Node<Key, Value> search(Key k) {
        if (k.compareTo(key) > 0 && right != null) {
            return right.search(k);
        }
        if (k.compareTo(key) < 0 && left != null) {
            return left.search(k);
        }
        if (k.compareTo(key) == 0) {
            return this;
        }
        return null;
    }

    private Node<Key, Value> findMin() {
        return left != null ? left.findMin() : this;
    }

    private Node<Key, Value> removeMin() {
        if (left == null) {
            return right;
        }
        left = left.removeMin();
        return balance();
    }

    private Node<Key, Value> balance() {
        fixHeight();
        if (balanceFactor() == 2) {
            if (right.balanceFactor() < 0)
                right = right.rotateRight();
            return rotateLeft();
        }
        if (balanceFactor() == -2) {
            if (left.balanceFactor() > 0)
                left = left.rotateLeft();
            return rotateRight();
        }
        return this;
    }

    private int balanceFactor() {
        return getHeight(right) - getHeight(left);
    }

    private int getHeight(Node<Key, Value> node) {
        return node != null ? node.height : 0;
    }

    private void fixHeight() {
        height = Math.max(getHeight(left), getHeight(right)) + 1;
    }

    private Node<Key, Value> rotateRight() {
        Node<Key, Value> node = left;
        left = node.right;
        node.right = this;
        fixHeight();
        node.fixHeight();
        return node;
    }

    private Node<Key, Value> rotateLeft() {
        Node<Key, Value> node = right;
        right = node.left;
        node.left = this;
        fixHeight();
        node.fixHeight();
        return node;
    }
}