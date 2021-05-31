// https://fa20.datastructur.es/materials/discussion/disc03.pdf

public class Arr {
    /** Inserts an item into arr at the given position and returns the resulting array.
     * This method cannot work destructively because arr will not have any empty space for moving items over to make space for the new one.
     */
    public static int[] insert(int[] arr, int item, int position) {
        int[] resultArray = new int[arr.length + 1];
        position = Math.min(position, arr.length);
        System.arraycopy(arr, 0, resultArray, 0, position);
        resultArray[position] = item;
        System.arraycopy(arr, position, resultArray, position + 1, arr.length - position);
        return resultArray;
    }

    private static int sumOfArray(int[] arr) {
        int sum = 0;
        for (int x: arr) {
            sum = sum + x;
        }
        return sum;
    }

    /** A non-destructive method that replaces the number at index i with arr[i] copies of itself. */
    public static int[] replicate(int[] arr) {
        int[] result = new int[sumOfArray(arr)];
        int current = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i]; j++) {
                result[current] = arr[i];
                current = current + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 5, 6};
        int[] b = insert(a, 4, 3);
        b = insert(b, 10, 10);
        b = replicate(a);
    }
}