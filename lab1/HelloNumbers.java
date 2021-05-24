public class HelloNumbers {
    public static void main(String[] args) {
        int cumulative_sum = 0;
        int x = 0;
        while (x < 10) {
            cumulative_sum += x;
            System.out.print(cumulative_sum + " ");
            x += 1;
        }
        System.out.print("\n");
    }
}