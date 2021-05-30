// https://fa20.datastructur.es/materials/discussion/disc03.pdf

class SLList {
    private class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    private IntNode first;

    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    /** Inserts item at position. If position is greater than the size of list, inserts item at the last of the list. */
    public void insert(int item, int position) {
        IntNode p = first;
        if (first == null || position == 0) {
            addFirst(item);
            return;
        }
        while (position > 1 && p.next != null) {
            p = p.next;
            position = position - 1;
        }
        p.next = new IntNode(item, p.next);
    }

    /** Recursively removes all nodes that contain item x. */
    public void removeItem(int x) {
        first = removeItemHelper(x, first);
    }

    /** Removes all nodes with item x starting at the current IntNode and returns a pointer to the first node in that series. */
    private IntNode removeItemHelper(int x, IntNode current) {
        if (current == null) {
            return current;
        } else if (current.item == x) {
            return removeItemHelper(x, current.next);
        } else {
            current.next = removeItemHelper(x, current.next);
            return current;
        }
    }

    /** Reverses the elements of the SLList destructively. */
    public void reverse() {
        if (first == null || first.next == null) {
            return;
        } else {
            IntNode p = first.next;
            first.next = null;

            while (p != null) {
                IntNode nextp = p.next;
                p.next = first;
                first = p;
                p = nextp;
            }
        }
    }

    public static void main(String[] args) {
        SLList s = new SLList();
        s.addFirst(2);
        s.addFirst(3);
        s.insert(1, 0);
        s.insert(4, 3);
        s.insert(5, 2);
        s.insert(5, 0);
        s.insert(5, 100);
        s.removeItem(5);
        s.reverse();
    }
}

class Arr {
    /** Inserts an item into arr at the given position and returns the resulting array.
     * This method cannot work destructively because arr will not have any empty space for moving items over to make space for the new one.
     */
    public static int[] insert(int[] arr, int item, int position) {
        int[] resultArray = new int[arr.length + 1];
        position = Math.min(position, arr.length);
        System.arraycopy(arr, 0, resultArray, 0, position);
        resultArray[position] = item;
        System.arraycopy(arr, position, resultArray, position + 1, arr.length - position);
        return resultArray;
    }

    private static int sumOfArray(int[] arr) {
        int sum = 0;
        for (int x: arr) {
            sum = sum + x;
        }
        return sum;
    }

    /** A non-destructive method that replaces the number at index i with arr[i] copies of itself. */
    public static int[] replicate(int[] arr) {
        int[] result = new int[sumOfArray(arr)];
        int current = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i]; j++) {
                result[current] = arr[i];
                current = current + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 5, 6};
        int[] b = insert(a, 4, 3);
        b = insert(b, 10, 10);
        b = replicate(a);
    }
}