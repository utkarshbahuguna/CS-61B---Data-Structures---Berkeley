//// https://fa20.datastructur.es/materials/discussion/examprep05.pdf

import java.util.Iterator;
import java.util.NoSuchElementException;
import Disc04.IntList;

/** Iterates over every Kth element of the IntList given to the constructor. */

public class KthIntList implements Iterator<Integer> {
    public int k;
    private IntList curList;
    private boolean hasNext;

    public KthIntList(IntList I, int k) {
        this.k = k;
        this.curList = I;
        this.hasNext = true;
    }

    public boolean hasNext() {
        return this.hasNext;
    }

    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Next kth item not available.");
        } else {
            int next = curList.first;
            for(int i = 0; i < k; i++) {
                curList = curList.rest;
                if(curList == null) {
                    hasNext = false;
                    break;
                }
            }
            return next;
        }
    }

    public static void main(String[] args) {
        IntList a = IntList.of(1, 2, 3, 4, 5, 6, 7);
        KthIntList itr = new KthIntList(a, 3);
        for (int i = 0; i < 10; i++) {
            System.out.print(itr.next() + " ");
        }
    }

}
