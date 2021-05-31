// https://fa20.datastructur.es/materials/lectures/lec7/lec7

// 1. Both integration testing and unit testing are good complements to each other.

import java.lang.reflect.Array;

public class AssertArrayEqual {

    /** Takes in two arrays and checks whether they are equal. */
    public static boolean equal(Object[] a1, Object[] a2) {
        boolean equal = true;
        if (a1.length != a2.length) {
            return false;
        }
        for (int i = 0; i < a1.length; i++) {
            if ((a1[i].getClass().isArray()) && (a2[i].getClass().isArray())) {
                equal = AssertArrayEqual.equal((Object[]) a1[i], (Object[]) a2[i]) && equal;
                if (!equal) {
                    return false;
                }
            } else if (!a1[i].equals(a2[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        Object[] a = {1, new Integer[]{2, 3}, 4};
        Object[] b = {1, new Integer[]{2, 3}, 4};
        System.out.println(AssertArrayEqual.equal(a, b));
        System.out.println(a.equals(b));
    }
}
