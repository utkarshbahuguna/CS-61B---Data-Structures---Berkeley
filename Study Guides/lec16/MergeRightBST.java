// https://inst.eecs.berkeley.edu//~cs61b/fa14/samples/2009/test2.pdf

class IntTree {
    public IntTree (int data, IntTree left, IntTree right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
    public final int data;
    public IntTree left, right;

    public static void print(IntTree T, int indent) {
        if (T == null) {
            return;
        }
        System.out.println(" ".repeat(indent) + T.data);
        print(T.left, indent + 2);
        print(T.right, indent + 2);
    }

    public static void printInOrder(IntTree T) {
        if (T == null) {
            return;
        }
        printInOrder(T.left);
        System.out.println(T.data);
        printInOrder(T.right);
    }
}

public class MergeRightBST {

    /** Assuming that T and L are binary search trees each with a single
     *  sentinel tree node, and that all left children in L aside from
     *  the sentinel are empty (L is "right-leaning"), returns (the
     *  sentinel of) a binary search tree containing the original elements
     *  of T and L.  The operation is destructive, and creates no
     *  new nodes.  In the worst case, it takes time linear in the
     *  total number of items in T and L.
     */
    public static IntTree mergeRight(IntTree T, IntTree L) {
        mergeTill(T.left, L.left, (int) Double.POSITIVE_INFINITY);
        return T;
    }

    private static IntTree mergeTill(IntTree T, IntTree L, int x) {
        if (cont(T, L) && T.left == null && L.data < T.data) {
            T.left = new IntTree(L.data, null, null);
            L = mergeTill(T.left, L.right, T.data);
        } else if (cont(T, L) && T.right == null && L.data > T.data && L.data < x) {
            T.right = new IntTree(L.data, null, null);
            L = mergeTill(T.right, L.right, x);
        }

        L = cont(T, L)? mergeTill(T.left, L, T.data) : L;
        L = cont(T, L)? mergeTill(T.right, L, x) : L;

        return L;
    }

    private static boolean cont(IntTree T, IntTree L) {
        return (!(L == null || T == null));
    }

    public static void main(String[] args) {
        IntTree T = new IntTree((int) Double.POSITIVE_INFINITY,
                        new IntTree(12,
                            new IntTree(3,
                                    null,
                                    new IntTree(10,
                                            new IntTree(8, null, null),
                                            null)),
                            new IntTree(16,
                                    new IntTree(14, null, null),
                                    new IntTree(27, null, null))),
                            null);

        IntTree.print(T, 1);
        IntTree.printInOrder(T);

        IntTree L = new IntTree((int) Double.POSITIVE_INFINITY,
                                   new IntTree(1,
                                           null,
                                           new IntTree(2,
                                                   null,
                                                       new IntTree(9,
                                                               null,
                                                                    new IntTree(11,
                                                                                null,
                                                                                    new IntTree(26, null, null))))),
                                                null);

        T = MergeRightBST.mergeRight(T, L);
        IntTree.print(T, 0);

        }
}

