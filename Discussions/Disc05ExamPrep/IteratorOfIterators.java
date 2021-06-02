// https://fa20.datastructur.es/materials/discussion/examprep05.pdf

import java.lang.reflect.Array;
import java.util.*;

/** Iterates over all the elements of each iterator in the list of iterators given as argument. */
public class IteratorOfIterators implements Iterator<Integer> {
    private List<Iterator<Integer>> L;
    private int currentIterator;

    public IteratorOfIterators(List<Iterator<Integer>> a) {
        L = a;
        currentIterator = 0;
    }

    @Override
    public boolean hasNext() {
        if (currentIterator == L.size()) {
            return false;
        } else if (L.get(currentIterator).hasNext()) {
            return true;
        } else {
            currentIterator += 1;
            return hasNext();
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more integers!");
        }
        return L.get(currentIterator).next();
    }

    public static void main(String[] args) {
        List<Integer> a1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> a2 = Arrays.asList(6, 7, 8, 9, 10);
        List<Integer> a3 = Arrays.asList(11, 12, 13, 14, 15);
        IteratorOfIterators itr = new IteratorOfIterators(Arrays.asList(a1.iterator(), a2.iterator(), a3.iterator()));
        for(int i = 0; i < 20; i++) {
            System.out.print(itr.next() + " ");
        }
    }
}
