// https://tbp.berkeley.edu/exams/4695/download/#page=9

public class LongestNeighbors {

    NullSafeStringComparator sc = new LengthComparator();

    public static String max(String[] a, NullSafeStringComparator sc) {
        String maxStr = a[0];
        for (int i = 1; i < a.length; i++) {
            if(sc.compare(a[i], maxStr) > 0) {
                maxStr = a[i];
            }
        }
    return maxStr;
    }

    public static String[][] step(String[][] arr) {
        String[][] stepped = new String[arr.length][arr[0].length];
        for (int i = 1; i < arr.length - 1; i += 1) {
            for (int j = 1; j < arr[0].length - 1; j += 1) {
                String[] temp = new String[9];
                int index = 0;
                for (int k = -1; k <= 1; k += 1) {
                    for (int m = -1; m <= 1; m += 1) {
                        temp[index] = arr[i+k][j+m];
                        index += 1;
                    }
                }
                stepped[i][j] = max(temp, new LengthComparator());
            }
        }
    return stepped;
    }

    public static void main(String[] args) {
        String[][] a = {{null, null, null, null, null, null},
                {null, "a", "cat", "cat", "dogs", null},
                {null, "a", null, "cat", "a", null},
                {null, "a", "ca", "", "ca", null},
                {null, null, null, null, null, null}};
        String[][] b = LongestNeighbors.step(a);
        for (int i = 0; i < b.length; i += 1) {
            for (int j = 0; j < b[0].length; j += 1) {
                System.out.print(b[i][j] + " ");
            }
            System.out.println();
        }
    }
}
