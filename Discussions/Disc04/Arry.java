// https://fa20.datastructur.es/materials/discussion/examprep03.pdf

class Arry {

    /** Takes in a 2-D array x and returns a 1-D array that contains all of the arrays in x concatenated together. */
    public static int[] flatten(int[][] x) {
        int totalLength = 0;
        for (int[] arr : x) {
            totalLength += arr.length;
        }

        int[] a = new int[totalLength];
        int aIndex = 0;
        for (int[] arr : x) {
            System.arraycopy(arr, 0, a, aIndex, arr.length);
            aIndex += arr.length;
        }

        return a;
    }

    public static void main(String[] args) {
        int[][] b = {{1, 2, 3}, {}, {7, 8}};
        int[] a = Arry.flatten(b);
    }
}
