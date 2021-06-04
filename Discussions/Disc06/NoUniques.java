// https://fa20.datastructur.es/materials/discussion/disc06.pdf

import java.util.Arrays;

public class NoUniques {

    /** Returns true if there is no unique value in the array, otherwise returns false. */
    public static boolean mystery(int [] array) {
        Arrays.sort(array);
        int current = array[0];
        boolean flag = true;
        for(int i = 1; i < array.length; i++) {
            if(array[i] == current) {
                flag = false;
            } else if (flag){
                return false;
            } else {
                flag = true;
                current = array[i];
            }
        }
        return !flag;
    }

    public static void main(String[] args) {
        int[] a = {0, 0, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5};
        System.out.print(mystery(a));
    }
}
