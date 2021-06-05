// https://fa20.datastructur.es/materials/discussion/disc07.pdf

public class SumExists {

    /**
     * Given a sorted array A of N distinct integers, finds if there exists indices i and j such that A[i] + A[j] == x.
     */
    public static boolean findSum(int[] A, int x) {
        int first = 0;
        int last = A.length - 1;
        while (first < last) {
            int sum = A[first] + A[last];
            if (sum == x) {
                return true;
            } else if (sum < x) {
                first += 1;
            } else {
                last -= 1;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] A = {1, 2, 3, 5, 6, 8, 10};
        System.out.println(SumExists.findSum(A, 11));
        System.out.println(SumExists.findSum(A, 17));
    }
}
