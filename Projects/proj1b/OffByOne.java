public class OffByOne implements CharacterComparator {

    /**
     * Returns true if characters are off by one from each other.
     */
    @Override
    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) == 1) {
            return true;
        }
        return false;
    }
}
