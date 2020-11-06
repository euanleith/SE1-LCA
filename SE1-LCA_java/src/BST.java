public class BST<T extends Comparable<T>> {
    private Node<T> root;

    private static class Node<T extends Comparable<T>> {
        private T val;
        private Node<T> left, right;

        Node(T val) {
            this.val = val;
        }
    }

    void put(T val) {
        root = put(val, root);
    }

    Node<T> put(T val, Node<T> node) {
        if (node == null) return new Node<>(val);
        int cmp = val.compareTo(node.val);
        if (cmp > 0) node.right = put(val, node.right);
        else if (cmp < 0) node.left = put(val, node.left);
        else node.val = val;
        return node;
    }

    boolean contains(T val) {
        return get(val, root) != null;
    }

    Node<T> get(T val, Node<T> node) {
        if (node == null || val == null) return null;
        int cmp = val.compareTo(node.val);
        if (cmp > 0) return get(val, node.right);
        else if (cmp < 0) return get(val, node.left);
        return node;
    }

    T lca(T val1, T val2) {
        if (!contains(val1) || !contains(val2)) return null;
        return lca(val1, val2, root);
    }

    T lca(T val1, T val2, Node<T> root) {
        int cmp1 = val1.compareTo(root.val);
        int cmp2 = val2.compareTo(root.val);
        if (cmp1 > 0 && cmp2 > 0) return lca(val1, val2, root.right);
        else if (cmp1 < 0 && cmp2 < 0) return lca(val1, val2, root.left);
        else return root.val;
    }
}