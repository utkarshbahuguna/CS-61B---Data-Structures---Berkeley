// https://fa20.datastructur.es/materials/discussion/examprep05.pdf

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.logging.Filter;

public class FilteredList<T> implements Iterable<T>, Iterator<T> {
    private List<T> L;
    private Predicate<T> filter;
    private int index;

    public FilteredList(List<T>L, Predicate<T> filter) {
        this.L = L;
        this.filter = filter;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        if (index == L.size()) {
            return false;
        } else if (filter.test(L.get(index))) {
            return true;
        } else {
            index += 1;
            return hasNext();
        }
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Iterator empty!");
        } else {
            index += 1;
            return L.get(index - 1);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new FilteredList<>(L, filter);
    }


    public static void main(String[] args) {
        class DivisibleByTwo implements Predicate<Integer> {
            @Override
            public boolean test(Integer x) {
                return x % 2 == 0;
            }
        }

        Predicate<Integer> d2Checker = new DivisibleByTwo();
        FilteredList<Integer> evens = new FilteredList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), d2Checker);
        for (int i : evens) {
            System.out.print(i + " ");
        }
        for (int i : evens) {
            System.out.print(i + " ");
        }
    }
}
