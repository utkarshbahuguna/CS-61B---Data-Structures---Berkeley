// https://fa20.datastructur.es/materials/proj/proj1a/proj1a

/** A DLList implementation of a double ended queue (deque). */
public class LinkedListDeque {

    /** A doubly-linked node in the DLList. */
    private class DNode {
        public Object value;
        public DNode prev;
        public DNode next;

        /** Creates a DNode. */
        public DNode(Object value, DNode prev, DNode next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        /* Creates a DNode with prev and next as null. */
        public DNode(Object value) {
            this(value, null, null);            //Method overloading.
        }
    }

    private int size;
    private DNode sentinel;         // The first DNode is always at sentinel.next and the last DNode at sentinel.prev. The next of the last element is the sentinel.

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {          // Constructor Overloading
        this(new Object[0]);
    }

    /** Creates a deque with arbitrary number of elements. */
    public LinkedListDeque(Object ... args) {
        sentinel = new DNode(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

        for (Object item : args) {
            addLast(item);
        }
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. Prints a new line at the end. */
    public void printDeque() {
        DNode node = sentinel.next;

        for(int i = 0; i < size; i++) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(Object item) {
        DNode first =  new DNode(item, sentinel, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;

        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(Object item) {
        DNode last = new DNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;

        size += 1;
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public Object removeFirst() {
        DNode first = sentinel.next;
        first.next.prev = sentinel;
        sentinel.next = first.next;

        size -= 1;

        return first.value;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public Object removeLast() {
        DNode last = sentinel.prev;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;

        size -= 1;

        return last.value;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. */
    public Object get(int index) {
        if (index >= size) {
            return null;
        } else {
            DNode node = sentinel.next;
            for(int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.value;
        }
    }

    /** Helper function for getRecursive.
     * Gets the item at ith position starting from the given node. node is the 0th position, node.next is the 1st position, and so on. */
    private Object getDNodeRecursion(DNode node, int i) {
        if (i == 0) {
            return node.value;
        } else {
            return getDNodeRecursion(node.next, i - 1);
        }
    }

    /** Same as get, but uses recursion. */
    public Object getRecursive(int index) {
        assert index >= 0 : "Index cannot be negative";
        if (index >= size) {
            return null;
        } else {
            return getDNodeRecursion(sentinel.next, index);
        }
    }

    public static void main(String[] args) {
        LinkedListDeque d1 = new LinkedListDeque("i", "am", "nice", "person");
        LinkedListDeque d2 = new LinkedListDeque(6, 7, 3, 4, 5);
        LinkedListDeque d3 = new LinkedListDeque();

        d1.printDeque();
        d2.printDeque();
        d3.printDeque();
    }
}
