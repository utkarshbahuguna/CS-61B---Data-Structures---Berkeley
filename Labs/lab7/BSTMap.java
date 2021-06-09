// https://fa20.datastructur.es/materials/lab/lab7/lab7

import edu.princeton.cs.algs4.BST;

import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node<K, V> root;
    private int size;

    public BSTMap() {
        root = null;
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the node with specified key in tree rooted at node root. If no such key exists, returns null. */
    private Node<K, V> search(K key, Node<K, V> root) {
        if (root == null) {
            return null;
        }
        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            return root;
        } else if (cmp < 0) {
            return search(key, root.left);
        } else {
            return search(key, root.right);
        }
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return search(key, root) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        Node<K, V> n = search(key, root);
        return (n == null) ? null : n.value;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /** Inserts/updates the key-value pair, if it doesn't exist in the tree rooted at root node. */
    private Node<K, V> insert(K key, V value, Node<K, V> root) {
        if (root == null) {
            size += 1;
            return new Node<>(key, value);
        }
        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            root.value = value;
        } else if (cmp < 0) {
            root.left = insert(key, value, root.left);
        } else {
            root.right = insert(key, value, root.right);
        }
        return root;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        root = insert(key, value, root);
    }

    /* Returns a Set view of the keys contained in the tree rooted at root. */
    private Set<K> setOfKeys(Node<K, V> root) {
        Set<K> keys = new HashSet<>();
        if (root == null) {
            return keys;
        }
        keys.add(root.key);
        keys.addAll(setOfKeys(root.left));
        keys.addAll(setOfKeys(root.right));
        return keys;
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return setOfKeys(root);
    }

    /** Prints out the BST rooted at node root in order of increasing key. */
    private void printBST(Node<K, V> root) {
        if (root == null) {
            return;
        }
        printBST(root.left);
        System.out.println(root.key + ": " + root.value);
        printBST(root.right);
    }


    /** Prints out the map in order of increasing key. */
    public void printInOrder() {
        printBST(root);
    }

    /** Returns the node with minimum key in the BST rooted at root node. Returns null if tree is empty.*/
    private K minBST(Node<K, V> root) {
        if (root == null) {
            return null;
        }
        while (root.left != null) {
            root = root.left;
        }
        return root.key;
    }

    /** Returns maximum key in the BST rooted at node root. Returns null if tree is empty.*/
    private K maxBST(Node<K, V> root) {
        if (root == null) {
            return null;
        }
        while (root.right != null) {
            root = root.right;
        }
        return root.key;
    }

    /** Returns key which is the predecessor of the key of node x. If no predecessor exist, return null. */
    private K predecessor(Node<K, V> x) {
        if (x == null) {
            return null;
        }
        return maxBST(x.left);
    }

    /** Returns the key which is the predecessor of the key of node x. If no successor exists, return null. */
    private K succecessor(Node<K, V> x) {
        if (x == null) {
            return null;
        }
        return minBST(x.right);
    }

    /** Removes key from the BST rooted at node root and returns the root. */
    private Node<K, V> removeKey(K key, Node<K, V> root) {
        if (root == null) {
            return null;
        }

        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = removeKey(key, root.left);
        } else if (cmp > 0) {
            root.right = removeKey(key, root.right);
        }

        // Root node key is equal to the key to be removed.
        else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                K predecessor = predecessor(root);
                root.key = predecessor;
                root.left = removeKey(predecessor, root.left);
            }
        }
        return root;
    }

    /* Removes the mapping for the specified key from this map if present. */
    @Override
    public V remove(K key) {
        return remove(key, get(key));
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value.
     */
    @Override
    public V remove(K key, V value) {
        Node<K, V> n = search(key, root);
        if (n != null && n.value == value) {
            size -= 1;
            root = removeKey(key, root);
            return n.value;
        }
        return null;
    }

    private class BSTIterator<K> implements Iterator<K> {
        private List<Node<K, V>> stack;

        /** Creats a BST Iterator for a BST rooted at node root. */
        public BSTIterator(Node root) {
            stack = new ArrayList<>();
            pushToStack(root);
        }

        /** Push all elements on the left edge of the tree rooted at node root on to the stack. */
        private void pushToStack(Node<K, V> root) {
            while(root != null) {
                stack.add(root);
                root = root.left;
            }
        }

        @Override
        public boolean hasNext() {
            return stack.size() != 0;
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<K, V> next = stack.remove(stack.size() - 1);
            pushToStack(next.right);
            return next.key;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTIterator<>(root);
    }

    public static void main(String[] args) {

    }

}
