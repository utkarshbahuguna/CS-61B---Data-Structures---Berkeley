//https://fa20.datastructur.es/materials/discussion/examprep03.pdf

class Arry {

    /** Takes in a 2-D array x and returns a 1-D array that contains all of the arrays in x concatenated together. */
    public static int[] flatten(int[][] x) {
        int totalLength = 0;
        for (int[] arr : x) {
            totalLength += arr.length;
        }

        int[] a = new int[totalLength];
        int aIndex = 0;
        for (int[] arr : x) {
            System.arraycopy(arr, 0, a, aIndex, arr.length);
            aIndex += arr.length;
        }

        return a;
    }

    public static void main(String[] args) {
        int[][] b = {{1, 2, 3}, {}, {7, 8}};
        int[] a = Arry.flatten(b);
    }
}

class IntList {
    public int first;
    public IntList rest;

    /** A List with first FIRST0 and rest REST0. */
    public IntList(int first0, IntList rest0) {
        first = first0;
        rest = rest0;
    }


    /**
     * Returns true if X is an IntList containing the same sequence of ints as THIS. */
    @Override
    public boolean equals(Object x) {
        if (!(x instanceof IntList)) {
            return false;
        }
        IntList L = (IntList) x;
        IntList p;

        for (p = this; p != null && L != null; p = p.rest, L = L.rest) {
            if (p.first != L.first) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }
        return true;
    }

    /** Returns a new IntList containing the ints in ARGS. */
    public static IntList list(Integer... args) {
        IntList result, p;

        if (args.length > 0) {
            result = new IntList(args[0], null);
        } else {
            return null;
        }

        int k;
        for (k = 1, p = result; k < args.length; k += 1, p = p.rest) {
            p.rest = new IntList(args[k], null);
        }
        return result;
    }

    /** Keeps the elements in THIS IntList skipping 1 element for the second entry, 2 for the third, 3 for the fourth and so on. */
    public void skippify() {
        IntList p = this;
        int n = 1;
        while (p != null) {
            IntList next = p.rest;
            for (int i = 0; i < n; i++) {
                if (next == null) {
                    break;
                }
                next = next.rest;
            }
            p.rest = next;
            p = next;
            n += 1;
        }
    }

    /** Destructively changes the ordering of a given IntList so that even indexed links precede odd indexed links. */
    public static void evenOdd(IntList lst) {
        if (lst == null || lst.rest == null || lst.rest.rest == null) {
            return;
        }
        IntList second = lst.rest;
        int index = 0;
        while (!(index % 2 == 0 && (lst.rest == null || lst.rest.rest == null))) {
            IntList temp = lst.rest;
            lst.rest = lst.rest.rest;
            lst = temp;
            index += 1;
        }
        lst.rest = second;
    }

    public static void main(String[] args) {
        IntList a = IntList.list(1, 2, 3, 4);
        IntList b = IntList.list(9, 8, 7, 6, 5);
//        a.skippify();
//        b.skippify();
        IntList.evenOdd(a);
        System.out.println(a.equals(IntList.list(1, 3, 2, 4)));
        IntList.evenOdd(b);
        System.out.println(b.equals(IntList.list(9, 7, 5, 8, 6)));
    }
}
