public class OffByN implements CharacterComparator {

    /**
     * Returns true if characters are off by n from each other.
     */
    private int n;

    public OffByN(int n) {
        this.n = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) == n) {
            return true;
        }
        return false;
    }
}