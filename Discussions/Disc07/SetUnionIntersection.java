// https://fa20.datastructur.es/materials/discussion/disc07.pdf

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SetUnionIntersection<T> {

    /** Returns a list of union of arrays A and B in linear time. */
    public static <T> List<T> unionArrays(T[] A, T[] B) {
        HashSet<T> unionSet = new HashSet<>();
        for (T element : A) {
            unionSet.add(element);
        }
        for (T element : B) {
            unionSet.add(element);
        }

        List<T> unionList = new ArrayList<>();
        for (T element : unionSet) {
            unionList.add(element);
        }
        return unionList;
    }

    /** Returns a list of intersection of arrays A and B in linear time. */
    public static <T> List<T> intersectionArrays(T[] A, T[] B) {
        HashSet<T> aSet = new HashSet<>();
        HashSet<T> bSet = new HashSet<>();
        List<T> intersectionList = new ArrayList<>();

        for (T element : A) {
            aSet.add(element);
        }
        for (T element : B) {
            bSet.add(element);
        }
        for (T element : aSet)  {
            if (bSet.contains(element)) {
                intersectionList.add(element);
            }
        }
        return intersectionList;
    }

    public static void main(String[] args) {
        Integer[] a = {2, 1, 3, 4};
        Integer[] b = {3, 4, 6, 5};
        List<Integer> union = unionArrays(a, b);
        List<Integer> intersection = intersectionArrays(a, b);
    }


}
