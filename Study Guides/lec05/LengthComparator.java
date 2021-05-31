// https://tbp.berkeley.edu/exams/4695/download/#page=9

public class LengthComparator implements NullSafeStringComparator{

    /** Returns a negative number if s1 is 'less than' s2, 0 if 'equal',
     *  and a positive number otherwise. Null is considered less than any String.
     *  If both inputs are null, return 0.
     */
    public int compare(String s1, String s2) {
        if (s1 == null) { s1 = ""; }
        if (s2 == null) { s2 = ""; }

        return s1.length() - s2.length();
    }

    public static void main(String[] args){
        LengthComparator lc = new LengthComparator();
        System.out.println(lc.compare("12", "1"));
    }
}
