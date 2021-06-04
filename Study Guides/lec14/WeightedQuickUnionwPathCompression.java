import java.util.ArrayList;
import java.util.List;

/** A disjoint sets implementation with path compression. */
public class WeightedQuickUnionwPathCompression {
    // Tracks the index of the parent of each node. */
    private int[] id;

    /** Initializes a Quick Union Data Structure with n items,
     * where all items are represented as indices of an array from 0 to n-1 and are disconnected initially.
     */
    public WeightedQuickUnionwPathCompression(int n) {
        id = new int[n];
        for(int i = 0; i < n; i++) {
            id[i] = -1;
        }
    }

    /** Retruns the index of the root parent of i. */
    private int find(int i) {
        /** List of nodes found along the way to the root. */
        int root = i;

        /** While root is not a root node. */
        while(id[root] > -1) {
            /** Change root to it's parent node. */
            root = id[root];
        }
        // root is now the root node of i.

        /** Path Compression:
         * For each node found along the way to the root, change the parent to the root.
         */
        while(i != root) {
            int parent = id[i];         // Temp variable to store the index of parent of i.
            id[i] = root;               // Change the parent of i to root.
            i = parent;                 // Move one node up towards the root node.
        }
        return root;
    }

    /** Connects two elements if they are not already connected. */
    public void connect(int a, int b) {
        if (isConnected(a, b)) { return; }

        int root_a = find(a);
        int root_b = find(b);
        /** If tree rooted at root_a is larger in size. */
        if (id[root_a] < id[root_b]) {

            /** Increment the size of tree at root_a by size of tree at root_b. */
            id[root_a] += id[root_b];

            /** Add tree rooted at root_b below root_a. */
            id[root_b] = root_a;
        } else {
            id[root_b] += id[root_a];
            id[root_a] = root_b;
        }

    }

    public boolean isConnected(int a, int b) {
        return (find(a) == find(b));
    }
}
