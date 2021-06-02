import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offBy3 = new OffByN(3);

    @Test
    public void testOffByN() {
        assertTrue(offBy3.equalChars('a', 'd'));
        assertTrue(offBy3.equalChars('d', 'a'));
        assertFalse(offBy3.equalChars('a', 'a'));
        assertFalse(offBy3.equalChars('A',  'a'));
        assertFalse(offBy3.equalChars('a',  'x'));
        assertFalse(offBy3.equalChars('b',  'f'));
        assertFalse(offBy3.equalChars('&',  '%'));
    }
}