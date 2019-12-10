public class Node<Key extends Comparable<Key>, Value> {
    private int height;
    Key key;
    private Value value;
    private Node<Key, Value> left, right;

    //region
    public Node(Key key, Value value) {
        height = 1;
        this.key = key;
        this.value = value;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
    //endregion

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
        if (k.compareTo(this.key) > 0) {
            right = right != null ?
                    right.remove(k) : null;
        } else if (k.compareTo(this.key) < 0) {
            left = left != null ?
                    left.remove(k) : null;
        } else {
            Node<Key, Value> l = left,
                    r = right;
//            this.left = null;
//            this.right = null;
//            this.key = null;

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
        if (k.compareTo(this.key) > 0 && right != null) {
            return right.search(k);
        } else if (k.compareTo(this.key) < 0 && left != null) {
            return left.search(k);
        } else if (k.compareTo(this.key) == 0) {
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

    public Node<Key, Value> balance() {
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

    private int getHeight(Node node) {
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