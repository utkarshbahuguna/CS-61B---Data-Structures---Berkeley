// https://fa20.datastructur.es/materials/discussion/disc01.pdf

/* 2. Mystery; The mystery method takes in an integer array and an integer k
    and returns the index of the smallest number in the subarray starting at index k and going to the end.
    
    For the given input, the mystery function would return 4,
    which is the index of the smallest number in the subarray starting at index 2.
*/

/* mystery2 sorts an array by finding the next smallest element for every iteration of the while loop
    and swapping it with the element at the current index.

    The example array would get sorted in ascending order, i.e. [0, 3, 3, 4, 6]
*/

// 3. Fib

/** Returns the nth Fibonacci number. */
public class Fib {
    public static int fib(int n) {
        int current = 0;
        int next = 1;
        while (n > 0) {
            int temp = next;
            next = current + next;
            current = temp;

            n -= 1;
        }
        return current;
    }

    /** Returns the nth Fibonacci number using a recursive solution.
      *  @param n fibonacci number
        @param k index of current fibonacci number
        @param f0 current fibonacci number
        @param f1 next fibonacci number
     */
    public static int fib2(int n, int k, int f0, int f1) {
        if (k == n) {
            return f0;
        } else {
            return fib2(n, k + 1, f1, f0 + f1);
        }
    }

    public static void main(String[] args) {
        System.out.println(fib2(Integer.parseInt(args[0]), 0, 0, 1));
    }
}