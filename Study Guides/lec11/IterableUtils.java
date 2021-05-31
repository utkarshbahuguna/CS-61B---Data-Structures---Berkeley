// https://tbp.berkeley.edu/exams/6137/download/#page=8

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IterableUtils {

    /** Returns a list from an iterable. If any item in the iterable are null, throws an IllegalArgumentException. */
    public static <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<>();
        for (T item: iterable) {
            if (item == null) {
                throw new IllegalArgumentException("item is null");
            }
            resultList.add(item);
        }
        return resultList;
    }
}
