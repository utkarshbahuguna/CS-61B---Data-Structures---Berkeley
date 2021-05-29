/** A AList implementation of a double ended queue (deque). */
public class ArrayDeque<T> {
    private T[] items;      // array holding the items of the deque
    private int first;      // index of the first element of the deque in items
    private int last;       // index of the first element of the deque in items

    private int size;
    private static double USAGE_FACTOR = 0.25;       // size divided by items.length should be at least 25%

    /** Creates an empty ArrayDeque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        first = 0;
        last = items.length - 1;
        size = 0;
    }

    /** Returns the index before index i in the array items. If i is 0, returns the last index of the array. */
    private int minusOne(int i) {
        if (i == 0) { return items.length - 1;
        } else { return i - 1; }
    }

    /** Returns the index after index i in the array items. If i is the last index in the array, returns 0. */
    private int plusOne(int i) {
        if (i == items.length - 1) { return 0;
        } else { return i + 1; }
    }

    /** Creates a new array double in size of the items array and copies elements of items from first to last at the beginning of the new array.
     * Sets items equal to this new array. */
    private void resize(String upOrDown) {
        T[] newItems;
        if (upOrDown.toLowerCase() == "up") {
            newItems = (T[]) new Object[items.length * 2];
        } else {
            newItems = (T[]) new Object[items.length / 2];
        }

        int itemsCopied = 0;
        if (first > last) {
            System.arraycopy(items, first, newItems, 0, items.length - first);
            itemsCopied = items.length - first;
        }
        first = 0;
        System.arraycopy(items, first, newItems, itemsCopied, last - first + 1);
        last = size - 1;
        items = newItems;
    }

    /** For arrays of length 16 or more, returns true if removing one element would make the usage factor less than USAGE_FACTOR, else returns False. */
    private boolean needsDownsizing() {
        return (items.length >= 16 && ((size - 1) / (double) items.length) < USAGE_FACTOR);
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
        int f = first;
        if (!isEmpty()) {
            while (f != last) {
                System.out.print(items[f] + " ");
                f = plusOne(f);
            }
        }
        System.out.print(items[f] + "\n");
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (size == items.length) { resize("up"); }
        first = minusOne(first);
        items[first] =  item;
        size = size + 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (size == items.length) { resize("up"); }
        last = plusOne(last);
        items[last] = item;
        size = size + 1;
        }


    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        if (isEmpty()) { return null;
        } else {
            if (needsDownsizing()) { resize("down"); }
            T firstItem = items[first];
            items[first] = null;
            size = size - 1;
            if (isEmpty()) { first = 0; last = items.length - 1;
            } else { first = plusOne(first); }
            return firstItem;
        }
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        if (isEmpty()) { return null;
        } else {
            if (needsDownsizing()) { resize("down"); }
            T lastItem = items[last];
            items[last] = null;
            size = size - 1;
            if (isEmpty()) { first = 0; last = items.length - 1;
            } else { last = minusOne(last); }
            return lastItem;
        }
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. */
    public T get(int index) {
        if (index >= size) { return null;
        } else {
            int i = (index + first >= items.length) ? (index + first - items.length) : index + first;
            return items[i];
        }
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        for(int i = 0; i < 30; i++) {
            L.addLast(i);
        }
        for(int i = 0; i < 30; i++) {
            L.removeLast();
        }

        for(int i = 0; i < 30; i++) {
            L.addLast(i);
        }

        L.printDeque();
        L.addFirst(100);
        L.addLast(90);
        L.printDeque();
    }
}
