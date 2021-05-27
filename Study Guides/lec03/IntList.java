/* https://fa20.datastructur.es/materials/lectures/lec3/lec3.html
   https://fa20.datastructur.es/materials/lectures/lec4/lec4
   https://www.kartikkapur.com/documents/mt1.pdf#page=7
 */

public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	// Lec 3 - B Level
	/** Return the size of the list using... recursion! */
	public int size() {
		if (this.rest == null) {
            return 1;
        } else {
            return 1 + this.rest.size();
        }
	}

	/** Return the size of the list using no recursion! */
	public int iterativeSize() {
		IntList p = this;
        int size = 1;

        while (p.rest != null) {
            size += 1;
            p = p.rest;
        }
        return size;
	}

	/** Returns the ith value in this list.*/
	public int get(int i) {
        if (i == 0) {
            return first;
        } else {
            return rest.get(i - 1);
        }
	}

     /** Returns an IntList identical to L, but with
      * each element incremented by x. L is not allowed
      * to change. */
    public static IntList incrList(IntList L, int x) {
        if (L.rest == null) {
            L = new IntList(L.first + x, null);
        } else {
            L = new IntList(L.first + x, incrList(L.rest, x));
        }
        return L;
    }

    /** Returns an IntList identical to L, but with
      * each element incremented by x. Not allowed to use
      * the 'new' keyword. */
    public static IntList dincrList(IntList L, int x) {
        if (L.rest == null) {
            L.first += x;
        } else {
            L.first += x;
            L.rest = dincrList(L.rest, x);
        }
        return L;
    }

    /** Adds x to the end of IntList. */
    public void addLast(int x) {
        IntList p = this;
        while (p.rest != null) {
            p = p.rest;
        }
        p.rest = new IntList(x, null);
    }

    /** Adds x to the end of the list and adds squares of all existing list elements right after them. */
    public void addLastSquare(int x) {
        IntList p = this;
        while (p.rest != null) {
            p.rest = new IntList(p.first * p.first, p.rest);
            p = p.rest.rest;
        }
        IntList addition = new IntList(x, null);
        p.rest = new IntList(p.first * p.first, addition);
    }

    /** If two numbers in a row are the same, adds them together to form a larger node. */
    public void addAdjacent() {
        IntList p = this;
        while (p.rest != null) {
            if (p.first == p.rest.first) {
                p.first = p.first + p.rest.first;
                p.rest = p.rest.rest;
            } else {
                p = p.rest;
            }
        }
    }

    public void printList() {
        for (int i = 0; i < this.size(); i++) {
            System.out.print(this.get(i) + " ");
        }
        System.out.println();
    }


	public static void main(String[] args) {
		IntList L = new IntList(15, null);
		L = new IntList(10, L);
		L = new IntList(5, L);

		System.out.println(L.iterativeSize());
		System.out.println(L.size());
		System.out.println(L.get(0));
		System.out.println(L.get(1));
        System.out.println(incrList(L, 3).get(1));
		L.printList();
        dincrList(L, 3);
        L.printList();
        L.addLast(18);
        L.printList();
        L.addAdjacent();
        L.printList();
        L.addLastSquare(7);
        L.printList();
	}
} 