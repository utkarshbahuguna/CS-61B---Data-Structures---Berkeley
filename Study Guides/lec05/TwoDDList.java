// https://fa20.datastructur.es/materials/lectures/lec5/lec5

// All objects in Java extend the Object class. So generic arrays can be formed as new Object[].
// 2D arrays with different types can be formed using new Objects [][].

/** A two dimensional DList. */
public class TwoDDList {

    /** A doubly-linked node in the TwoDDList. */
    private class DNode {
        public LinkedListDeque DList;
        public DNode prev;
        public DNode next;

        /** Creates a DNode. */
        public DNode(LinkedListDeque DList, DNode prev, DNode next) {
            this.DList = DList;
            this.prev = prev;
            this.next = next;
        }

        /* Creates a DNode with prev and next as null. */
        public DNode(LinkedListDeque DList) {
            this(DList, null, null);            //Method overloading.
        }
    }

    private int size;
    private DNode sentinel;         //The first DNode is always at sentinel.next and the last DNode at sentinel.prev. The next of the last element is the sentinel.

    /** Creates an empty TwoDDList. */
    public TwoDDList() {
        this(new LinkedListDeque[0]);
    }

    /** Creates a TwoDDList from an arbitrary number of DLists. */
    public TwoDDList(LinkedListDeque ... args) {
        sentinel = new DNode(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

        for (LinkedListDeque DList : args) {
            DNode last = new DNode(DList, sentinel.prev, sentinel);
            sentinel.prev.next = last;
            sentinel.prev = last;
            size = size + 1;
        }
    }

    /** Returns true if TwoDDList is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of DLists in the TwoDDList. */
    public int size() {
        return size;
    }

    /** Prints the DLists in the TwoDDList in separate lines. */
    public void printTwoDDList() {
        DNode node = sentinel.next;

        for(int i = 0; i < size; i++) {
            node.DList.printDeque();
            node = node.next;
        }
    }

    /** Adds a DList to the front of the TwoDDList. */
    public void addFirst(LinkedListDeque DList) {
        DNode first =  new DNode(DList, sentinel, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;

        size += 1;
    }

    /** Adds a DList to the back of the TwoDDList. */
    public void addLast(LinkedListDeque DList) {
        DNode last = new DNode(DList, sentinel.prev, sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;

        size += 1;
    }

    /** Removes and returns the DList at the front of the TwoDDList. If no such DList exists, returns null. */
    public LinkedListDeque removeFirst() {
        DNode first = sentinel.next;
        first.next.prev = sentinel;
        sentinel.next = first.next;

        size -= 1;

        return first.DList;
    }

    /** Removes and returns the DList at the back of the TwoDDList. If no such DList exists, returns null. */
    public LinkedListDeque removeLast() {
        DNode last = sentinel.prev;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;

        size -= 1;

        return last.DList;
    }

    /** Gets the DList at the given index, where 0 is the front, 1 is the next DList, and so forth. If no such DList exists, returns null. */
    public LinkedListDeque get(int index) {
        if (index >= size) {
            return null;
        } else {
            DNode node = sentinel.next;
            for(int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.DList;
        }
    }

    /** Helper function for getRecursive.
     * Gets the DList at ith position starting from the given node. node is the 0th position, node.next is the 1st position, and so on. */
    private LinkedListDeque getDNodeRecursion(DNode node, int i) {
        if (i == 0) {
            return node.DList;
        } else {
            return getDNodeRecursion(node.next, i - 1);
        }
    }

    /** Same as get, but uses recursion. */
    public LinkedListDeque getRecursive(int index) {
        assert index >= 0 : "Index cannot be negative";
        if (index >= size) {
            return null;
        } else {
            return getDNodeRecursion(sentinel.next, index);
        }
    }

    /** Returns true if an item can be uniformly added to the DList at index i.
     * Uniform addition means that no DList in the 2DDList will have a size greater than any other by more than 1 element. */
    private boolean canUniformlyAdd (int index) {
        LinkedListDeque d = get(index);
        for (int j = 0; j < size; j++) {
            if (d.size() - get(j).size() > 0) {
                return false;
            }
        }
        return true;
    }

    /** Returns the index of the next DList in the 2DDList. If i is the last DList, returns 0. */
    private int nextDListIndex(int i) {
        if (i == this.size - 1) {
            return 0;
        } else {
            return i + 1;
        }
    }

    /** Tries to add item to ith DList, if the length of ith DList is greater than any other DList by 1, tries to add the item to the next DList with the same constraints. */
    public void addUniform(Object item, int i) {
        if (canUniformlyAdd(i)) {
            get(i).addLast(item);
        } else {
            addUniform(item, nextDListIndex(i));
        }
    }

    public static void main(String[] args) {
        LinkedListDeque a = new LinkedListDeque("i", "am", "nice", "person");
        LinkedListDeque b = new LinkedListDeque(4, 5, 6);
        LinkedListDeque c = new LinkedListDeque();

        TwoDDList d = new TwoDDList(a, b, c);
        TwoDDList d1 = new TwoDDList();
        d.printTwoDDList();

        for(int i = 10; i <= 100; i = i + 10) {
            d.addUniform(i, 0);
        }
        d.printTwoDDList();
    }
}
