// https://fa20.datastructur.es/materials/discussion/disc07.pdf

public class BSTMap {
    private class Node {
        int key;
        int value;
        Node left;
        Node right;

        Node (int key, int value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    Node head;          // Contains the node at the head of the tree

    /** Returns the value associated with key in the BSTMap or null if key is not present. */
    public Integer find(int key) {
        return findHelper(key, head);
    }

    /** Returns the value associated with key in the BSTMap rooted at node n or null if key is not present. */
    private Integer findHelper(int key, Node n) {
        if (n == null) {
            return null;
        } else if (n.key == key) {
            return n.value;
        } else if (n.key < key) {
            return findHelper(key, n.left);
        } else {
            return findHelper(key, n.right);
        }
    }
}
