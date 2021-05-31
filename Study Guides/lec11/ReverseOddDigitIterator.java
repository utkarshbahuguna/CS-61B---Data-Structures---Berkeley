// https://tbp.berkeley.edu/exams/6137/download/#page=8

import java.util.Iterator;

public class ReverseOddDigitIterator implements Iterable<Integer>, Iterator<Integer> {

    private int value;
    public ReverseOddDigitIterator(int v) {
        value = v;
    }

    public boolean hasNext() {
        if (value == 0) {
            return false;
        }
        if ((value % 10) % 2 != 0 ) {
            return true;
        } else {
            value = value / 10;
            return hasNext();
        }
    }

    public Integer next() {
        int d = value % 10;
        value = value / 10;
        return d;
    }

    public Iterator<Integer> iterator() {
        return this;
    }
}
